package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.domain.entity.StoreAdmin;
import com.ssafy.cstars.domain.repository.StoreAdminRepository;

import com.ssafy.cstars.service.StoreAdminDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreAdminDetailsServiceImpl implements UserDetailsService{
    @Autowired
    StoreAdminRepository storeAdminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        StoreAdmin storeAdmin = storeAdminRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
        
        return StoreAdminDetailsImpl.build(storeAdmin);
    }
}
