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



    public BookEntity(String bookID, String title, String thumnailpath, float ratingvalue, float ratingcount, String author, String detailcontent) {
        this.bookID = bookID;
        this.title = title;
        this.thumnailpath = thumnailpath;
        this.ratingvalue = ratingvalue;
        this.ratingcount = ratingcount;
        this.author = author;
        this.detailcontent = detailcontent;
    }

    public BookEntity() {
    }

    @ManyToMany
    @JoinTable(name = "[Books_genres]",
            joinColumns = @JoinColumn(name = "bookID"),
            inverseJoinColumns = @JoinColumn(name = "genreID"))
    private Collection<GenresEntity> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity bookEntity = (BookEntity) o;

        if (!bookID.equals(bookEntity.bookID)) return false;
        return title.equals(bookEntity.title);
    }

    @Override
    public int hashCode() {
        int result = bookID.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    public Collection<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(Collection<GenresEntity> genres) {
        this.genres = genres;
    }
}
