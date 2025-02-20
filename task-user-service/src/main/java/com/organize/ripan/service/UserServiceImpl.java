package com.organize.ripan.service;

import com.organize.ripan.config.JwtProvider;
import com.organize.ripan.model.User;
import com.organize.ripan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired private UserRepository userRepository;

    @Override
    public User getUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
