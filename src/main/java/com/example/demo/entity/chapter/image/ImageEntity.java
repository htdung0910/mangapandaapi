package com.example.demo.entity.chapter.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entity.chapter.ChapterEntity;
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
@Table(name = "`Chapter_image`")
@JsonIgnoreProperties(value = "chapterID")
public class ImageEntity {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "url")
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "chapter_id", nullable = false)
	private ChapterEntity chapterID;
	

}
