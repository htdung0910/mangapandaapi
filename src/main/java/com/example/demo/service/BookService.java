package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.book.BookEntity;
import com.example.demo.entity.book.ReturnBookEntity;
import com.example.demo.entity.chapter.image.ReturnImageEntity;

public interface BookService {

	/**
	 * Trả về list các title của tất cả manga
	 * */
	List<String> getAllTitile();
	
	/**
	 * Trả về list các manga có chứa title
	 * */
	List<ReturnBookEntity> getMangaByTitle(String title);
	
	/**
	 * Top 10 manga có lượt vote cao nhất
	 * */
	List<ReturnBookEntity> getHottestManga();
	
	/**
	 * Trả về 1 manga dựa vào id,null nếu ko tìm ra
	 * */
	BookEntity getByID(String id);
	
	/**
	 * Trả về 1 chapter,rồi từ entity chapter này lấy list các image của nó
	 * */
	List<String> getImageByChapterID(String chapterID);
}
