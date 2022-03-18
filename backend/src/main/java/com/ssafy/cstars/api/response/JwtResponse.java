package com.ssafy.cstars.api.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private Long id;
	private String username;
	private String email;
	private String role;
	private String tel;
	private double eth;
	private String address;
	private String name;
	private String store;
	private String wallet;
	private List<String> roles;

	public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, String role,List<String> roles) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
		this.roles = roles;
	}
	//user
	public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, double eth, String tel, String address, String role){
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.email = email;
		this.tel =tel;
		this.eth = eth;
		this.address = address;
		this.role = role;

	}
	//store
	public JwtResponse(String accessToken, String refreshToken, Long id, String email, String store, String wallet, String role){
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.email = email;
		this.store = store;
		this.wallet = wallet;
		this.role = role;

	}
	// brand
	public JwtResponse(String accessToken, String refreshToken, Long id, String email, String name, String wallet, String role, List<String> roles){
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.email = email;
		this.name = name;
		this.wallet = wallet;
		this.role = role;
		this.roles = roles;

	}
	// admin
	public JwtResponse(String email, String role){
		this.email = email;
		this.role = role;
	}

// 	public String getAccessToken() {
// 		return token;
// 	}

// 	public void setAccessToken(String accessToken) {
// 		this.token = accessToken;
// 	}

// 	public String getTokenType() {
// 		return type;
// 	}

// 	public void setTokenType(String tokenType) {
// 		this.type = tokenType;
// 	}

// 	public Long getId() {
// 		return id;
// 	}

// 	public void setId(Long id) {
// 		this.id = id;
// 	}

// 	public String getEmail() {
// 		return email;
// 	}

// 	public void setEmail(String email) {
// 		this.email = email;
// 	}

// 	public String getUsername() {
// 		return username;
// 	}

// 	public void setUsername(String username) {
// 		this.username = username;
// 	}

// 	public List<String> getRoles() {
// 		return roles;
// 	}

//   public String getRefreshToken() {
//     return refreshToken;
//   }

//   public void setRefreshToken(String refreshToken) {
//     this.refreshToken = refreshToken;
//   }
}
