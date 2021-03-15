package org.closure.app.CommunityModule.repositories;

import java.util.Optional;

import org.closure.app.entities.CommunityEntity;
import org.closure.app.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepo extends JpaRepository<CommunityEntity, Long>{

    Optional<CommunityEntity> findByUsers(UserEntity userEntity);

    Optional<CommunityEntity> findByName(String name);
    
}
