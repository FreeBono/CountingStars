package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.domain.entity.BrandAdmin;
import com.ssafy.cstars.domain.repository.BrandAdminRepository;

import com.ssafy.cstars.service.BrandAdminDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrandAdminDetailsServiceImpl implements UserDetailsService{
    @Autowired
    BrandAdminRepository brandAdminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BrandAdmin brandAdmin = brandAdminRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
        
        return BrandAdminDetailsImpl.build(brandAdmin);
    }
}
