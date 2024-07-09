package com.project.dbcls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
    public final static String URL="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    public final static String USER="core";
    public final static String PASS = "abdulla123";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASS);
    }
}
