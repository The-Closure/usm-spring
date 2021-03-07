package org.closure.app.Repositories;

import org.closure.app.entities.ScholarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarRepo extends CrudRepository<ScholarEntity,Long>{
    
}
