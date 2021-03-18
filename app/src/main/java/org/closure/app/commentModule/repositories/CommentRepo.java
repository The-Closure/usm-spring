package org.closure.app.commentModule.repositories;

import org.closure.app.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
    
}
