package com.example.demo.Service;

import com.example.demo.Entity.GenresEntity;
import com.example.demo.Repository.GenresRepository;
import com.example.demo.ServiceInterface.GenresServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenresService implements GenresServiceInterface {
    @Autowired
    private GenresRepository repo;

    private static Logger log = LogManager.getLogger(GenresService.class);
    @Override
    public List<GenresEntity> getAllGenres() {
        try {
            return repo.findAll();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<GenresEntity>();
    }

    @Override
    public GenresEntity findGenreById(Long genreID) {
        return repo.findGenreById(genreID);
    }

}
