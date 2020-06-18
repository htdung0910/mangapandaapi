package com.example.demo.ReturnEntity;

import com.example.demo.Entity.GenresEntity;

import java.util.List;

public class ReturnBookEntityWithGenres {
    private String id;
    private String title;
    private String thumnailPath;
    private List<GenresEntity> genresList;

    public ReturnBookEntityWithGenres(String id, String title, String thumnailPath, List<GenresEntity> genresList) {
        this.id = id;
        this.title = title;
        this.thumnailPath = thumnailPath;
        this.genresList = genresList;
    }
}
