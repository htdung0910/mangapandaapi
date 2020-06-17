package com.example.demo.Entity;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "[Genres]")
public class GenresEntity {
    @Id
    @Column(name = "genreID")
    private Integer genreID;

    @Column(name = "genre")
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private transient Collection<BookEntity> books;

    public GenresEntity() {
    }

    public GenresEntity(Integer genreID, String genre) {
        this.genreID = genreID;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenresEntity genresEntity = (GenresEntity) o;

        if (!genreID.equals(genresEntity.genreID)) return false;
        return genre.equals(genresEntity.genre);
    }

    @Override
    public int hashCode() {
        int result = genreID.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
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

