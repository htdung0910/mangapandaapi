package com.example.demo.ReturnEntity;

import com.example.demo.Entity.GenresEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class ReturnBookEntity {
    private String id;
    private String title;
    private String thumnailPath;

    public ReturnBookEntity(String id, String title, String thumnailPath) {
        this.id = id;
        this.title = title;
        this.thumnailPath = thumnailPath;
    }
}
