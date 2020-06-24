package com.example.demo.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Data
@Entity
@Table(name = "[Book_process]")
public class BookProcessEntity {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "bookID")
    private String bookID;

    @Column(name = "rate")
    private float rate;

    @Column(name = "currentPage")
    private int currentPage;

    @Column(name = "isFollow")
    private boolean isFollow;

    @Column(name = "isUpload")
    private boolean isUpload;
}
