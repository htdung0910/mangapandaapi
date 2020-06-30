package com.example.demo.controller;

import com.example.demo.Entity.ChapterEntity;
import com.example.demo.Entity.ImageEntity;
import com.example.demo.ReturnEntity.ReturnChapterEntity;
import com.example.demo.service.BookService;
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
    private BookService service;

    @GetMapping("/image/{chapterID}")
    @CrossOrigin
    /**
     * Lấy các images của chapter đó,dựa vào chapterID
     */
    public Object getChapterImagesByChapterID(@PathVariable("chapterID") String chapterID) {
        /*String chapterID2 = chapterID.trim();
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
        return new ResponseEntity("Exception occured", HttpStatus.NOT_FOUND);*/
        List<String> returnData = new ArrayList<>();
        returnData.add("https://i2.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132275.jpg");
        returnData.add("https://i2.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132277.jpg");
        returnData.add("https://i6.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132279.jpg");
        returnData.add("https://i6.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132281.jpg");
        returnData.add("https://i8.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132283.jpg");
        returnData.add("https://i6.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132285.jpg");
        returnData.add("https://i4.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132287.jpg");
        returnData.add("https://i10.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132289.jpg");
        returnData.add("https://i6.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132291.jpg");
        returnData.add("https://i4.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132293.jpg");
        returnData.add("https://i5.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132259.jpg");
        returnData.add("https://i2.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132295.jpg");
        returnData.add("https://i4.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132297.jpg");
        returnData.add("https://i10.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132299.jpg");
        returnData.add("https://i2.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132301.jpg");
        returnData.add("https://i4.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132303.jpg");
        returnData.add("https://i6.mangapanda.com/valkyria-chronicles/10/valkyria-chronicles-4132305.jpg");
        return returnData;
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
