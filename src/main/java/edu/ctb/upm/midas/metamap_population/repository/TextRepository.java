package edu.ctb.upm.midas.metamap_population.repository;

import edu.ctb.upm.midas.metamap_population.model.jpa.Text;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends CrudRepository<Text, String> {
}
