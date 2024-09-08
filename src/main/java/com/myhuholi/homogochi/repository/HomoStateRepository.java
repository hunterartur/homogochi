package com.myhuholi.homogochi.repository;

import com.myhuholi.homogochi.domain.HomoState;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomoStateRepository extends JpaRepository<HomoState, Long> {
    Optional<HomoState> findHomoStateBySysName(String sysName);

    Optional<HomoState> findHomoStateByDefaultFlagIsTrue();
}
