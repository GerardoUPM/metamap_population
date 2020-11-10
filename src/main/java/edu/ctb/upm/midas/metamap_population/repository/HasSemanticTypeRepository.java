package edu.ctb.upm.midas.metamap_population.repository;

import edu.ctb.upm.midas.metamap_population.model.jpa.HasSemanticType;
import edu.ctb.upm.midas.metamap_population.model.jpa.HasSemanticTypePK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HasSemanticTypeRepository extends CrudRepository<HasSemanticType, HasSemanticTypePK> {
}
