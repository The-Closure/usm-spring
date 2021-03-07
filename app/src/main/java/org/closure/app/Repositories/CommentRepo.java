package org.closure.app.Repositories;

import org.closure.app.entities.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<CommentEntity,Long>{
    
}
