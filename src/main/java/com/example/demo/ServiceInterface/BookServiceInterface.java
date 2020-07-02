package com.example.demo.ServiceInterface;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.ChapterEntity;
import com.example.demo.ReturnEntity.ReturnBookEntity;

import java.util.*;

public interface BookServiceInterface {

    /**
     * Trả về list các title của tất cả manga
     * */
    List<String> getAllTitile();

    /**
     * Trả về list các manga có chứa title
     * */
    List<BookEntity> getMangaByTitle(String title);

    /**
     * Top 10 manga có lượt vote cao nhất
     * */
    List<BookEntity> getHottestManga();

    /**
     * Trả về 1 manga dựa vào id,null nếu ko tìm ra
     * */
    BookEntity getByID(String id);

    /**
     * Trả về 1 chapter,rồi từ entity chapter này lấy list các image của nó
     * */
    List<String> getImagesByChapterID(String chapterID);

    /**
     * Trả về những chapter của manga này
     */
    List<ChapterEntity> getChaptersByBookID(String bookID);

    /**
     * Trả về Top 4 book rate cao nhất theo genreID
     *
     * */
    List<BookEntity> getBookByGenres(int quantity,int genreID);
}
