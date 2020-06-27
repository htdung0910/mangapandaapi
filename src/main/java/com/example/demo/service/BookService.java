package com.example.demo.service;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.ChapterEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.Entity.ImageEntity;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.ChapterReposiory;
import com.example.demo.Repository.ImageRepository;
import com.example.demo.ReturnEntity.ReturnBookEntity;
import com.example.demo.ServiceInterface.BookServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {
    private static Logger log = LogManager.getLogger(BookService.class);
    @Autowired
    private BookRepository repo;

    @Autowired
    private ChapterReposiory cRepo;

    @Autowired
    private ImageRepository iRepo;

    @Override
    public List<String> getAllTitile() {
        return repo.getAllTitle();
    }

    @Override
    public List<ReturnBookEntity> getMangaByTitle(String title) {
        if (title.isEmpty() || title == null) {
            return null;
        }
        try {
            List<BookEntity> temp = repo.getBookByTitle(title);
            List<ReturnBookEntity> returnData = new ArrayList<ReturnBookEntity>(temp.size());
            temp.stream().forEach(x -> {
                returnData.add(new ReturnBookEntity(x.getBookID(), x.getTitle(), x.getThumnailpath()));
            });
            return returnData;

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ReturnBookEntity> getHottestManga() {
        List<BookEntity> temp = null;
        try {
            temp = repo.findTop10ByOrderByRatingValueDesc();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        List<ReturnBookEntity> returnData = new ArrayStack<ReturnBookEntity>(temp.size());
        temp.stream().forEach(x -> {
            returnData.add(new ReturnBookEntity(x.getBookID(), x.getTitle(), x.getThumnailpath()));
        });
        return returnData;
    }

    @Override
    public BookEntity getByID(String id) {
        BookEntity returnData = null;
        try {
            if (repo.existsById(id))
                returnData = repo.findById(id).orElse(null);
            return returnData;

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return returnData;
    }

    @Override
    public List<String> getImagesByChapterID(String chapterID) {
        List<String> returnData = null;
        try {
            if(!cRepo.existsById(chapterID))
                return null;
            returnData = iRepo.getImagesByChapterID(chapterID);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return returnData;
    }

    @Override
    public List<ChapterEntity> getChaptersByBookID(String bookID) {
        BookEntity manga = getByID(bookID);
        if (manga == null)
            return null;
        List<ChapterEntity> tempData = null;
        try {
            tempData = cRepo.getChaptersByBookID(bookID);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return tempData;
    }

    @Override
    public List<ReturnBookEntity> getBookByGenres(long listNum, long genreID) {
        List<BookEntity> book = repo.getBookByGenres(listNum,genreID);
        List<ReturnBookEntity> returnData = new ArrayList<>(book.size());
        book.stream().forEach(x -> {
            returnData.add(new ReturnBookEntity(x.getBookID(),x.getTitle(),x.getThumnailpath()));
        });
        return returnData;
    }


}
