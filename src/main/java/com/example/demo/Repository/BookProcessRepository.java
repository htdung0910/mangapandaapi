package com.example.demo.Repository;

import com.example.demo.Entity.BookProcessEntity;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookProcessRepository extends JpaRepository<BookProcessEntity, String> {
    @Query(value = "select * from Book_process bp where username = ?1 and bookID = ?2", nativeQuery = true)
    BookProcessEntity getInfoUserBetweenBook(String username, String bookID);

}
