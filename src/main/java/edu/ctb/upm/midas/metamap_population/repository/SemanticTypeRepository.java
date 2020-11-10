package edu.ctb.upm.midas.metamap_population.repository;

import edu.ctb.upm.midas.metamap_population.model.jpa.SemanticType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemanticTypeRepository extends CrudRepository<SemanticType, String> {
}
