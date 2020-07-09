package com.example.demo.Entity.ReturnEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ReturnUserEntity {
    private String username;
    private String fullname;

    public ReturnUserEntity(String username, String fullname) {
        this.username = username;
        this.fullname = fullname;
    }
}
