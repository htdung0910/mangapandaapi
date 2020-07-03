package com.example.demo.Repository;

import com.example.demo.Entity.BookProcessEntity;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookProcessRepository extends JpaRepository<BookProcessEntity, String> {
    @Query(value = "select bp from BookProcessEntity bp where bp.username = :username and bp.bookID = :bookID")
    BookProcessEntity getInfoUserBetweenBook(@Param("username") String username, @Param("bookID") String bookID);

}
