package com.organize.ripan.controller;

import com.organize.ripan.config.JwtProvider;
import com.organize.ripan.exception.UserException;
import com.organize.ripan.model.User;
import com.organize.ripan.repository.UserRepository;
import com.organize.ripan.request.LoginRequest;
import com.organize.ripan.request.RegistrationRequest;
import com.organize.ripan.response.RegisterUserResponse;
import com.organize.ripan.response.LoginResponse;
import com.organize.ripan.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private CustomUserDetailsServiceImpl customeUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegistrationRequest user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        String role = user.getRole();

        User isUserExists = userRepository.findByEmail(email);
        if(isUserExists != null) throw new UserException("User already exists");

        // Create a new User
        User createdUser = new User();
        createdUser.setName(name);
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password)); // Make Sure, encode the password before save it to DB
        createdUser.setRole(role);

        User savedUser = userRepository.save(createdUser); // save the user

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication); // generate the token

        var response = new RegisterUserResponse(token, "User Registered Successfully!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        var response = new LoginResponse(token, "Login Successful!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid Credentials!");
        }
        if(! passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
