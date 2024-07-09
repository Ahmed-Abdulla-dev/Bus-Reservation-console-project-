package com.project.dbcls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.project.model.ReceiptGenerator;

public class ReceiptGeneratorDAO {

    public static void generateBookingInfo(int routeId, LocalDate bDate, int busNo, long num) throws SQLException {
        ResultSet bookRs = generateBookingId(routeId, bDate, busNo, num);
        bookRs.next();
        int bookingId = bookRs.getInt(1);
        String cusName = bookRs.getString(2);
        String email = bookRs.getString(3);
        String placequery = "select From_city,to_city,timing,bus_name,ac,cost from receipt where  route_id=? and bus_id=?";
        Connection con = DbCon.getConnection();
        PreparedStatement st = con.prepareStatement(placequery);
        st.setInt(1, routeId);
        st.setInt(2, busNo);
        ResultSet rs = st.executeQuery();

        rs.next();

        String fCity = rs.getString(1);
        String tCity = rs.getString(2);
        String time = rs.getString(3);
        String bName = rs.getString(4);
        String isAc = rs.getString(5);
        int rate = rs.getInt(6);
        try {
            ReceiptGenerator.generateReceipt(bookingId, fCity, tCity, time, bName, isAc, rate, bDate, cusName, email);
        } catch (IOException ex) {
        }
    }

    public static ResultSet generateBookingId(int routeId, LocalDate bDate, int busNo, long num) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String currDate = bDate.format(formatter);
        String placequery = "select booking_id,cus_name,email from booking where route_id=? and bus_id=? and book_date=? and cus_number=?";
        Connection con = DbCon.getConnection();
        PreparedStatement st = con.prepareStatement(placequery);
        st.setInt(1, routeId);
        st.setInt(2, busNo);
        st.setString(3, currDate);
        st.setLong(4, num);
        return st.executeQuery();
    }
}
