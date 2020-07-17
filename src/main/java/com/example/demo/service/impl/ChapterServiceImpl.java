package com.example.demo.service.impl;

import com.example.demo.Entity.ChapterEntity;
import com.example.demo.Repository.ChapterReposiory;
import com.example.demo.service.ChapterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    private static Logger log = LogManager.getLogger(ChapterServiceImpl.class);
    @Autowired
    private ChapterReposiory repo;

    @Override
    public List<ChapterEntity> getAllChapterByBookID(String bookID) {
        return repo.getChaptersByBookID(bookID);
    }
}
