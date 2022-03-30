package com.ssafy.cstars.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.cstars.domain.entity.BrandAdmin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandAdminDetailsImpl implements UserDetails{
    
    private Long id;

    private String email;

    private String name;

    private String wallet;

    private String role;

    @JsonIgnore
	private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public BrandAdminDetailsImpl(Long id, String email, String name, String wallet, String role, String password, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.email = email;
        this.name = name;
        this.wallet = wallet;
        this.role = role;
        this.password = password;
        this.authorities = authorities;
    }

    public static BrandAdminDetailsImpl build(BrandAdmin brandAdmin){
        List<GrantedAuthority> authorities = brandAdmin.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

        return new BrandAdminDetailsImpl(
                brandAdmin.getId(),
                brandAdmin.getEmail(),
                brandAdmin.getName(),
                brandAdmin.getWallet(),
                brandAdmin.getRole(),
                brandAdmin.getPassword(),
                authorities);
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

    @Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
	public String getPassword() {
		return password;
	}

    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
            BrandAdminDetailsImpl brandAdmin = (BrandAdminDetailsImpl) o;
		return Objects.equals(id, brandAdmin.id);
	}

}
