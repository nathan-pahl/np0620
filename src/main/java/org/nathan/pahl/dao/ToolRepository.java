package org.nathan.pahl.dao;

import org.nathan.pahl.model.Tool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A CrudRepository for retrieving a {@link Tool} from a database.
 */
@Repository
public interface ToolRepository extends CrudRepository<Tool, String> {

}
