package com.ssafy.cstars.domain.entity;

import java.time.Instant;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "refreshtokenstoreadmin")
@Data
@AllArgsConstructor
public class RefreshTokenStoreAdmin {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne
  @JoinColumn(name = "email", referencedColumnName = "email")
  private StoreAdmin storeAdmin;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private Instant expiryDate;

  public RefreshTokenStoreAdmin() {
  }

}
