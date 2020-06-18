package com.example.demo.Repository;

import com.example.demo.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    @Query(value = "SELECT b.title FROM BookEntity b")
    List<String> getAllTitle();

    @Query(value = "SELECT * FROM [Book] WHERE title like %?1%",nativeQuery = true)
    List<BookEntity> getBookByTitle(String title);

    //SELECT s FROM Students s ORDER BY s.id DESC LIMIT 1
    @Query(value = "SELECT TOP 10 * FROM Book ORDER BY rating_value DESC",nativeQuery = true)
    List<BookEntity> findTop10ByOrderByRatingValueDesc();

    @Query(value="SELECT TOP (?1) *\n" +
            "  FROM [Book] b\n" +
            "  INNER JOIN Book_genres bg ON b.bookID = bg.bookID\n" +
            "  INNER JOIN Genres g ON g.genreID = bg.genreID\n" +
            "  WHERE g.genreID = ?2\n" +
            "  ORDER BY b.rating_value DESC ", nativeQuery = true)
    List<BookEntity> getBookByGenres(long listNum,long genresID);





}
