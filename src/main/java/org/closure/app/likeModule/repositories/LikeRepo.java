package org.closure.app.likeModule.repositories;

import java.util.List;
import java.util.Optional;

import org.closure.app.entities.LikeEntity;
import org.closure.app.entities.PostEntity;
import org.closure.app.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepo extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> findByPentity(PostEntity pEntity);
    List<LikeEntity> findByUentity(UserEntity uEntity);
    Optional<LikeEntity> findByPentityAndUentity(PostEntity pEntity, UserEntity uEntity);
}
