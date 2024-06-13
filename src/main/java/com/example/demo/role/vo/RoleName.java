package com.example.demo.role.vo;

import com.example.demo.role.exception.IllegalRoleException;
import lombok.Getter;

@Getter
public enum RoleName {
    USER("USER", "一般ユーザー"),
    ADMIN("ADMIN", "管理者");

    private final String value;
    private final String label;

    RoleName(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static RoleName fromValue(String value) {
        for (RoleName roleName : values()) {
            if (roleName.value.equals(value)) {
                return roleName;
            }
        }
        throw new IllegalRoleException("権限名が不正です");
    }

    public String getSecurityRoleName() {
        return "ROLE_" + value;
    }

}
