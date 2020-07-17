package com.example.demo.service;

import com.example.demo.Entity.GenresEntity;

import java.util.List;

public interface GenresService {
    /**
     * Trả về Genres
     *
     * */
    List<GenresEntity> getAllGenres();
    /**
     * Get Genrse By ID
     * Parameter genreID
     *
     * @param genrsID*/
    GenresEntity findGenreById(Long genrsID);
}
