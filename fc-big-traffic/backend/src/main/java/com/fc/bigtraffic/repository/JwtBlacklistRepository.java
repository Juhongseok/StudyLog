package com.fc.bigtraffic.repository;

import com.fc.bigtraffic.entity.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Long> {

    Optional<JwtBlacklist> findByToken(String token);

    Optional<JwtBlacklist> findTopByEmailOrderByExpirationTimeDesc(String email);

}
