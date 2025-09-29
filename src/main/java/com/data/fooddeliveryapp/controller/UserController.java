package com.data.fooddeliveryapp.controller;


import com.data.fooddeliveryapp.io.UserRequest;
import com.data.fooddeliveryapp.io.UserResponse;
import com.data.fooddeliveryapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRequest request){
             return userService.registerUser(request);
    }
}
