package edu.ctb.upm.midas.metamap_population.service;

import com.google.gson.Gson;
import edu.ctb.upm.midas.metamap_population.constant.Constants;
import edu.ctb.upm.midas.metamap_population.model.filter.common.Consult;
import edu.ctb.upm.midas.metamap_population.model.filter.metamap.response.Concept;
import edu.ctb.upm.midas.metamap_population.model.filter.metamap.response.ProcessedText;
import edu.ctb.upm.midas.metamap_population.model.filter.metamap.response.Response;
import edu.ctb.upm.midas.metamap_population.model.filter.metamap.response.Text;
import edu.ctb.upm.midas.metamap_population.model.jpa.*;
import edu.ctb.upm.midas.metamap_population.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@Service
public class MetaMapService {

    private static final Logger logger = LoggerFactory.getLogger(MetaMapService.class);

    @Autowired
    private SymptomRepository symptomRepository;
    @Autowired
    private HasSymptomRepository hasSymptomRepository;
    @Autowired
    private SemanticTypeRepository semanticTypeRepository;
    @Autowired
    private HasSemanticTypeRepository hasSemanticTypeRepository;
    @Autowired
    private TextRepository textRepository;


// (/*propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, */noRollbackFor={Exception.class})
    @Transactional
    public boolean insert(Consult consult) throws Exception{
        logger.info("Querying information about of: " + consult);
        List<SemanticType> semanticTypes = new ArrayList<>();
        List<Symptom> symptoms = new ArrayList<>();
        List<HasSymptom> hasSymptoms = new ArrayList<>();
        List<HasSemanticType> hasSemTypes = new ArrayList<>();

        List<edu.ctb.upm.midas.metamap_population.model.filter.metamap.special.Symptom> symptomsAux = new ArrayList<>();


        Response response = null;
        try {
            response = readMetamapResponseJSON(consult, false);
        } catch (Exception e) {
            logger.error("Error to read the MetaMap Json", e);
        }

        if (response != null) {
            List<Text> textList = response.getTextList();
            if (textList.size() > 0) {
                try {
                    int textCount = 1, conceptCount = 1, countTotalHasSymptom = countHasSymptom(textList);
                    for (Text metamapText : textList) {
                        //Validar que haya conceptos
                        if (metamapText.getConcepts() != null) {
                            //Al menos un concepto
                            if (metamapText.getConcepts().size() > 0) {
                                List<Concept> noRepeatedConcepts = removeRepetedConcepts(metamapText.getConcepts());
                                conceptCount = createHasSymptomElement(metamapText.getConcepts(), noRepeatedConcepts, hasSymptoms, metamapText.getId(), conceptCount);

                                for (Concept concept : metamapText.getConcepts()) {
                                    Symptom symptom = new Symptom(concept.getCui(), concept.getName());
                                    //Se agrega a la lista
                                    symptoms.add(symptom);

                                    edu.ctb.upm.midas.metamap_population.model.filter.metamap.special.Symptom symp = new edu.ctb.upm.midas.metamap_population.model.filter.metamap.special.Symptom(concept.getCui(), concept.getName(), concept.getSemanticTypes());
                                    symptomsAux.add(symp);

                                    for (String semanticType : concept.getSemanticTypes()) {
                                        SemanticType semType = new SemanticType(semanticType);
                                        semanticTypes.add(semType);
                                    }
                                }
                                textCount++;
                            }
                        }
                    }
                    System.out.println("conceptCount: " + conceptCount + ", countTotalHasSymptom: " + countTotalHasSymptom);
                } catch (Exception e) {
                    System.out.println("Mensaje de la excepción 2: " + e.getMessage());
                }
            }


            //Eliminar repetidos
            System.out.println("SemanticTypes repetidos size: " + semanticTypes.size());
            semanticTypes = removeRepetedSemanticTypesJpa(semanticTypes);
            System.out.println("SemanticTypes sin repetir size: " + semanticTypes.size());

            //Sintomas <<formar los insert para insertar sintomas "symptom" y sus tipos semanticos "has_semantic_type">>
            System.out.println("symptoms repetidos size: " + symptoms.size());
            symptoms = removeRepetedSymptomsJpa(symptoms);
            System.out.println("symptoms sin repetir size: " + symptoms.size());

//        symptomsAux = removeRepetedSymptoms(symptomsAux);

            //HasSymptoms resultado del proceso de metamap en la tabla "has_symptom"
            System.out.println("semantic_types size: " + semanticTypes.size());
            System.out.println("symptoms size: " + symptoms.size());
            System.out.println("has_semantic_types size: " + hasSemTypes.size());
            System.out.println("has_symptoms size: " + hasSymptoms.size());

//        //region COMPRUEBA QUE TODOS LOS TEXTOS EXISTAN
//        // SI ESTA PEQUEÑA PRUEBA SALE BIEN, SE PROCEDE A INSERTAR LOS DATOS
//        int count = 1, errorTextCount=0;
//        for (HasSymptom hasSymptom: hasSymptoms) {
////            logger.info(hasSymptom.toString());
//            try {
////                hasSymptomRepository.save(hasSymptom);
//                edu.ctb.upm.midas.metamap_population.model.jpa.Text text = textRepository.findById(hasSymptom.getTextId()).orElse(null);
//                if (text==null){
//                    errorTextCount++;
//                    logger.error("THIS TEXT ID DOESN'T EXIST. PLEASE, VERIFY => " + hasSymptom.getTextId());
//                }
//            }catch (Exception e) {
//                logger.error("Error to insert => " + hasSymptom, e);
//            }
////            if (count==10) break;
//            count++;
//        }
//        //endregion

            int countSymp = 1, errorSympCount = 0;
            for (edu.ctb.upm.midas.metamap_population.model.filter.metamap.special.Symptom symptom : symptomsAux) {
                try {
                    Symptom symp = symptomRepository.findById(symptom.getCui()).orElse(null);
                    if (symp == null) {
                        logger.warn("THIS TEXT ID DOESN'T EXIST => " + symptom.getCui());
                        logger.info("The medical term and its respective relationship will be inserted");
                        Symptom sympInserted = symptomRepository.save(new Symptom(symptom.getCui(), symptom.getName()));
                        if (sympInserted.getCui() != null) {
                            logger.info("   " + errorSympCount + "/" + symptomsAux.size() + " => SUCCESSFUL SYMPTOM INSERTED: " + sympInserted);
                            for (String semanticType : symptom.getSemanticTypes()) {
                                SemanticType existstingTS = semanticTypeRepository.findById(semanticType).orElse(null);
                                if (existstingTS == null) {
                                    //Si no existe, se inserta el tipo semántico y despues la relación: has_semantic_type
                                    SemanticType tsInserted = semanticTypeRepository.save(new SemanticType(semanticType));
                                    if (tsInserted.getSemanticType() != null) {
                                        logger.info("       SUCCESSFUL SEMANTIC TYPE INSERTED: " + tsInserted);
                                        insertSemanticType(sympInserted.getCui(), tsInserted.getSemanticType());
                                    }
                                } else {//Si existe, se crea la relación: has_semantic_type
                                    insertSemanticType(sympInserted.getCui(), existstingTS.getSemanticType());
                                }
                            }
                        }
                        errorSympCount++;
                    }
                } catch (Exception e) {
                    logger.error("Error to insert => " + symptom, e);
                    return false;
                }
//            if (countSymp==10) break;
                countSymp++;
            }
            logger.info(errorSympCount + " NEW SYMPTOMS INSERTED");

//        if (errorTextCount==0) {
            logger.info("Successful text verification");
            logger.info("The data set is populated with information from the " + consult.getSource() + " snapshot of " + consult.getSnapshot());

            logger.info("START batch has symptom insert");
            hasSymptomRepository.saveAll(hasSymptoms);
            logger.info("END batch has symptom insert");
            logger.info("Successful MetaMap population from the " + consult.getSource() + " snapshot of " + consult.getSnapshot());
            return true;
//        }
        }  else {
            return false;
        }
    }

