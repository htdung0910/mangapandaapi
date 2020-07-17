package com.example.demo.controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.ServiceInterface.*;
import com.example.demo.service.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/genre")
public class GenresController {
    @Autowired
    private GenresServiceInterface gService;

    @Autowired
    private BookServiceInterface bService;

    private static Logger log = LogManager.getLogger(GenresController.class);
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    @GetMapping
    @CrossOrigin
    public Object getAllGenres() {
        return new ResponseEntity(gService.getAllGenres(), HttpStatus.OK);
    }

    @GetMapping("/{genreID}/{quantity}")
    @CrossOrigin
    public Object getBookByGenre(@PathVariable("genreID") Integer genreID, @PathVariable("quantity") Integer quantity) {
        try {
            List<BookEntity> books = bService.getBookByGenres(quantity,genreID);

            if(books == null)
                return new ResponseEntity("The list is null",HttpStatus.BAD_REQUEST);

            Map<String, Object> result = new HashMap<>();
            GenresEntity genre = gService.findGenreById((long)genreID);
            result.put("genreFind",genre.getGenre());
            result.put("books",books);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Exception occured", HttpStatus.BAD_REQUEST);
    }
}
