package org.closure.app.FirebaseModule.repositories;

import java.util.Optional;

import org.closure.app.entities.FirebaseEntity;
import org.closure.app.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseRepo extends JpaRepository<FirebaseEntity,Long> {
    Optional<FirebaseEntity> findByOwner(UserEntity owner);
}
