package com.example.demo.controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.service.GenresService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenresController {
    private GenresService gService;

    private static Logger log = LogManager.getLogger(UserController.class);
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<String> getAllGenres() {
        try {
            gService = new GenresService();
            gService.getAllGenres();
            List<GenresEntity> genres = gService.getListGenre();
            return new ResponseEntity(gson.toJson(genres), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
    }
}
