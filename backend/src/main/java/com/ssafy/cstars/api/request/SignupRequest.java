package com.ssafy.cstars.api.request;

import java.util.Set;

import javax.validation.constraints.*;

import com.ssafy.cstars.domain.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SignupRequest extends BaseEntity{
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 60)
    private String password;

    @Size(max = 11)
    private String tel;

    private double eth;

    private String address;

    private String grade;

  
    // public String getUsername() {
    //     return username;
    // }
 
    // public void setUsername(String username) {
    //     this.username = username;
    // }
 
    // public String getEmail() {
    //     return email;
    // }
 
    // public void setEmail(String email) {
    //     this.email = email;
    // }
 
    // public String getPassword() {
    //     return password;
    // }
 
    // public void setPassword(String password) {
    //     this.password = password;
    // }
    
    // public Set<String> getRole() {
    //   return this.role;
    // }
    
    // public void setRole(Set<String> role) {
    //   this.role = role;
    // }
}
