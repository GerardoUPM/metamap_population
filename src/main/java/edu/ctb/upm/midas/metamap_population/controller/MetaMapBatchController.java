package edu.ctb.upm.midas.metamap_population.controller;

import edu.ctb.upm.midas.metamap_population.common.util.TimeProvider;
import edu.ctb.upm.midas.metamap_population.model.filter.common.Consult;
import edu.ctb.upm.midas.metamap_population.service.MetaMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.text.ParseException;

@RestController
@RequestMapping("${my.service.rest.request.mapping.retrieval.general.url}")
public class MetaMapBatchController {

    private static final Logger logger = LoggerFactory.getLogger(MetaMapBatchController.class);


    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private MetaMapService metaMapService;

    @GetMapping(path = {"${my.service.rest.request.mapping.metamap.batch.inserts.path}"}
    , params = {"source", "snapshot"})
    public ResponseEntity<HttpStatus> insertMetaMapData(@RequestParam(value = "source") @Valid @NotBlank @NotNull @NotEmpty String source,
                                                    @RequestParam(value = "snapshot") @Valid @NotBlank @NotNull @NotEmpty String snapshot) throws Exception {
        boolean resp = false;
        String start = timeProvider.getTime();
        Consult consult;
        try {
            consult = new Consult(source, snapshot);
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (consult.getSource() != null && consult.getSnapshot() != null) {
            resp = metaMapService.insert(consult);
            logger.info("START: " + start + " | END: " + timeProvider.getTime());
            if (resp)
                return ResponseEntity.status(HttpStatus.OK).build();
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
