package com.ssafy.cstars.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.cstars.domain.entity.StoreAdmin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreAdminDetailsImpl implements UserDetails{
    
    private Long id;

    private String email;

    private String store;

    private String wallet;

    private String role;

    @JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

    public StoreAdminDetailsImpl(Long id, String email, String password, String store, String wallet, String role, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.email = email;
        this.password = password;
        this.store = store;
        this.wallet = wallet;
        this.role = role;
        this.authorities = authorities;
    }

    public static StoreAdminDetailsImpl build(StoreAdmin storeAdmin){
        List<GrantedAuthority> authorities = storeAdmin.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

        return new StoreAdminDetailsImpl(
                storeAdmin.getId(),
                storeAdmin.getEmail(),
                storeAdmin.getPassword(),
                storeAdmin.getStore(),
                storeAdmin.getWallet(),
                storeAdmin.getRole(),
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
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
            StoreAdminDetailsImpl storeAdmin = (StoreAdminDetailsImpl) o;
		return Objects.equals(id, storeAdmin.id);
	}
}
