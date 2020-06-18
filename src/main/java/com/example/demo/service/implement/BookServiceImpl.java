package com.example.demo.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.book.BookEntity;
import com.example.demo.entity.book.ReturnBookEntity;
import com.example.demo.entity.chapter.ChapterEntity;
import com.example.demo.entity.chapter.image.ImageEntity;
import com.example.demo.entity.chapter.image.ReturnImageEntity;
import com.example.demo.repo.BookRepo;
import com.example.demo.repo.ChapterRepo;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static Logger log = LogManager.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepo repo;

	@Autowired
	private ChapterRepo chapterRepo;

	@Override
	public List<String> getAllTitile() {
		return repo.getAllTitle();
	}

	@Override
	public List<ReturnBookEntity> getMangaByTitle(String title) {
		if (title.isEmpty() || title == null) {
			return null;
		}
		try {
			List<BookEntity> temp = repo.getBookByTitle(title);
			List<ReturnBookEntity> returnData = new ArrayList<ReturnBookEntity>(temp.size());
			temp.stream().forEach(x -> {
				returnData.add(new ReturnBookEntity(x.getId(), x.getTitle(), x.getThumnailPath()));
			});
			return returnData;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<ReturnBookEntity> getHottestManga() {
		List<BookEntity> temp = null;
		try {
			temp = repo.findTop10ByOrderByRatingValueDesc();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		List<ReturnBookEntity> returnData = new ArrayStack<ReturnBookEntity>(temp.size());
		temp.stream().forEach(x -> {
			returnData.add(new ReturnBookEntity(x.getId(), x.getTitle(), x.getThumnailPath()));
		});
		return returnData;
	}

	@Override
	public BookEntity getByID(String id) {
		BookEntity returnData = null;
		try {
			if (repo.existsById(id))
				returnData = repo.findById(id).orElse(null);
			return returnData;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return returnData;
	}

	@Override
	public List<String> getImageByChapterID(String chapterID) {
		ChapterEntity chapter = null;
		try {
			chapter = chapterRepo.getAllChapterImageByChapterID(chapterID);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (chapter == null)
			return null;
		List<ImageEntity> tempData = chapter.getImages();
		List<String> returnData = new ArrayList<String>(tempData.size());
		tempData.stream().forEach(x -> {
			returnData.add(x.getUrl());
		});
		return returnData;
	}

}
