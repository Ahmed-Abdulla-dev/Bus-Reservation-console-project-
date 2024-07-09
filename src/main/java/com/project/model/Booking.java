package com.project.model;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.project.dbcls.BookInfoDAO;
import com.project.dbcls.BookingDAO;

public class Booking {

    public static void bookSeat(int routeId, LocalDate bDate, int busNo) {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("\n\033[1m\033[35mTo confirm Booking on your travel,");
            System.out.println("Please fill the following details:\033[0m");
            System.out.print("\n\033[1mEnter your name:");
            String name = scan.nextLine();
            System.out.print("\nEnter your Number:");
            long cusNumber = scan.nextLong();
            scan.nextLine();
            System.out.print("\nEnter your E-mail:\033[0m");
            String email = scan.nextLine();
            if (name == null || name.trim().isEmpty() || cusNumber == 0 || email == null || email.trim().isEmpty()) {
                System.out.println("\033[31m\033[1mYou Must Fill all the above to confirm your booking!\033[0m");
            } else {
                BookingDAO.confirmBooking(routeId, bDate, busNo, name, cusNumber, email);
            }
        } catch (NoSuchElementException ex) {
            System.out.println(ex);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void cancelBookingPage() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("\n\n\033[1m\033[41mPlease read before cancelling the booking!!!!!!!!!!!!!!!!!!!!!!!!\033[0m");
            System.out.println(
                    "\033[1m\033[31m=> The booking id is mandatory for cancelling the ticket.If you forget your booking id check the receipt in email or get it in the booked info from home page.");
            System.out.println("=> You can only cancel the booking before the date of booked schedule.");
            System.out.println("=> 100% refund will be provided.\033[0m");

            System.out.println("\n\n\033[1m\033[35mTo cancel the booking:\033[0m");
            System.out.print("\n\033[1mEnter the booking id of the receipt:\033[0m");
            int bookingId = scan.nextInt();
            ResultSet bookDetails = BookingDAO.getRequireInfo(bookingId);
            bookDetails.next();
            int routeId = bookDetails.getInt(1);
            int busNo = bookDetails.getInt(2);
            String cusName = bookDetails.getString(3);
            Date date = bookDetails.getDate(4);
            LocalDate bookedDate = date.toLocalDate();
            if (bookedDate.equals(LocalDate.now())) {
                System.out.println("\n\033[31m\033[1mYou cannot cancel the booking on the booking Scheduled day\033[0m");
            } else {
                ResultSet receiptInfo = BookInfoDAO.getReceiptInfo(busNo, routeId);
                receiptInfo.next();
                String fCity = receiptInfo.getString(1);
                String tCity = receiptInfo.getString(2);
                String time = receiptInfo.getString(3);
                String bName = receiptInfo.getString(4);
                String isAc = receiptInfo.getString(5);
                int rate = receiptInfo.getInt(6);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                String bookDate = bookedDate.format(formatter);
                ReceiptGenerator.bookedReceipt(bookingId, cusName, fCity, tCity, time, bName, isAc, rate, bookDate);
                System.out.print("\n\n\033[1mEnter Y to confirm your cancellation of booking otherwise Enter n:\033[0m");
                String opt = scan.next();
                if (opt.equalsIgnoreCase("y")) {
                    BookingDAO.cancelBooking(bookingId);
                }
                System.out.println("\n\n\033[1m\033[36mThanks for choosing Apple Reservation Agency..........................!\033[0m");
                System.out.println("\n\033[1m\033[41mRedirecting to home page in 5 seconds......!\033[0m");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        } catch (NoSuchElementException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
        } catch (IOException ex) {
        }
    }
}
