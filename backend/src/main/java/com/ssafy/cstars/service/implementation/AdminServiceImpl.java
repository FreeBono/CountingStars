package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.domain.entity.Admin;
import com.ssafy.cstars.domain.repository.AdminRepository;
import com.ssafy.cstars.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin getAdmin(Long adminId) {
        return adminRepository.getById(adminId);
    }
}
