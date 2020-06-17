/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Huynh Dung
 */
public class DBUtils implements Serializable{
    
    public static Connection makeConnection() throws ClassNotFoundException, SQLException{
        Connection con =  null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://htd0910.ddns.net;databaseName=BookApp_Manager";
        con = DriverManager.getConnection(url,"htd0910","123456");
        return con;
    }
}
