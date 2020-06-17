package com.example.demo.Service;

import com.example.demo.Entity.GenresEntity;
import com.example.demo.Repository.GenresRepository;
import com.example.demo.ServiceInterface.GenresServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenresService implements GenresServiceInterface {
    @Autowired
    private GenresRepository repo;
    @Override
    public List<GenresEntity> getAllGenres() {
        return repo.findAll();
    }

    @Override
    public GenresEntity findGenreById(Long genreID) {
        return repo.findGenreById(genreID);
    }
}
