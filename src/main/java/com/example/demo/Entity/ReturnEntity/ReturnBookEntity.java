package com.example.demo.Entity.ReturnEntity;

import com.example.demo.Entity.GenresEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class ReturnBookEntity {
    private String bookID;
    private String title;
    private String thumnailpath;
    private List<GenresEntity> genres;

    public ReturnBookEntity(String id, String title, String thumnailPath) {
        this.bookID = id;
        this.title = title;
        this.thumnailpath = thumnailPath;
    }

    public ReturnBookEntity(String id, String title, String thumnailPath, List<GenresEntity> genresList) {
        this.bookID = id;
        this.title = title;
        this.thumnailpath = thumnailPath;
        this.genres = new ArrayList<>();
        this.genres.addAll(genresList);
    }
}
