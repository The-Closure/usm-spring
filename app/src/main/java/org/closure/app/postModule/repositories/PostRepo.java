package org.closure.app.postModule.repositories;

import org.closure.app.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<PostEntity,Long> {
    
}