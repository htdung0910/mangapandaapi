package com.example.demo.Repository;

import com.example.demo.Entity.BookProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookProcessInterface extends JpaRepository<BookProcessEntity, String> {
    
}
