package com.example.demo.service;

import com.example.demo.Entity.ChapterEntity;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.ChapterReposiory;
import com.example.demo.ServiceInterface.ChapterServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService implements ChapterServiceInterface {
    private static Logger log = LogManager.getLogger(ChapterService.class);
    @Autowired
    private ChapterReposiory repo;

    @Override
    public List<ChapterEntity> getAllChapterByBookID(String bookID) {
        return repo.getChaptersByBookID(bookID);
    }
}
