package com.example.demo.controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.ServiceInterface.BookServiceInterface;
import com.example.demo.ServiceInterface.UserServiceInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceInterface service;
    private static Logger log = LogManager.getLogger(UserController.class);
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestParam("username") String username,@RequestParam("password") String password) {
        try {
            UserEntity user = service.login(username, password);
            if (user != null) {
                return new ResponseEntity("matched", HttpStatus.OK);
            }
            return new ResponseEntity("Access denied", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Access denied", HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<String> register(@RequestParam("username") String username,@RequestParam("password") String password) {

        try {
            UserEntity user = new UserEntity(username.trim(), password.trim());
            if (service.addUser(user)) {
                return new ResponseEntity("OK", HttpStatus.OK);
            }
            return new ResponseEntity("Username already existed", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Register denied", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/Info")
    @CrossOrigin
    public ResponseEntity<String> getInfo(@RequestParam("username") String username,@RequestParam("password") String password) {

        try {
            UserEntity user = service.login(username, password);
            if (user != null) {
                return new ResponseEntity(user, HttpStatus.OK);
            }
            return new ResponseEntity("Access denied", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Access denied", HttpStatus.UNAUTHORIZED);
    }



}
