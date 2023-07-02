package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.model.Role;
import com.namnd.bookingbe.repository.RoleRepository;
import com.namnd.bookingbe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void flush() {
        roleRepository.flush();
    }
}
