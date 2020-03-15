package com.eteration.site.Services;

import com.eteration.site.Model.Role;
import com.eteration.site.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public void save(Role adminRole) {
        roleRepository.save(adminRole);
    }
}
