package org.closure.app.UserModule.repositories;


import java.util.List;
import java.util.Optional;

import org.closure.app.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByIdAndPassword(Long id, String password);

    Optional<UserEntity> findByIdAndName(Long id, String name);

    List<UserEntity> findByEmailLikeOrNameLike(String email, String name);


}
