package com.fitness.fitness.controller;

import com.fitness.fitness.dto.RegisterRequest;
import com.fitness.fitness.dto.UserResponse;
import com.fitness.fitness.service.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request){
       return ResponseEntity.ok(userServices.register(request));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userServices.deleteUser(id);
    }
}
