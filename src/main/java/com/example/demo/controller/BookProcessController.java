package com.example.demo.controller;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.BookProcessEntity;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@RestController
@RequestMapping("/userbookinfo")
public class BookProcessController {
    @Autowired
    private BookService bService;

    @Autowired
    private UserService uService;

    private static Logger log = LogManager.getLogger(BookProcessController.class);

    @PostMapping("/{bookID}/{rateValue}")
    @CrossOrigin
    public Object rateChapterByMangaID(@PathVariable("bookID") String bookID, @PathVariable("rateValue") float rateValue,
                                     @RequestParam("username") String username, @RequestParam("password") String password
    ) {
        try{
            DecimalFormat df = new DecimalFormat("0.00");
            if(uService.login(username,password) != null){
                BookEntity bEntity = bService.getByID(bookID);


                String newRateValue = df.format((bEntity.getRatingvalue() * bEntity.getRatingcount() + rateValue)/(bEntity.getRatingcount() + 1));
                bEntity.setRatingvalue(Float.valueOf(newRateValue));
                bEntity.setRatingcount(bEntity.getRatingcount() + 1);

                BookProcessEntity bpEntity = bService.getInfoUserBetweenBook(username,bookID);
                if(bpEntity == null){
                    bpEntity = new BookProcessEntity(username,bookID, (float) 0.0,false,false);
                }
                bpEntity.setRate(rateValue);
                bService.saveBookProcessEntity(bpEntity);
                bService.saveBookEntity(bEntity);
                return new ResponseEntity("", HttpStatus.OK);
            }
            return new ResponseEntity("Cannot indentified you", HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity("Error of the Internet", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{bookID}")
    @CrossOrigin
    public Object getRateByBookIDAndUser(@PathVariable("bookID") String bookID,
                                         @RequestParam("username") String username,@RequestParam("password") String password
    ) {
        try{
            if(uService.login(username,password) != null){
                BookProcessEntity bpEntity = bService.getInfoUserBetweenBook(username,bookID);
                if(bpEntity != null){
                    if(bpEntity.getIsFollow() == null){
                        bpEntity.setIsFollow(false);
                    }
                    if(bpEntity.getIsUpload() == null){
                        bpEntity.setIsUpload(false);
                    }
                    if(bpEntity.getRate() == null){
                        bpEntity.setRate((float) 0.0);
                    }
                    return new ResponseEntity(bpEntity, HttpStatus.OK);
                }
                bpEntity = new BookProcessEntity(username, bookID, (float) 0.0, false,false);
                return new ResponseEntity(bpEntity, HttpStatus.OK);
            }
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{bookID}/follow")
    @CrossOrigin
    public Object setUserFollow(@PathVariable("bookID") String bookID,
                              @RequestParam("username") String username,@RequestParam("password") String password
    ) {
        try{
            if(uService.login(username,password) != null){
                BookProcessEntity bpEntity = bService.getInfoUserBetweenBook(username,bookID);
                if(bpEntity == null){
                    bpEntity = new BookProcessEntity(username,bookID, (float) 0.0, false,false);
                }
                bpEntity.setIsFollow(!bpEntity.getIsFollow());
                bService.saveBookProcessEntity(bpEntity);
                return new ResponseEntity(bpEntity.getIsFollow(), HttpStatus.OK);
            }
            return new ResponseEntity(false, HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }
}
