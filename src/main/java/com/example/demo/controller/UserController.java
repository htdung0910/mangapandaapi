package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService uService;
    private static Logger log = LogManager.getLogger(UserController.class);
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            uService = new UserService();
            boolean checkLogin = uService.login(username, password);
            if (checkLogin) {
                return new ResponseEntity(gson.toJson("Ok man"), HttpStatus.OK);
            }
            return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
    }

}
