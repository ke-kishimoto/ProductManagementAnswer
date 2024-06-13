package com.example.demo.role.repository;

import com.example.demo.role.repository.record.RoleRecord;

public interface RoleRepository {
    RoleRecord findById(Integer id);
}
