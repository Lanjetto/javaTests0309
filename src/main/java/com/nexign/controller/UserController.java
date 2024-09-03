package com.nexign.controller;

import com.nexign.entity.UserEntity;
import com.nexign.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUsers(@RequestBody UserEntity entity) {
        userService.addUsers(entity);
        return ResponseEntity.ok(entity);
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> userList =
                userService.getUserList();
        return ResponseEntity.ok(userList);
    }
}
