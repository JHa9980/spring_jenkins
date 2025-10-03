package com.skala.springbootsample.controller;

import java.util.List;

import com.skala.springbootsample.model.Region;
import com.skala.springbootsample.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    // 모든 지역 조회
    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.findAll();
        return ResponseEntity.ok(regions);
    }

    // 특정 지역 조회
    @GetMapping("/regions/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        log.info("getRegionById called with id: {}", id);
        return regionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 지역명으로 조회
    @GetMapping("/regions/name/{name}")
    public ResponseEntity<Region> getRegionByName(@PathVariable String name) {
        log.info("getRegionByName called with name: {}", name);
        return regionService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 지역 생성
    @PostMapping("/regions")
    public ResponseEntity<?> createRegion(@RequestBody Region region) {
        Region createdRegion = regionService.create(region);
        return new ResponseEntity<>(createdRegion, HttpStatus.CREATED);

    }

    // 지역 수정
    @PutMapping("/regions/{id}")
    public ResponseEntity<?> updateRegion(@PathVariable Long id, @RequestBody Region updatedRegion) {
        return regionService.update(id, updatedRegion)
                .map(region -> ResponseEntity.ok(region))
                .orElse(ResponseEntity.notFound().build());
    }

    // 지역 삭제
    @DeleteMapping("/regions/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        return regionService.delete(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}