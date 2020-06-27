package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "[Genres]")
@JsonIgnoreProperties(value = "books")
public class GenresEntity {
    @Id
    @Column(name = "genreID")
    private Integer genreID;

    @Column(name = "genre")
    @Nationalized
    private String genre;

    /*@ManyToMany(mappedBy = "genres",fetch = FetchType.LAZY)
    private transient Collection<BookEntity> books;*/

    public GenresEntity() {
    }

    public GenresEntity(Integer genreID, String genre) {
        this.genreID = genreID;
        this.genre = genre;
    }
}

