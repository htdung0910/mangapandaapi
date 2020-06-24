package com.example.demo.Entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`Chapter`")
@JsonIgnoreProperties(value = {"bookID","images"})
public class ChapterEntity {
    @Id
    @Column(name = "chapterID")
    private String id;

    @Column(name = "chapter_name")
    private String chapterName;

    @Column(name = "upload_date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookID", nullable = false)
    private BookEntity bookID;

    @OneToMany(mappedBy = "chapterID",fetch = FetchType.LAZY)
    private List<ImageEntity> images;
}
