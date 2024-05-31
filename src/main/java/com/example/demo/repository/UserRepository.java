package com.example.demo.repository;

import com.example.demo.record.UserRecord;

public interface UserRepository {

    UserRecord login(String loginId, String password);
}
