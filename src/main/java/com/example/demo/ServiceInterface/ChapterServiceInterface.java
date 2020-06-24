package com.example.demo.ServiceInterface;

import com.example.demo.Entity.ChapterEntity;

import java.util.List;

public interface ChapterServiceInterface {
    /**
     * Trả về list các chapter by BookID
     * */
    List<ChapterEntity> getAllChapterByBookID(String bookID);
}
