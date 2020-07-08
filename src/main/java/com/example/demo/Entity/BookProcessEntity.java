package com.example.demo.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Data
@Entity
@Table(name = "[Book_process]")
public class BookProcessEntity {

    @EmbeddedId
    private MyCompositePK id;

    @Column(name = "rate")
    private Float rate;

    @Column(name = "isFollow")
    private Boolean isFollow;

    @Column(name = "isUpload")
    private Boolean isUpload;

    public BookProcessEntity(String username, String bookID, Float rate,Boolean isFollow,Boolean isUpload) {
        this.id = new MyCompositePK(username,bookID);
        this.rate = rate != null ? rate : 0;
        this.isFollow = isFollow != null ? isFollow : false;
        this.isUpload = isUpload != null ? isUpload : false;
    }

    public BookProcessEntity() {
    }
}

@Getter
@Setter
@Data
@Embeddable
class MyCompositePK implements Serializable {
    @Column(name = "username")
    private String username;

    @Column(name = "bookID")
    private String bookID;

    public MyCompositePK() {
    }

    public MyCompositePK(String username, String bookID) {
        this.username = username;
        this.bookID = bookID;
    }
}
