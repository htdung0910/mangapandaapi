package com.example.demo.Entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "[User]")
public class UserEntity {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "password")
    private String password;

    @Column(name = "isadmin")
    private int admin;

    public UserEntity() {

    }

}
