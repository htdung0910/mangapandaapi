package com.example.demo.Repository;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.GenresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    @Query(value = "SELECT b.title FROM BookEntity b")
    List<String> getAllTitle();

    @Query(value = "SELECT b FROM BookEntity b WHERE b.title like %:title%")
    List<BookEntity> getBookByTitle(@Param("title") String title);

    //SELECT s FROM Students s ORDER BY s.id DESC LIMIT 1
    @Query(value = "SELECT TOP 10 * FROM Book Where rating_value between 3.5 and 5 ORDER BY rating_value, newid()",nativeQuery = true)
    List<BookEntity> findTop10ByOrderByRatingValueDesc();

    @Query(value="SELECT TOP (?1) *\n" +
            "  FROM [Book] b\n" +
            "  INNER JOIN Book_genres bg ON b.bookID = bg.bookID\n" +
            "  INNER JOIN Genres g ON g.genreID = bg.genreID\n" +
            "  WHERE g.genreID = ?2\n" +
            "  ORDER BY b.rating_value, newid() ", nativeQuery = true)
    List<BookEntity> getBookByGenres(long listNum,long genresID);


    @Query(value="SELECT g.genreID\n" +
            "  FROM [dbo].[Book_process] bp\n" +
            "  INNER JOIN Book b ON bp.bookID = b.bookID\n" +
            "  INNER JOIN Book_genres bg ON bg.bookID = b.bookID\n" +
            "  INNER JOIN Genres g ON bg.genreID = g.genreID\n" +
            "  WHERE bp.username = ?1\n" +
            "  GROUP BY g.genreID\n" +
            "  ORDER BY Count(g.genreID) * 0.4 + (SUM(bp.rate) / Count(g.genreID)) * 0.25 + SUM(CAST(bp.is_follow AS INT)) * 0.35 DESC", nativeQuery = true)
    List<Integer> getListGenresRecommend(String username);

    @Query(value="Select Top 10 b.* From Book b\n" +
            "INNER JOIN Book_genres bg ON bg.bookID = b.bookID\n" +
            "INNER JOIN Genres g ON bg.genreID = g.genreID\n" +
            "WHERE b.rating_value BETWEEN 4 AND 5 ?1\n" +
            "order by g.genreID ,RAND() Desc", nativeQuery = true)
    List<BookEntity> getListMangaRecommend(String sql);



}
