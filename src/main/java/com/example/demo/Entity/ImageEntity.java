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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private ChapterEntity chapterID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ChapterEntity getChapterID() {
        return chapterID;
    }

    public void setChapterID(ChapterEntity chapterID) {
        this.chapterID = chapterID;
    }
}
