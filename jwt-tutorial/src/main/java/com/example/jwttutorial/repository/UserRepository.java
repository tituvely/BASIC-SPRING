package com.example.jwttutorial.repository;

import com.example.jwttutorial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // @EntityGraph는 쿼리가 수행될때 Lazy조회가 아니라 Eager 조회로 authorities 정보를 가져온다.
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
