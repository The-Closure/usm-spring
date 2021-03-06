package org.closure.app.profsModule.repositories;

import org.closure.app.entities.ProfsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfRepo extends JpaRepository<ProfsEntity, Long> {
    
}