    public void insertSemanticType(String cui, String semanticType){
        HasSemanticType existingSHT = hasSemanticTypeRepository.findById(new HasSemanticTypePK(cui, semanticType)).orElse(null);
        if (existingSHT==null){
            HasSemanticType hstInserted = hasSemanticTypeRepository.save(new HasSemanticType(cui, semanticType));
            if (hstInserted.getCui()!=null){
                logger.info("       SUCCESSFUL HAS SEMANTIC TYPE RELATION INSERTED: " + hstInserted);
            }
        }
    }


    private int countHasSymptom(List<Text> textList){
        int count = 1;//1 para ajustar
        for (Text text: textList) {
            count += removeRepetedConcepts(text.getConcepts()).size();
        }
        return count;
    }

    private List<Concept> removeRepetedConcepts(List<Concept> elements){
        //Se crea esta lista para no afectar a la original
        List<Concept> elements_2 = new ArrayList<>();
        elements_2.addAll(elements);
        List<Concept> resList = elements_2;
        Set<Concept> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(elements_2);
        elements_2.clear();
        elements_2.addAll(linkedHashSet);

        return resList;
    }

    private List<SemanticType> removeRepetedSemanticTypesJpa(List<SemanticType> elements){
        List<SemanticType> resList = elements;
        Set<SemanticType> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(elements);
        elements.clear();
        elements.addAll(linkedHashSet);

        return resList;
    }

