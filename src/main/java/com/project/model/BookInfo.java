package com.project.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.project.dbcls.BookInfoDAO;

public class BookInfo {

    public static void printBookInfo() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("\033[4m\033[1m\033[35mWELCOME TO APPLES TRANSPORT AGENCY\033[0m");
            System.out.println("\n\n\033[32m\033[1mFor check your booking details:\033[0m");
            System.out.println("\n\033[1mEnter the date of your ticket booking:");
            String bookDate = scan.nextLine();
            System.out.println("\nEnter the contact number which is used to ticket booking:\033[0m");
            long bookNum = scan.nextLong();
            ResultSet cusInfo = BookInfoDAO.getBookInfo(bookNum, bookDate);
            cusInfo.next();
            int bookingNo = cusInfo.getInt(1);
            String cusName = cusInfo.getString(2);
            int busNo = cusInfo.getInt(3);
            int routeId = cusInfo.getInt(4);
            ResultSet receiptInfo = BookInfoDAO.getReceiptInfo(busNo, routeId);
            receiptInfo.next();
            String fCity = receiptInfo.getString(1);
            String tCity = receiptInfo.getString(2);
            String time = receiptInfo.getString(3);
            String bName = receiptInfo.getString(4);
            String isAc = receiptInfo.getString(5);
            int rate = receiptInfo.getInt(6);
            ReceiptGenerator.bookedReceipt(bookingNo, cusName, fCity, tCity, time, bName, isAc, rate, bookDate);
            System.out.println("\n\n\033[1m\033[36mThanks for choosing Apple Reservation Agency..........................!\033[0m");
            System.out.println("\n\n\033[1mPress y to go home page\033[0m");
            char opt = scan.next().charAt(0);
            if (opt == 'y' && opt == 'Y') {
            }
        } catch (SQLException ex) {
        } catch (ParseException ex) {
        } catch (IOException ex) {
        }
    }
}
