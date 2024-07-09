package com.project.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.project.dbcls.BookingDAO;
import com.project.dbcls.BusDAO;
import com.project.dbcls.yesDatecheckDAO;

public class available {

    public static void isAvailable(int routeId, LocalDate bDate, int busNo) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
            LocalDate currDate = LocalDate.now();
            LocalDate yesDate = yesDatecheckDAO.getYesDate();
            int capacity = BusDAO.getTotalCapacity(busNo, routeId);

            if ((currDate.format(formatter)).equals(yesDate.format(formatter))) {
                int currCapacity = BookingDAO.getCapacity(routeId, bDate, busNo);
                if (capacity > currCapacity) {
                    Booking.bookSeat(routeId, bDate, busNo);
                } else {
                    System.out.println("\n\033[31m\033[1mThe bus you selected is currently full on your selected date:" + bDate + "\033[0m");
                }
            } else {
                BookingDAO.removeYesBooking(currDate);
                yesDatecheckDAO.removeYesDate();
                yesDatecheckDAO.setYesDate();
                int currCapacity = BookingDAO.getCapacity(routeId, bDate, busNo);
                if (capacity > currCapacity) {
                    Booking.bookSeat(routeId, bDate, busNo);
                } else {
                    System.out.println("\n\033[31m\033[1mThe bus you selected is currently full on your selected date:" + bDate + "\033[0m");
                }

            }
        } catch (SQLException ex) {
        }
    }
}
