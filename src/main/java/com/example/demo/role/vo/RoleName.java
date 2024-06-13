package com.example.demo.role.vo;

import com.example.demo.role.exception.IllegalRoleException;

public record RoleName(String name) {
    public String securityRoleName() {
        if(!("ADMIN".equals(name) || "USER".equals(name))) {
            throw new IllegalRoleException("権限名が不正です");
        }
        return "ROLE_" + this.name;
    }
}
