package com.example.demo.entity.book;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.example.demo.entity.chapter.ChapterEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`Book`")
public class BookEntity {
	@Id
	@Column(name = "bookID")
	private String id;
	@Column(name = "title")
	@Nationalized
	private String title;
	@Column(name = "thumnail_path")
	private String thumnailPath;
	@Column(name = "rating_value")
	private float ratingValue;
	@Column(name = "rating_count")
	private float ratingCount;
	@Column(name = "author")
	private String author;
	@Column(name = "detail_content")
	private String detailContent;
	
	@OneToMany(mappedBy = "bookID",fetch = FetchType.EAGER)
	private List<ChapterEntity> chapters;

}
