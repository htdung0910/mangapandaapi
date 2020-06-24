package com.example.demo.Entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "[Book]")
public class BookEntity {
    @Id
    @Column(name = "bookID")
    private String bookID;

    @Column(name = "title")
    @Nationalized
    private String title;

    @Column(name = "thumnail_path")
    private String thumnailpath;

    @Column(name = "rating_value")
    private float ratingvalue;

    @Column(name = "rating_count")
    private float ratingcount;

    @Column(name = "author")
    private String author;

    @Column(name = "detail_content")
    private String detailcontent;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "[Book_genres]",
            joinColumns = @JoinColumn(name = "bookID"),
            inverseJoinColumns = @JoinColumn(name = "genreID"))
    private Collection<GenresEntity> genres;

    @OneToMany(mappedBy = "bookID",fetch = FetchType.LAZY)
    private List<ChapterEntity> chapters;

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

    public List<ChapterEntity> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterEntity> chapters) {
        this.chapters = chapters;
    }
}
