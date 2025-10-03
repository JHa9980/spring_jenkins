package com.skala.springbootsample.repository;

import com.skala.springbootsample.model.Region;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    // 지역명으로 조회
    Optional<Region> findByName(String name);

    // 지역명 존재 여부 확인
    boolean existsByName(String name);
}
