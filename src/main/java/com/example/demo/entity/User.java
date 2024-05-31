package com.example.demo.entity;

import java.io.Serializable;

import lombok.Data;

public record User(int id, String name, String loginId) implements Serializable {
}
