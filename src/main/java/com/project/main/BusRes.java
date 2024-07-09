package com.project.main;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.project.dbcls.BookingDAO;
import com.project.dbcls.yesDatecheckDAO;
import com.project.model.BookInfo;
import com.project.model.Booking;
import com.project.model.PlaceTime;

public class BusRes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String topic = "Apple Transport Agency";
            String banner = "";
            for (int i = 0; i < topic.length(); i++) {
                banner += "=";
            }
            System.out.println(banner);
            System.out.println("\033[1m\033[33m" + topic.toUpperCase() + "\033[0m");
            System.out.println(banner);

            System.out.println("\n\033[1m\u001B[34m1.Book Ticket\u001B[0m");
            System.out.println("\033[1m\u001B[34m2.Booked Information\u001B[0m");
            System.out.println("\033[1m\u001B[34m3.Cancel Booking\u001B[0m");
            System.out.println("\033[1m\u001B[34m4.Exit\u001B[0m\n");

            System.out.print("\033[1mChoose Your Action:\033[0m");
            int i = scanner.nextInt();
            scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
            LocalDate currDate = LocalDate.now();
            LocalDate yesDate = null;

            switch (i) {
                case 1:
                    PlaceTime.setPlaceTime();
                    break;
                case 2:
                    try {
                        yesDate = yesDatecheckDAO.getYesDate();
                        if ((currDate.format(formatter)).equals(yesDate.format(formatter))) {
                            BookInfo.printBookInfo();
                        } else {
                            BookingDAO.removeYesBooking(currDate);
                            yesDatecheckDAO.removeYesDate();
                            yesDatecheckDAO.setYesDate();
                            BookInfo.printBookInfo();
                        }
                    } catch (SQLException ex) {
                    }
                    break;
                case 3:
                    try {
                        yesDate = yesDatecheckDAO.getYesDate();
                        if ((currDate.format(formatter)).equals(yesDate.format(formatter))) {
                            Booking.cancelBookingPage();
                        } else {
                            BookingDAO.removeYesBooking(currDate);
                            yesDatecheckDAO.removeYesDate();
                            yesDatecheckDAO.setYesDate();
                            Booking.cancelBookingPage();
                        }
                    } catch (SQLException ex) {
                    }
                    break;

                case 4:
                    System.out.println("\033[1m\033[36mThanks For Choosing Our Agency............!\033[0m");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\033[31m\033[1mInput Miss Match\033[0m");
                    System.out.println("\n");
                    break;
            }
        }
    }
}
