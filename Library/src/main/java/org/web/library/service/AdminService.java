package org.web.library.service;

import org.web.library.dto.AdminDto;
import org.web.library.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);
    Admin save(AdminDto adminDto);
}
