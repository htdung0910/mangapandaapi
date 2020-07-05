package com.example.demo.Repository;

import com.example.demo.Entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username")
    UserEntity getUser(@Param("username") String username);

    @Query(value = "SELECT username FROM UserEntity")
    List<String> getAllUserName();

    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
    UserEntity login(@Param("username") String username, @Param("password") String password);

    @Query(value="SELECT u.*\n" +
            "FROM [dbo].[User] u\n" +
            "INNER JOIN Book_process bp ON bp.username = u.username\n" +
            "INNER JOIN [Book] b ON b.bookID = bp.bookID\n" +
            "WHERE bp.isUpload = 1\n" +
            "GROUP BY u.username,u.fullname,u.[password],u.isadmin\n" +
            "Order by SUM(b.rating_value) DESC", nativeQuery = true)
    List<UserEntity> getListTopUserByRateBook();
}
