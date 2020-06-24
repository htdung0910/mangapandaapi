package com.example.demo.Repository;

import com.example.demo.Entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Async
    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username")
    UserEntity getUser(@Param("username") String username);

    @Async
    @Query(value = "SELECT username FROM UserEntity")
    List<String> getAllUserName();

    @Async
    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
    UserEntity login(@Param("username") String username, @Param("password") String password);

}