    private List<Symptom> removeRepetedSymptomsJpa(List<Symptom> elements){
        List<Symptom> resList = elements;
        Set<Symptom> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(elements);
        elements.clear();
        elements.addAll(linkedHashSet);

        return resList;
    }


    private int createHasSymptomElement(List<Concept> concepts, List<Concept> noRepeatedConcepts, List<HasSymptom> hasSymptoms, String textId, int conceptCount){
        //System.out.println("concepts: " + concepts.size() + " noRepetead: " + noRepeatedConcepts.size());
        try {
            int countC = 1;
            for (Concept uniqueConcept : noRepeatedConcepts) {
                conceptCount++;
                HasSymptom hasSymptom = new HasSymptom(textId, uniqueConcept.getCui(), (byte) 0);
                final int[] count = {1};
                concepts.stream().filter(o -> o.getCui().equals(uniqueConcept.getCui())).forEach(
                        o -> {
                            String matchedWords_ = "";
                            String positionalInfo_ = "";
                            if (count[0] == 1) {
                                matchedWords_ = o.getMatchedWords().toString();
                                positionalInfo_ = o.getPositionalInfo();
                            } else {
                                matchedWords_ = hasSymptom.getMatchedWords() + "&" + o.getMatchedWords().toString();
                                positionalInfo_ = hasSymptom.getPositionalInfo() + "&" + o.getPositionalInfo();
                            }
                            hasSymptom.setMatchedWords(matchedWords_);
                            hasSymptom.setPositionalInfo(positionalInfo_);

                            //System.out.println("    " + count + ". concept: " + o.getCui() + " = match: " + o.getMatchedWords().toString());

                            count[0]++;
                        }
                );
                //
                hasSymptoms.add(hasSymptom);
//                System.out.println(conceptCount + ". " + hasSymptom);

            }
        }catch (Exception e){
            System.out.println("Mensaje de la excepción 2: " + e.getMessage());
        }

        return conceptCount;
    }


    private Response readMetamapResponseJSON(Consult consult, boolean onlyTexts) throws Exception {
        List<Text> texts = new ArrayList<>();
        Response response = null;
        System.out.println("Read MetaMap JSON!...");
        Gson gson = new Gson();
        String fileName = consult.getSnapshot() + "_" + consult.getSource() + Constants.METAMAP_FILE_NAME + Constants.DOT_JSON;//adis = disease album
        String path = Constants.METAMAP_FOLDER + fileName;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            if (onlyTexts) {
                ProcessedText resp = gson.fromJson(br, ProcessedText.class);
                texts = resp.getTexts();
            }else{
                response = gson.fromJson(br, Response.class);
                texts = response.getTextList();
            }
        }catch (Exception e){
            System.out.println("Error to read or convert MetaMap JSON!...");
        }

        /*for (edu.upm.midas.data.validation.metamap.model.response.Text text: resp.retrieveTexts()) {
            System.out.println("TextId: " + text.getId() + " | Concepts: " + text.getConcepts().toString());
        }*/

        return response;
    }

}
