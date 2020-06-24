package com.example.demo.ServiceInterface;

import com.example.demo.Entity.GenresEntity;

import java.util.List;

public interface GenresServiceInterface {
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
