package com.example.demo.Repository;

import com.example.demo.Entity.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterReposiory extends JpaRepository<ChapterEntity, String> {



    @Query("SELECT c FROM ChapterEntity c WHERE c.bookID = :bookID order by length(c.id),c.id ASC")
    List<ChapterEntity> getChaptersByBookID(@Param("bookID") String bookID);

}
