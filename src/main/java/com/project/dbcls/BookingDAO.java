package com.project.dbcls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingDAO {

    public static int getCapacity(int routeId, LocalDate bDate, int busNo) throws SQLException {
        String placequery = "select count(booking_id) from booking where route_id=? and book_date=? and bus_id=?";
        Connection con = DbCon.getConnection();
        PreparedStatement st = con.prepareStatement(placequery);
        Date bookDate = Date.valueOf(bDate);
        st.setInt(1, routeId);
        st.setDate(2, bookDate);
        st.setInt(3, busNo);
        ResultSet rs = st.executeQuery();
        rs.next();
        int capacity = rs.getInt(1);
        return capacity;
    }

    public static void confirmBooking(int routeId, LocalDate bDate, int busNo, String name, long num, String email)
            throws SQLException {
        if (checkRedent(routeId, bDate, busNo, num)) {
            String placequery = "insert into booking(book_date,route_id,bus_id,cus_name,cus_number,email) values(?,?,?,?,?,?)";
            Connection con = DbCon.getConnection();
            PreparedStatement st = con.prepareStatement(placequery);
            Date bookDate = Date.valueOf(bDate);
            st.setDate(1, bookDate);
            st.setInt(2, routeId);
            st.setInt(3, busNo);
            st.setString(4, name);
            st.setLong(5, num);
            st.setString(6, email);
            int result = st.executeUpdate();
            if (result == 1) {
                ReceiptGeneratorDAO.generateBookingInfo(routeId, bDate, busNo, num);
            } else {
                System.out.println("\n\033[31m\033[1mBooking Failed, Techinical Error!!!!!!!!!!!!!!!!!!!!!\033[0m");
            }
        } else {
            System.out.println("\n\033[31m\033[1myou already book ticket on " + bDate + " using this " + num + ". For the Same route and bus.\033[0m");
        }
    }

    public static boolean checkRedent(int routeId, LocalDate bDate, int busNo, long num) throws SQLException {
        String placequery = "select count(booking_id) from booking where route_id=? and book_date=? and bus_id=? and cus_number=?";
        Connection con = DbCon.getConnection();
        PreparedStatement st = con.prepareStatement(placequery);
        Date bookDate = Date.valueOf(bDate);
        st.setInt(1, routeId);
        st.setDate(2, bookDate);
        st.setInt(3, busNo);
        st.setLong(4, num);
        ResultSet rs = st.executeQuery();
        rs.next();
        return rs.getInt(1) == 0;
    }

    public static void removeYesBooking(LocalDate c_Date) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String currDate = c_Date.format(formatter);
        String placequery = "DELETE FROM booking WHERE book_date < TO_DATE(?, 'DD-MM-YY')";
        Connection con = DbCon.getConnection();
        PreparedStatement st = con.prepareStatement(placequery);
        st.setString(1, currDate);
        st.executeUpdate();
    }

    public static ResultSet getRequireInfo(int bookingId) throws SQLException {
        String placequery = "select route_id,bus_id,cus_name,book_date from booking where booking_id=" + bookingId;
        Connection con = DbCon.getConnection();
        Statement st = con.createStatement();
        return st.executeQuery(placequery);
    }

    public static void cancelBooking(int bookingId) throws SQLException {
        String placequery = "delete from booking where booking_id=" + bookingId;
        Connection con = DbCon.getConnection();
        Statement st = con.createStatement();
        int result = st.executeUpdate(placequery);
        if (result == 1) {
            System.out.println("\n\033[1m\033[32mYour booking cancellation process is sucessfully done \033[0m");
        } else {
            System.out.println(" Some Techinical Error.Cancellation process failed. Please Try Again later");
        }
    }
}
