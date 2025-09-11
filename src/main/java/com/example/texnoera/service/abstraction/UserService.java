package com.example.texnoera.service.abstraction;

import com.example.texnoera.dto.request.CreateUserRequest;
import com.example.texnoera.dto.request.UpdateUserRequest;
import com.example.texnoera.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(CreateUserRequest request);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    UserResponse findUserById(Long id);

    void deleteUser(Long id);

    List<UserResponse> findAllUsers();
}
