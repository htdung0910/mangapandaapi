package com.example.demo.Service;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Repository.BookRepository;
import com.example.demo.ServiceInterface.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceInterface {
    @Autowired
    private BookRepository repo;

    @Override
    public List<BookEntity> findAllByTitle(String title) {
        return repo.findAllByTitle(title);
    }

    @Override
    public List<BookEntity> getBook() {
        return repo.findAll();
    }

    @Override
    public List<BookEntity> getTop10BookByRateValue() {
        return repo.getTop10BookByRateValue();
    }

    @Override
    public List<BookEntity> getBookByGenres(long listNum,long genreID) {
        return repo.getBookByGenres(listNum,genreID);
    }


}
