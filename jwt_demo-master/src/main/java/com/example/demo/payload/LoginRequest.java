package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {//tạo request khi dùng postman login
    private String username;
    private String password;
}
