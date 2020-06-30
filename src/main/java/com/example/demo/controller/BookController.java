package com.example.demo.controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.ReturnEntity.ReturnBookEntity;
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

    private static Logger log = LogManager.getLogger(BookController.class);
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @GetMapping("/all")
    public Object getAllTitle() {
        return new ResponseEntity(bService.getAllTitile(), HttpStatus.OK);
    }

    @GetMapping("/title/{search}")
    @CrossOrigin
    public Object getBookByTitle(@PathVariable(value = "search") String title) {
        String title2 = title.trim();
        if (title2 == null || title2.isEmpty())
            return new ResponseEntity("Search can't be empty", HttpStatus.NOT_ACCEPTABLE);
        try {
            List<BookEntity> books = bService.getMangaByTitle(title2);
            if (books == null)
                return new ResponseEntity("Can't find the manga", HttpStatus.NOT_FOUND);
            return new ResponseEntity(books, HttpStatus.OK);

        } catch (Exception e) {

        }
        return new ResponseEntity("Exception occured", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/hottestManga")
    @CrossOrigin
    public ResponseEntity<String> getTop10BookByRateValue() {
        try {
            List<BookEntity> books = bService.getHottestManga();
            return new ResponseEntity(books, HttpStatus.OK);
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
            List<BookEntity> books = bService.getBookByGenres(listNum,genreID);
            GenresEntity genre = gService.findGenreById((long)genreID);
            result.put("genreFind",genre.getGenre());
            result.put("books",gson.toJson(books));
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity("Access denied", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{bookID}")
    @CrossOrigin
    public Object getAllChapterByMangaID(@PathVariable("bookID") String bookID) {
        String bookID2 = bookID.trim();
        if (bookID2 == null || bookID2.isEmpty())
            return new ResponseEntity("Book id can't be empty",
                    HttpStatus.NOT_ACCEPTABLE);
        try {
            BookEntity returnData = bService.getByID(bookID2);
            if (returnData == null)
                return new ResponseEntity("The manga doesn't exist",
                        HttpStatus.NOT_FOUND);
            return new ResponseEntity(returnData, HttpStatus.OK);

        } catch (Exception e) {

        }
        return new ResponseEntity("Exception occured", HttpStatus.NOT_FOUND);
    }
}
