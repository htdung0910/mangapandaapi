package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`Chapter_image`")
@JsonIgnoreProperties(value = "chapterID")
public class ImageEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "url")
    private String url;

    /*
     * @ManyToOne
     *
     * @JoinColumn(name = "chapter_id", nullable = false) private ChapterEntity
     * chapterID;
     */

    @Column(name = "chapter_id")
    private String chapterID;


}
