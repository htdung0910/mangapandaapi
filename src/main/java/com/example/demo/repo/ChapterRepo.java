package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.chapter.ChapterEntity;
import org.springframework.data.jpa.repository.Query;

public interface ChapterRepo extends JpaRepository<ChapterEntity, String> {
    @Query(value = "SELECT c.*,ci.*\n" +
            "  FROM [dbo].[Chapter] c\n" +
            "  INNER JOIN [Chapter_image] ci ON ci.chapter_id = c.chapterID\n" +
            "  WHERE c.chapterID = ?1\n" +
            "  ORDER BY len(ci.id),ci.id"
            , nativeQuery = true)
    ChapterEntity getAllChapterImageByChapterID(String chapterID);
}
