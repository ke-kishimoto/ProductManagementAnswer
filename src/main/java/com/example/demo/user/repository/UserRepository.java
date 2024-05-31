package com.example.demo.user.repository;

import com.example.demo.user.record.UserRecord;

public interface UserRepository {

    UserRecord login(String loginId, String password);
}
