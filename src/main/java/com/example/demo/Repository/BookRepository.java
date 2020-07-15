package com.example.demo.Repository;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "SELECT TOP 10 * FROM Book ORDER BY rating_value DESC",nativeQuery = true)
    List<BookEntity> findTop10ByOrderByRatingValueDesc();

    @Query(value="SELECT TOP (?1) *\n" +
            "  FROM [Book] b\n" +
            "  INNER JOIN Book_genres bg ON b.bookID = bg.bookID\n" +
            "  INNER JOIN Genres g ON g.genreID = bg.genreID\n" +
            "  WHERE g.genreID = ?2 and b.rating_value between 4.01 and 5\n" +
            "  ORDER BY b.rating_value, newid() ", nativeQuery = true)
    List<BookEntity> getBookByGenres(long quantity ,long genresID);


    @Query(value="SELECT g.genreID\n" +
            "  FROM [dbo].[Book_process] bp\n" +
            "  INNER JOIN Book b ON bp.bookID = b.bookID\n" +
            "  INNER JOIN Book_genres bg ON bg.bookID = b.bookID\n" +
            "  INNER JOIN Genres g ON bg.genreID = g.genreID\n" +
            "  WHERE bp.username = ?1\n" +
            "  GROUP BY g.genreID\n" +
            "  ORDER BY Count(g.genreID) * 0.4 + (SUM(bp.rate) / Count(g.genreID)) * 0.25 + SUM(CAST(bp.isFollow AS INT)) * 0.35 DESC", nativeQuery = true)
    List<Integer> getListGenresRecommend(String username);


    @Query(value="SELECT b.*\n" +
            "  FROM [dbo].[Book] b\n" +
            "  INNER JOIN Book_process bp ON bp.bookID = b.bookID\n" +
            "  INNER JOIN [User] u ON u.username = bp.username\n" +
            "  WHERE u.username = ?1 and bp.isFollow = 1", nativeQuery = true)
    List<BookEntity> getListMangaFollowByUser(String username);

    @Query(value="SELECT Top 10 b.*\n" +
            "  FROM [dbo].[Book] b\n" +
            "  INNER JOIN Chapter c ON c.bookID = b.bookID\n" +
            "  GROUP BY b.bookID, b.title, b.thumnail_path, b.rating_value, b.rating_count, b.author, b.detail_content, b.isLogin\n" +
            "  ORDER BY max(c.upload_date) DESC\n", nativeQuery = true)
    List<BookEntity> getTop10ListMangaOrderByUploadDate();

    @Query(value = "SELECT b FROM BookEntity b WHERE b.isLogin = 1")
    public Page<BookEntity> findVipBook(Pageable pageable);

}
