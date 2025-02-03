package org.web.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web.library.dto.AdminDto;
import org.web.library.model.Admin;
import org.web.library.repository.AdminRepository;
import org.web.library.repository.RoleRepository;
import org.web.library.service.AdminService;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
