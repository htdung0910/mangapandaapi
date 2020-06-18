package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ReturnEntity;
import com.example.demo.entity.book.BookEntity;
import com.example.demo.entity.book.ReturnBookEntity;
import com.example.demo.entity.chapter.image.ReturnImageEntity;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/manga")
public class MangaController {

	@Autowired
	private BookService service;

	@Autowired
	BookRepo repo;// test

	@GetMapping("/test/{title}")
	public Object test(@PathVariable("title") String title) {
		return new ResponseEntity<List<BookEntity>>(repo.getBookByTitle(title), HttpStatus.OK);
	}

	@GetMapping("/all")
	public Object getAllTitle() {
		return new ResponseEntity<List<String>>(service.getAllTitile(), HttpStatus.OK);
	}

	@GetMapping("/title/{search}")
	@CrossOrigin
	public Object getBookByTitle(@PathVariable(value = "search") String title) {
		String title2 = title.trim();
		if (title2 == null || title2.isEmpty())
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Search can't be empty"),
					HttpStatus.NOT_ACCEPTABLE);
		try {
			List<ReturnBookEntity> dataReturn = service.getMangaByTitle(title2);
			if (dataReturn == null)
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("Can't find the manga"), HttpStatus.NOT_FOUND);
			return new ResponseEntity<ReturnEntity>(new ReturnEntity(service.getMangaByTitle(title2)), HttpStatus.OK);

		} catch (Exception e) {

		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Exception occured"), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/hottestManga")
	@CrossOrigin
	public Object getTop10BookByRateValue() {
		return new ResponseEntity<ReturnEntity>(new ReturnEntity(service.getHottestManga()), HttpStatus.OK);
	}

	@GetMapping("/{bookID}")
	@CrossOrigin
	public Object getAllChapterByMangaID(@PathVariable("bookID") String bookID) {
		String bookID2 = bookID.trim();
		if (bookID2 == null || bookID2.isEmpty())
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Book id can't be empty"),
					HttpStatus.NOT_ACCEPTABLE);
		try {
			BookEntity returnData = service.getByID(bookID2);
			if (returnData == null)
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("The manga doesn't exist"),
						HttpStatus.NOT_FOUND);
			return new ResponseEntity<ReturnEntity>(new ReturnEntity(returnData), HttpStatus.OK);

		} catch (Exception e) {

		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Exception occured"), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/chapter/{chapterID}")
	@CrossOrigin
	public Object getChapterImageByChapterID(@PathVariable("chapterID") String chapterID) {
		String chapterID2 = chapterID.trim();
		if (chapterID2 == null || chapterID2.isEmpty())
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Chapter id can't be empty"),
					HttpStatus.NOT_ACCEPTABLE);
		try {
			List<String> returnData = service.getImageByChapterID(chapterID2);
			if (returnData == null)
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("The chapter doesn't exist"),
						HttpStatus.NOT_FOUND);
			return new ResponseEntity<ReturnEntity>(new ReturnEntity(returnData), HttpStatus.OK);

		} catch (Exception e) {
		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Exception occured"), HttpStatus.NOT_FOUND);

	}

}
