package com.organize.ripan.service;

import com.organize.ripan.model.User;

import java.util.List;

public interface UserService {
    User getUserByJwt(String jwt);

    List<User> getAllUsers();
}
