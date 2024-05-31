package com.example.demo.record;

import java.sql.Timestamp;

public record CategoryRecord(int id, String name, Timestamp createdAt, Timestamp updatedAt) {
}
