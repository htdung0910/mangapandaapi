package com.example.demo.ServiceInterface;

import com.example.demo.Entity.BookEntity;
import com.example.demo.ReturnEntity.ReturnBookEntity;
import com.example.demo.ReturnEntity.ReturnBookEntityWithGenres;

import java.util.*;

public interface BookServiceInterface {

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

    /**
     * Trả về Top 4 book rate cao nhất theo genreID
     *
     * */
    List<ReturnBookEntityWithGenres> getBookByGenres(long listNum,long genreID);
}
