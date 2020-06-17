package com.example.demo.service;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.GenresEntity;
import com.example.demo.Utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenresService {
    private List<GenresEntity> listGenre;

    public List<GenresEntity> getListGenre() {
        return listGenre;
    }

    public void setListGenre(List<GenresEntity> listGenre) {
        this.listGenre = listGenre;
    }

    public void searchGenreOfManga(String searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        GenresEntity dto = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT * FROM Genres g \n" +
                        "INNER JOIN [Book_genres] bg ON bg.genreID = g.genreID \n" +
                        "INNER JOIN Book b ON bg.bookID = b.bookID \n" +
                        "WHERE b.bookID = ? ORDER BY b.rating_value DESC";
                stm = con.prepareStatement(sql);
                stm.setString(1, searchValue);
                rs = stm.executeQuery();
                while(rs.next()){
                    int genreID = rs.getInt("genreID");
                    String genre = rs.getString("genre");

                    dto = new GenresEntity(genreID,genre);
                    if(listGenre == null) listGenre = new ArrayList<>();
                    listGenre.add(dto);
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



    public GenresEntity findGenreById(long searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        GenresEntity dto = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT * FROM Genres WHERE genreID = ?";
                stm = con.prepareStatement(sql);
                stm.setLong(1, searchValue);
                rs = stm.executeQuery();
                while(rs.next()){
                    int genreID = rs.getInt("genreID");
                    String genre = rs.getString("genre");
                    dto = new GenresEntity(genreID,genre);
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
        return dto;
    }

    public void getAllGenres() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        GenresEntity dto = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT * FROM Genres";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    int genreID = rs.getInt("genreID");
                    String genre = rs.getString("genre");
                    dto = new GenresEntity(genreID,genre);
                    if(listGenre == null) listGenre = new ArrayList<>();
                    listGenre.add(dto);
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
