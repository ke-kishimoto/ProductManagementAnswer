package com.example.demo.category.record;

import java.sql.Timestamp;

public record CategoryRecord(int id, String name, Timestamp createdAt, Timestamp updatedAt) {
}
