package com.example.demo.Repository;

import com.example.demo.Entity.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterReposiory extends JpaRepository<ChapterEntity, String> {

    @Query(value = "SELECT * \n" +
            "  FROM [dbo].[Chapter] c\n" +
            "  INNER JOIN [Chapter_image] ci ON ci.chapter_id = c.chapterID\n" +
            "  WHERE c.chapterID = ?1\n" +
            "  ORDER BY len(ci.id),ci.id"
            , nativeQuery = true)
    ChapterEntity getAllChapterImageByChapterID(String chapterID);

    @Query(value = "SELECT c \n" +
            "  FROM [Chapter] c\n" +
            "  WHERE c.bookID = ?1\n", nativeQuery = true)
    List<ChapterEntity> getAllChapterByBookID(String bookID);

}
