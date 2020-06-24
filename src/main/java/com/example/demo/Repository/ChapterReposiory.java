package com.example.demo.Repository;

import com.example.demo.Entity.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterReposiory extends JpaRepository<ChapterEntity, String> {

    @Async
    @Query(value = "SELECT * \n" +
            "  FROM [dbo].[Chapter] c\n" +
            "  INNER JOIN [Chapter_image] ci ON ci.chapter_id = c.chapterID\n" +
            "  WHERE c.chapterID = ?1\n" +
            "  ORDER BY len(ci.id),ci.id"
            , nativeQuery = true)
    ChapterEntity getAllChapterImageByChapterID(String chapterID);
}
