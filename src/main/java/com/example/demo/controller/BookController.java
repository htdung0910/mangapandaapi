package com.example.demo.Controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.ServiceInterface.BookServiceInterface;
import com.example.demo.ServiceInterface.GenresServiceInterface;
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
@RequestMapping("/manga")
public class BookController {
    @Autowired
    private BookServiceInterface bService;
    @Autowired
    private GenresServiceInterface gService;

    private static Logger log = LogManager.getLogger(UserController.class);
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @GetMapping("/title/{search}")
    @CrossOrigin
    public ResponseEntity<String> getBookByGenre(@PathVariable(value = "search") String title ) {
        try {
            List<BookEntity> book = bService.findAllByTitle(title);
            return new ResponseEntity(gson.toJson(book), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/hottestManga")
    @CrossOrigin
    public ResponseEntity<String> getTop10BookByRateValue() {
        try {
            List<BookEntity> books = bService.getTop10BookByRateValue();
            /*for (BookEntity b : books) {
                List<GenresEntity> gL = new ArrayList<>();
                b.setGenres(gL);
            }*/
            return new ResponseEntity(gson.toJson(books), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/genre/{genreID}/{listNum}")
    @CrossOrigin
    public ResponseEntity<String> getBookByGenre(@PathVariable(value = "genreID") Long genreID,@PathVariable(value = "listNum") Long listNum) {
        try {
            Map<String, String> result = new HashMap<>();
            List<BookEntity> book = bService.getBookByGenres(listNum,genreID);
            GenresEntity genre = gService.findGenreById((long)genreID);
            result.put("genre",genre.getGenre());
            result.put("books",gson.toJson(book));
            return new ResponseEntity(gson.toJson(result), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Access denied", HttpStatus.UNAUTHORIZED);
    }

}
