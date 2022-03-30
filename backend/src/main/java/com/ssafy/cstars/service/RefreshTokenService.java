package com.ssafy.cstars.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.ssafy.cstars.domain.entity.RefreshToken;
import com.ssafy.cstars.domain.entity.RefreshTokenBrandAdmin;
import com.ssafy.cstars.domain.entity.RefreshTokenStoreAdmin;
import com.ssafy.cstars.domain.repository.BrandAdminRepository;
import com.ssafy.cstars.domain.repository.RefreshTokenBrandAdminRepository;
import com.ssafy.cstars.domain.repository.RefreshTokenRepository;
import com.ssafy.cstars.domain.repository.RefreshTokenStoreAdminRepository;
import com.ssafy.cstars.domain.repository.StoreAdminRepository;
import com.ssafy.cstars.domain.repository.UserRepository;
import com.ssafy.cstars.exception.TokenRefreshException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenService {
  @Value("${bezkoder.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private RefreshTokenStoreAdminRepository refreshTokenStoreAdminRepository;

  @Autowired
  private RefreshTokenBrandAdminRepository refreshTokenBrandAdminRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private StoreAdminRepository storeAdminRepository;

  @Autowired
  private BrandAdminRepository brandAdminRepository;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public Optional<RefreshTokenStoreAdmin> findByTokenStoreAdmin(String token) {
    return refreshTokenStoreAdminRepository.findByToken(token);
  }

  public Optional<RefreshTokenBrandAdmin> findByTokenBrandAdmin(String token) {
    return refreshTokenBrandAdminRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(String Email) {
    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setUser(userRepository.findByEmail(Email).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshTokenStoreAdmin createStoreAdminRefreshToken(String email) {
    RefreshTokenStoreAdmin refreshToken = new RefreshTokenStoreAdmin();

    refreshToken.setStoreAdmin(storeAdminRepository.findByEmail(email).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenStoreAdminRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshTokenBrandAdmin createBrandAdminRefreshToken(String email) {
    RefreshTokenBrandAdmin refreshToken = new RefreshTokenBrandAdmin();

    refreshToken.setBrandAdmin(brandAdminRepository.findByEmail(email).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenBrandAdminRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  public RefreshTokenStoreAdmin verifyExpirationStoreAdmin(RefreshTokenStoreAdmin token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenStoreAdminRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  public RefreshTokenBrandAdmin verifyExpirationBrandAdmin(RefreshTokenBrandAdmin token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenBrandAdminRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByEmail(String email) {
    int result = 0;
    if(userRepository.existsByEmail(email)){
      result = refreshTokenRepository.deleteByUser(userRepository.findByEmail(email).get());
    }else if(storeAdminRepository.existsByEmail(email)){
      result = refreshTokenStoreAdminRepository.deleteByStoreAdmin(storeAdminRepository.findByEmail(email).get());
    }else if(brandAdminRepository.existsByEmail(email)){
      result = refreshTokenBrandAdminRepository.deleteByBrandAdmin(brandAdminRepository.findByEmail(email).get());
    }

    return result;
  }
}
