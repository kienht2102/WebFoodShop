package org.web.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.web.library.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
