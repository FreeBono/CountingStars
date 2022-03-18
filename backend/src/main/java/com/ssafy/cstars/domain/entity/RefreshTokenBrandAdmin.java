package com.ssafy.cstars.domain.entity;

import java.time.Instant;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "refreshtokenbrandadmin")
@Data
@AllArgsConstructor
public class RefreshTokenBrandAdmin {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne
  @JoinColumn(name = "email", referencedColumnName = "email")
  private BrandAdmin brandAdmin;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private Instant expiryDate;

  public RefreshTokenBrandAdmin() {
  }

}
