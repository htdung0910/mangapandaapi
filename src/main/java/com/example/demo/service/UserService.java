package com.example.demo.service;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserService {


    public boolean login(String username, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookEntity dto = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT * FROM [User] WHERE username = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1,username);
                stm.setString(2,password);
                rs = stm.executeQuery();
                if(rs.next()) return true;
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
        return false;
    }
}

