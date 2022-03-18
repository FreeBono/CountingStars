package com.ssafy.cstars.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.ssafy.cstars.domain.entity.RefreshToken;
import com.ssafy.cstars.domain.entity.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  Boolean existsByToken(String token);

  @Modifying
  int deleteByUser(User user);
}
