package com.example.demo.service;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService{


    private List<BookEntity> listBook;

    public List<BookEntity> getListBook() {
        return listBook;
    }

    public void setListBook(List<BookEntity> listBook) {
        this.listBook = listBook;
    }

    public void searchMangaByTitle(String searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookEntity dto = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT * FROM [Book] WHERE title like N'%"+searchValue+"%'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String id = rs.getString("bookID");
                    String title = rs.getString("title");
                    String thumnail_path = rs.getString("thumnail_path");
                    Float rating_value = rs.getFloat("rating_value");
                    Float rating_count = rs.getFloat("rating_count");
                    String author = rs.getString("author");
                    String detail_content = rs.getString("detail_content");
                    dto = new BookEntity(id,title,thumnail_path,rating_value,rating_count,author,detail_content);
                    if(listBook == null) listBook = new ArrayList<>();
                    listBook.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getTop10BookByRateValue() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookEntity dto = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT TOP 10 * FROM [Book] ORDER BY rating_value DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String id = rs.getString("bookID");
                    String title = rs.getString("title");
                    String thumnail_path = rs.getString("thumnail_path");
                    Float rating_value = rs.getFloat("rating_value");
                    Float rating_count = rs.getFloat("rating_count");
                    String author = rs.getString("author");
                    String detail_content = rs.getString("detail_content");
                    dto = new BookEntity(id,title,thumnail_path,rating_value,rating_count,author,detail_content);
                    if(listBook == null) listBook = new ArrayList<>();
                    listBook.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getBookByGenres(long listNum,long genresID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookEntity dto = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT TOP (?) *" +
                        " FROM [Book] " +
                        " INNER JOIN [Book_genres] ON [Book].bookID = [Book_genres].bookID" +
                        " INNER JOIN [Genres] ON [Genres].genreID = [Book_genres].genreID" +
                        " WHERE [Genres].genreID = ?" +
                        " ORDER BY [Book].rating_value DESC";
                stm = con.prepareStatement(sql);
                stm.setLong(1, listNum);
                stm.setLong(2, genresID);
                rs = stm.executeQuery();
                while(rs.next()){
                    String id = rs.getString("bookID");
                    String title = rs.getString("title");
                    String thumnail_path = rs.getString("thumnail_path");
                    Float rating_value = rs.getFloat("rating_value");
                    Float rating_count = rs.getFloat("rating_count");
                    String author = rs.getString("author");
                    String detail_content = rs.getString("detail_content");
                    dto = new BookEntity(id,title,thumnail_path,rating_value,rating_count,author,detail_content);
                    if(listBook == null) listBook = new ArrayList<>();
                    listBook.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
