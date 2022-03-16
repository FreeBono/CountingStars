package com.ssafy.cstars.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "brand_admin", 
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandAdmin {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank
    private String email;

    private String password;

    private String name;

    private String wallet;

    private String role;

    // @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(	name = "email_roles", 
	// 			joinColumns = @JoinColumn(name = "email"), 
	// 			inverseJoinColumns = @JoinColumn(name = "role_id"))

    @Transient
    private Set<Role> roles = new HashSet<>();

    public BrandAdmin(String email, String password, String name, String wallet, String role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.wallet = wallet;
        this.role = role;
    }
}
