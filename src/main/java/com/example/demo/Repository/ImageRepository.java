package com.example.demo.Repository;

import com.example.demo.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {
    @Query("SELECT i.url FROM ImageEntity i WHERE i.chapterID = :chapterID ORDER BY length(i.id), i.id ")
    List<String> getImagesByChapterID(@Param("chapterID") String chapterID);
}
