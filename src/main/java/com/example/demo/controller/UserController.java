package com.example.demo.controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.ReturnEntity.ReturnUserEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService uService;
    @Autowired
    private BookService bService;

    private static Logger log = LogManager.getLogger(UserController.class);
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity login(@RequestParam("username") String username,@RequestParam("password") String password) {
        try {
            UserEntity user = uService.login(username, password);
            if (user != null) {
                if(user.getAdmin() == 1){
                    return new ResponseEntity("", HttpStatus.NOT_ACCEPTABLE);
                }
                return new ResponseEntity(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Access denied", HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<String> register(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam(value = "fullname", required = false) String fullname) {

        try {
            if(fullname == null){
                fullname = "";
            }
            UserEntity user = new UserEntity(username.trim(), password.trim(), fullname.trim());
            if (uService.addUser(user)) {
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
            UserEntity user = uService.login(username, password);
            if (user != null) {
                return new ResponseEntity(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Access denied", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/update")
    @CrossOrigin
    public Object update(   @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam(value = "fullname", required = false) String fullname)

    {
        try {
            if(fullname == null){
                fullname = "";
            }
            UserEntity user = new UserEntity(username.trim(), password.trim(), fullname.trim());
            if (uService.updateAUser(user)) {
                return new ResponseEntity("OK", HttpStatus.OK);
            }
            return new ResponseEntity("Update denied", HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Update denied", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/follow")
    @CrossOrigin
    public ResponseEntity getBookFollowByUser(@RequestParam("username") String username) {

        try {
            if(uService.checkUsername(username) != null){
                List<BookEntity> result = bService.getFollow(username);
                return new ResponseEntity(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Access denied", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/topPost")
    @CrossOrigin
    public ResponseEntity getTopUserByBookRate() {
        List<ReturnUserEntity> result = null;
        try {
            result = bService.getTopUserPostBook();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
