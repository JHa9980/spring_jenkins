package com.skala.springbootsample.repository;

import com.skala.springbootsample.model.User;

import java.util.List;

// import java.util.Collection;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// 아래 주석처리 된 글은 FileUserRepository 같이 사용할 때 사용
// public interface UserRepository {
//     // 조회
//     Collection<User> findAll();
//     Optional<User> findById(Long id);

//     // 생성 (id 없으면 자동 생성)
//     User create(User user);

//     // 갱신 (존재하지 않으면 예외)
//     User update(Long id, User user);

//     // 삭제 (존재하지 않으면 무시 또는 예외 - 여기선 무시)
//     void delete(Long id);
// }

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 이름으로 사용자 검색 (대소문자 구분 없음)
    List<User> findByNameIgnoreCase(String name);

    // 지역별 사용자 조회
    List<User> findByRegionId(Long regionId);

    // 지역명으로 사용자 조회 (Spring Data JPA 메서드 네이밍 규칙 사용)
    List<User> findByRegionName(String regionName);

    // 이메일로 사용자 존재 여부 확인
    boolean existsByEmail(String email);
}

