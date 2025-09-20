package com.example.transaction.service.abstraction;

import com.example.transaction.dto.request.UserRequest;

public interface UserService {
    void test(UserRequest request);
    void createUser(UserRequest request) ;
}
