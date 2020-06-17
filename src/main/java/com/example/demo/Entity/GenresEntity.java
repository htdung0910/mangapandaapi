package com.example.demo.Entity;

import java.util.*;

public class GenresEntity {

    private Integer genreID;
    private String genre;
    private Collection<BookEntity> books;

    public GenresEntity() {
    }

    public GenresEntity(Integer genreID, String genre) {
        this.genreID = genreID;
        this.genre = genre;
    }

    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Collection<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Collection<BookEntity> books) {
        this.books = books;
    }
}

