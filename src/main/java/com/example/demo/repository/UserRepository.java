package com.example.demo.repository;

import com.example.demo.entity.User;

public interface UserRepository {

    User login(String loginId, String password);
}
