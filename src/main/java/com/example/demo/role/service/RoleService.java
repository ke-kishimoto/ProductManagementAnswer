package com.example.demo.role.service;

import com.example.demo.role.entity.Role;
import com.example.demo.role.repository.RoleRepository;
import com.example.demo.role.vo.RoleId;
import com.example.demo.role.vo.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Integer id) {
        var roleRecord = roleRepository.findById(id);
        return new Role(
                new RoleId(roleRecord.id()),
                RoleName.fromValue(roleRecord.name())
        );
    }
}
