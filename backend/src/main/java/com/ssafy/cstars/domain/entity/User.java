package com.ssafy.cstars.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "email") 
		})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	private String role;

	//private String role;

	private String tel;

    private double eth;

    private String address;
	
	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(	name = "email_roles", 
	// 			joinColumns = @JoinColumn(name = "email"), 
	// 			inverseJoinColumns = @JoinColumn(name = "role_id"))
	
	@Transient
	private Set<Role> roles = new HashSet<>();

	// public User() {
	// }

	public User(String username, String email, String password, String tel, String address, String role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.address = address;
		this.role = role;
	}
}
