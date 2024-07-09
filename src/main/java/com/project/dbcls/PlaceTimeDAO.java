package com.project.dbcls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlaceTimeDAO {

    public static ResultSet getCities() throws SQLException {
        String placequery = "select from_city,to_city from places";
        Connection con = DbCon.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(placequery);
        return rs;
    }

    public static ResultSet getDateTime(int n) throws SQLException {
        String placequery = "select timing from places where route_id=" + n;
        Connection con = DbCon.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(placequery);
        return rs;
    }
}
