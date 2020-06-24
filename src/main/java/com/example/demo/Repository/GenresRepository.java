package com.example.demo.Repository;

import com.example.demo.Entity.GenresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<GenresEntity, String> {
    @Async
    @Query(value = "SELECT g.genre, g.genreID FROM Genres AS g WHERE genreID = ?1 ", nativeQuery = true)
    GenresEntity findGenreById(long genreID);
}
