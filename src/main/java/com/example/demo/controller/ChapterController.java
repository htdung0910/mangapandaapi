package com.example.demo.controller;

import com.example.demo.Entity.ChapterEntity;
import com.example.demo.Entity.ReturnEntity.ReturnChapterEntity;
import com.example.demo.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private BookServiceImpl service;

    @GetMapping("/image/{chapterID}")
    @CrossOrigin
    /**
     * Lấy các images của chapter đó,dựa vào chapterID
     */
    public Object getChapterImagesByChapterID(@PathVariable("chapterID") String chapterID) {
        String chapterID2 = chapterID.trim();
        if (chapterID2 == null || chapterID2.isEmpty())
            return new ResponseEntity("Chapter id can't be empty",
                    HttpStatus.NOT_ACCEPTABLE);
        try {
            List<String> returnData = service.getImagesByChapterID(chapterID2);
            if (returnData == null)
                return new ResponseEntity("The chapter doesn't exist",
                        HttpStatus.NOT_FOUND);
            return new ResponseEntity(returnData, HttpStatus.OK);

        } catch (Exception e) {
        }
        return new ResponseEntity("Exception occured", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{bookID}")
    @CrossOrigin
    /**
     * Trả về các chapter của manga đó theo mangaID
     */
    public Object getChaptersByBookID(@PathVariable("bookID") String bookID) {
        List<ChapterEntity> tempData = service.getChaptersByBookID(bookID);
        if(tempData == null){
            return new ResponseEntity(null, HttpStatus.OK);
        }
        List<ReturnChapterEntity> returnData = new ArrayList<ReturnChapterEntity>(tempData.size());
        tempData.stream().forEach(x -> {
            returnData.add(new ReturnChapterEntity(x.getId(), x.getChapterName(), x.getDate()));
        });
        return new ResponseEntity(returnData, HttpStatus.OK);
    }

}
