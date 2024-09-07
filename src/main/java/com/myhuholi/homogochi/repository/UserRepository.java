package com.myhuholi.homogochi.repository;

import com.myhuholi.homogochi.domain.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "FROM User u JOIN HomoState where u.uuid = ?1")
    Optional<User> findUserByUuid(UUID uuid);
}
