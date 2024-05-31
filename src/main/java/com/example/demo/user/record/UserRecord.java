package com.example.demo.user.record;

import com.example.demo.user.entity.User;

import java.sql.Timestamp;

public record UserRecord(int id, String loginId, String password, String name, int role, Timestamp createdAt, Timestamp updatedAt) {
    public User toUser() {
        return new User(this.id(), this.name(), this.loginId());
    }
}
