package com.example.demo.user.service;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 自前でのログイン処理
 */

@Service
public class RegularUserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String loginId, String password) {
        return userRepository.login(loginId, password).toUser();
    }
}
