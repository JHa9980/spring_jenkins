package com.skala.springbootsample.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skala.springbootsample.exception.BadRequestException;
import com.skala.springbootsample.model.Region;
import com.skala.springbootsample.model.User;
import com.skala.springbootsample.repository.RegionRepository;
// import com.sk.skala.myfirstapp.repository.FileUserRepository;

import com.skala.springbootsample.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    public UserService(UserRepository userRepository, RegionRepository regionRepository) {
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
    }

    public List<User> findAll(Optional<String> name) {
        Collection<User> users = userRepository.findAll();

        if (name.isPresent()) {
            return users.stream()
                    .filter(user -> user.getName().equalsIgnoreCase(name.get()))
                    .toList();
        }
        log.debug("유저 {}", users);
        return new ArrayList<>(users);
    }

    public Optional<User> findByID(long id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        if (user.getRegion() == null) {
            throw new BadRequestException("Region information must be provided.");
        }

        Region region;
        // ID가 있으면 ID로 조회
        if (user.getRegion().getId() != null) {
            region = regionRepository.findById(user.getRegion().getId())
                    .orElseThrow(() -> new BadRequestException("Region not found with id: " + user.getRegion().getId()));
        }
        //  post 입력값
        //     {
        //     "name": "alice",
        //     "email": "alice@example.com",
        //     "region": {
        //         "id": 1
        //         }
        //     }

        // ID는 없고 이름이 있으면 이름으로 조회
        else if (user.getRegion().getName() != null) {
            region = regionRepository.findByName(user.getRegion().getName())
                    .orElseThrow(() -> new BadRequestException("Region not found with name: " + user.getRegion().getName()));
        }
        //  post 입력값
        //     {
        //     "name": "alice",
        //     "email": "alice@example.com",
        //     "region": {
        //         "name": "서울"
        //         }
        //     }

        // ID와 이름 둘 다 없으면 예외 처리
        else {
            throw new BadRequestException("Either region 'id' or 'name' must be provided.");
        }

        // 영속화된 Region 객체로 설정
        user.setRegion(region);

        return userRepository.save(user);
    }

    public Optional<User> update(long id, User updated) {
        try {
            return Optional.of(userRepository.save(updated));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
