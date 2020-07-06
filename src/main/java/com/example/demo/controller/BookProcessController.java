package com.example.demo.controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.BookProcessEntity;
import com.example.demo.ServiceInterface.BookServiceInterface;
import com.example.demo.ServiceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@RestController
@RequestMapping("/userbookinfo")
public class BookProcessController {
    @Autowired
    private BookServiceInterface bService;

    @Autowired
    private UserServiceInterface uService;

    @PostMapping("/{bookID}/{rateValue}")
    @CrossOrigin
    public void rateChapterByMangaID(@PathVariable("bookID") String bookID, @PathVariable("rateValue") float rateValue,
                                     @RequestParam("username") String username, @RequestParam("password") String password
    ) {
        DecimalFormat df = new DecimalFormat("0.00");
        if(uService.login(username,password) != null){
            BookEntity bEntity = bService.getByID(bookID);


            String newRateValue = df.format((bEntity.getRatingvalue() * bEntity.getRatingcount() + rateValue)/(bEntity.getRatingcount() + 1));
            bEntity.setRatingvalue(Float.valueOf(newRateValue));
            bEntity.setRatingcount(bEntity.getRatingcount() + 1);

            BookProcessEntity bpEntity = bService.getInfoUserBetweenBook(username,bookID);
            if(bpEntity == null){
                bpEntity = new BookProcessEntity();
                bpEntity.setUsername(username);
                bpEntity.setBookID(bookID);
            }
            bpEntity.setRate(rateValue);
            bService.saveBookProcessEntity(bpEntity);
            bService.saveBookEntity(bEntity);
        }
    }

    @PostMapping("/{bookID}")
    @CrossOrigin
    public Object getRateByBookIDAndUser(@PathVariable("bookID") String bookID,
                                         @RequestParam("username") String username,@RequestParam("password") String password
    ) {
        DecimalFormat df = new DecimalFormat("0.00");
        if(uService.login(username,password) != null){
            BookProcessEntity bpEntity = bService.getInfoUserBetweenBook(username,bookID);
            return new ResponseEntity(bpEntity, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{bookID}/follow")
    @CrossOrigin
    public void setUserFollow(@PathVariable("bookID") String bookID,
                              @RequestParam("username") String username,@RequestParam("password") String password
    ) {
        if(uService.login(username,password) != null){
            BookProcessEntity bpEntity = bService.getInfoUserBetweenBook(username,bookID);
            bpEntity.setFollow(!bpEntity.isFollow());
            bService.saveBookProcessEntity(bpEntity);
        }
    }
}
