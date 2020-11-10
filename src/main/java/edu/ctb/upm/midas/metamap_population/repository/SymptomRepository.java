package edu.ctb.upm.midas.metamap_population.repository;

import edu.ctb.upm.midas.metamap_population.model.jpa.Symptom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends CrudRepository<Symptom, String> {
}
