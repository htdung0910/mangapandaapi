package com.example.demo.Repository;

import com.example.demo.Entity.BookEntity;
import org.hibernate.dialect.function.StandardAnsiSqlAggregationFunctions;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.support.incrementer.SqlServerMaxValueIncrementer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    @Async
    @Query(value = "SELECT b.title FROM BookEntity b")
    List<String> getAllTitle();

    @Async
    @Query(value = "SELECT b FROM BookEntity b WHERE b.title like %:title%")
    List<BookEntity> getBookByTitle(@Param("title") String title);

    //SELECT s FROM Students s ORDER BY s.id DESC LIMIT 1
    @Async
    @Query(value = "SELECT TOP 10 * FROM Book ORDER BY rating_value DESC",nativeQuery = true)
    List<BookEntity> findTop10ByOrderByRatingValueDesc();

    @Async
    @Query(value="SELECT b \n" +
            "  FROM BookEntity b, GenresEntity g" +
            "  WHERE g.genreID = :genresID \n" +
            "  ORDER BY b.ratingvalue DESC")
    List<BookEntity> getBookByGenres(@Param("genresID") int genresID, Pageable page);





}
