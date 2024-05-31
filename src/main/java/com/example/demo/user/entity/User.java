package com.example.demo.user.entity;

import java.io.Serializable;

public record User(int id, String name, String loginId) implements Serializable {
}
