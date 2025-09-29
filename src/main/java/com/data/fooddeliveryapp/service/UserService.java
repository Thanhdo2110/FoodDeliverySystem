package com.data.fooddeliveryapp.service;

import com.data.fooddeliveryapp.io.UserRequest;
import com.data.fooddeliveryapp.io.UserResponse;

public interface UserService {

  UserResponse registerUser(UserRequest request);

  String findByUserId();
}
