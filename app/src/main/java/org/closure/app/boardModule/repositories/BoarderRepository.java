package org.closure.app.boardModule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.closure.app.entities.BoardEntity;

@Repository
public interface BoarderRepository extends JpaRepository<BoardEntity, Long> {
    
}
