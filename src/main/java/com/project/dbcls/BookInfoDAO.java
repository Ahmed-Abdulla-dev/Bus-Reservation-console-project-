package com.project.dbcls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class BookInfoDAO {

    public static ResultSet getBookInfo(long num, String date) throws ParseException, SQLException {
        String placequery = "select booking_id,cus_name,bus_id,route_id from booking where book_date=TO_DATE(?, 'DD/MM/YY') and cus_number=?";
        Connection con = DbCon.getConnection();
        PreparedStatement st = con.prepareStatement(placequery);
        st.setString(1, date);
        st.setLong(2, num);
        return st.executeQuery();
    }

    public static ResultSet getReceiptInfo(int busNo, int routeId) throws SQLException {
        String placequery = "select From_city,to_city,timing,bus_name,ac,cost from receipt where  route_id=? and bus_id=?";
        Connection con = DbCon.getConnection();
        PreparedStatement st = con.prepareStatement(placequery);
        st.setInt(1, routeId);
        st.setInt(2, busNo);
        return st.executeQuery();
    }
}
