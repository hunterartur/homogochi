package com.myhuholi.homogochi.repository;

import com.myhuholi.homogochi.domain.HomoPicture;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomoPictureRepository extends JpaRepository<HomoPicture, Long> {
    @Query(value = "FROM HomoPicture pic WHERE pic.stateSysName = ?1 AND pic.defaultFlag IS TRUE")
    Optional<HomoPicture> findDefaultHomoPictureByStateSysName(String stateSysName);
}
