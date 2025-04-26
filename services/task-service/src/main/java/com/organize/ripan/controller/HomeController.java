package com.organize.ripan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/hello")
    public ResponseEntity<String> getMessage(){
        return new ResponseEntity<>("Hello, from Home Controller", HttpStatus.OK);
    }
}
