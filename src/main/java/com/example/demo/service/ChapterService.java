package com.example.demo.service;

import com.example.demo.Entity.ChapterEntity;

import java.util.List;

public interface ChapterService {
    /**
     * Trả về list các chapter by BookID
     * */
    List<ChapterEntity> getAllChapterByBookID(String bookID);
}
