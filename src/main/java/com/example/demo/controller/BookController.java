package com.example.demo.controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.service.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/manga")
public class BookController {
    private BookService bService;
    private GenresService gService;

    private static Logger log = LogManager.getLogger(BookController.class);
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @GetMapping("/title/{search}")
    @CrossOrigin
    public ResponseEntity<String> getBookByGenre(@PathVariable(value = "search") String title ) {
        try {
            bService = new BookService();
            bService.searchMangaByTitle(title);
            List<BookEntity> books = bService.getListBook();
            return new ResponseEntity(gson.toJson(books), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/hottestManga")
    @CrossOrigin
    public ResponseEntity<String> getTop10BookByRateValue() {
        try {
            bService = new BookService();
            bService.getTop10BookByRateValue();
            List<BookEntity> books = bService.getListBook();
            return new ResponseEntity(gson.toJson(books), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/genre/{genreID}/{listNum}")
    @CrossOrigin
    public ResponseEntity<String> getBookByGenre(@PathVariable(value = "genreID") long genreID,@PathVariable(value = "listNum") long listNum, @RequestParam(value = "stopLoadGenre") int getGenre) {
        try {
            Map<String, String> result = new HashMap<>();
            bService = new BookService();
            gService = new GenresService();
            bService.getBookByGenres(listNum,genreID);
            List<BookEntity> books = bService.getListBook();
            if(getGenre != 0 ){
                for (BookEntity b : books){
                    gService.searchGenreOfManga(b.getBookID());
                    b.setGenres(gService.getListGenre());
                }
            }
            GenresEntity genre = gService.findGenreById(genreID);
            result.put("genre",genre.getGenre());
            result.put("books",gson.toJson(books));
            return new ResponseEntity(gson.toJson(result), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
    }

}
