package com.example.demo.Entity;

import java.util.*;


public class BookEntity {
    private String bookID;

    private String title;

    private String thumnailpath;

    private float ratingvalue;

    private float ratingcount;

    private String author;

    private String detailcontent;

    private Collection<GenresEntity> genres;

    public BookEntity() {
    }

    public BookEntity(String bookID, String title, String thumnailpath, float ratingvalue, float ratingcount, String author, String detailcontent) {
        this.bookID = bookID;
        this.title = title;
        this.thumnailpath = thumnailpath;
        this.ratingvalue = ratingvalue;
        this.ratingcount = ratingcount;
        this.author = author;
        this.detailcontent = detailcontent;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumnailpath() {
        return thumnailpath;
    }

    public void setThumnailpath(String thumnailpath) {
        this.thumnailpath = thumnailpath;
    }

    public float getRatingvalue() {
        return ratingvalue;
    }

    public void setRatingvalue(float ratingvalue) {
        this.ratingvalue = ratingvalue;
    }

    public float getRatingcount() {
        return ratingcount;
    }

    public void setRatingcount(float ratingcount) {
        this.ratingcount = ratingcount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetailcontent() {
        return detailcontent;
    }

    public void setDetailcontent(String detailcontent) {
        this.detailcontent = detailcontent;
    }

    public Collection<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(Collection<GenresEntity> genres) {
        this.genres = genres;
    }
}
