package org.closure.app.CommunityModule.repositories;

import java.util.List;
import java.util.Optional;

import org.closure.app.entities.CommunityEntity;
import org.closure.app.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepo extends JpaRepository<CommunityEntity, Long>{

    Optional<CommunityEntity> findByUsers(UserEntity userEntity);

    Optional<CommunityEntity> findByName(String name);

    @Query(value="select * from communities  where name like %:keyword% or description like %:keyword%", nativeQuery=true)
    List<CommunityEntity> findByNameLike(@Param("keyword") String value);
    
}
