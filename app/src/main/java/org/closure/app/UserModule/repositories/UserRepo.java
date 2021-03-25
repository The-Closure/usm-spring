package org.closure.app.UserModule.repositories;


import java.util.List;
import java.util.Optional;

import org.closure.app.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByIdAndPassword(Long id, String password);

    Optional<UserEntity> findByIdAndName(Long id, String name);

    @Query(value="select * from user u where u.name like %:keyword% or u.email like %:keyword%", nativeQuery=true)
    List<UserEntity> findByEmailLikeOrNameLike(@Param("keyword") String keyword);


}
