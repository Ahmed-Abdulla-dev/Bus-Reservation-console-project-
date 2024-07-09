package com.project.dbcls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BusDAO {

    public static ResultSet getBuses(int routeId) throws SQLException {
        String placequery = "select bus_id,bus_name,ac from buses where route_id=" + routeId;
        Connection con = DbCon.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(placequery);
        return rs;
    }

    public static int getTotalCapacity(int busNo, int routeId) throws SQLException {
        {
            String placequery = "select capacity from buses where bus_id=? and route_id=?";
            Connection con = DbCon.getConnection();
            PreparedStatement st = con.prepareStatement(placequery);
            st.setInt(1, busNo);
            st.setInt(2, routeId);
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}
