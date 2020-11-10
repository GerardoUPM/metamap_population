package edu.ctb.upm.midas.metamap_population.repository;

import edu.ctb.upm.midas.metamap_population.model.jpa.HasSymptom;
import edu.ctb.upm.midas.metamap_population.model.jpa.HasSymptomPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HasSymptomRepository extends CrudRepository<HasSymptom, HasSymptomPK> {
}
