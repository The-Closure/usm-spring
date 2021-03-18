package org.closure.app.boardModule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.closure.app.entities.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    
}
