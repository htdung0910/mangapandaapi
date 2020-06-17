package com.example.demo.ServiceInterface;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.UserEntity;

import java.util.*;

public interface BookServiceInterface {

    List<BookEntity> findAllByTitle(String title);
    /**
     * Trả về Book
     *
     * */
    List<BookEntity> getBook();

    /**
     * Trả về Top 10 book rate cao nhất
     *
     * */
    List<BookEntity> getTop10BookByRateValue();

    /**
     * Trả về Top 4 book rate cao nhất theo genreID
     *
     * */
    List<BookEntity> getBookByGenres(long listNum,long genreID);
}
