package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/user")
@RolesAllowed(value = "[ROLE_USER]")
public class UserController {
    @GetMapping("/test")
    public ResponseEntity<?> userPage(){
        return ResponseEntity.ok("Trang user");
    }
}
