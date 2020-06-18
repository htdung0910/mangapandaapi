package com.example.demo.service;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.ChapterEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.Entity.ImageEntity;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.ChapterReposiory;
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
                returnData.add(new ReturnBookEntity(x.getBookID(), x.getTitle(), x.getThumnailpath(), (List<GenresEntity>) x.getGenres()));
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
    public List<String> getImageByChapterID(String chapterID) {
        ChapterEntity chapter = null;
        try {
            chapter = cRepo.getAllChapterImageByChapterID(chapterID);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (chapter == null)
            return null;
        List<ImageEntity> tempData = chapter.getImages();
        List<String> returnData = new ArrayList<String>(tempData.size());
        tempData.stream().forEach(x -> {
            returnData.add(x.getUrl());
        });
        return returnData;
    }

    @Override
    public List<ReturnBookEntity> getBookByGenres(long listNum, long genreID) {
        List<BookEntity> book = repo.getBookByGenres(listNum,genreID);
        List<ReturnBookEntity> returnData = new ArrayList<>(book.size());
        book.stream().forEach(x -> {
            returnData.add(new ReturnBookEntity(x.getBookID(),x.getTitle(),x.getThumnailpath(), (List<GenresEntity>) x.getGenres()));
        });
        return returnData;
    }


}
