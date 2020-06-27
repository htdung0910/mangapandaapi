package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"genres","chapters"})
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

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "[Book_genres]",
            joinColumns = @JoinColumn(name = "bookID"),
            inverseJoinColumns = @JoinColumn(name = "genreID"))
    private Collection<GenresEntity> genres;

    @OneToMany(mappedBy = "bookID",fetch = FetchType.LAZY)
    private List<ChapterEntity> chapters;*/
}
