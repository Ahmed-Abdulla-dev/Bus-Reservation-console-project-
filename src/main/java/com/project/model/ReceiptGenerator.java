package com.project.model;

import java.io.IOException;
import java.time.LocalDate;

public class ReceiptGenerator {

    public static void generateReceipt(int bookingId, String fcity, String tcity, String time, String bname, String Ac,
            int rate, LocalDate bDate, String cusName, String email) throws IOException {
        System.out.println("\n\033[1m\033[32mYour Bus Ticket Reservation is Successfully Completed..................!\033[0m");
        System.out.println("\n\033[1m\033[35mHere the details about your bus ticket reservation:\033[0m");
        System.out.println("\n\033[1mBooking id : \033[34m" + bookingId + "\033[0m");
        System.out.println("\033[1mYou are travel from \033[34m" + fcity + "\033[0m\033[1m To \033[34m" + tcity + "\033[0m");
        System.out.println("\033[1mYou Booked Ticket on \033[34m" + bDate + "\033[0m\033[1m at \033[34m" + time + "\033[0m");
        System.out.println("\033[1mBus you Booked For your travel is \033[34m" + bname + "\033[0m\033[1m with \033[34m" + Ac + "\033[0m");
        System.out.println("\033[1mThe total cost of your bus booking is :₹ \033[34m" + rate + "\033[0m");
        SendEmail obj = new SendEmail(bookingId, fcity, tcity, time, bname, Ac, rate, bDate, cusName, email);
        obj.emailReceipt();
        System.out.println("\n\n\033[1m\033[36mThanks for choosing Apple Reservation Agency..........................!\033[0m");
        System.out.println("\n\033[1m\033[41mRedirecting to home page in 5 seconds......!\033[0m");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
    }

    public static void bookedReceipt(int bookingId, String cusName, String fcity, String tcity, String time, String bname, String Ac,
            int rate, String bDate) throws IOException {
        System.out.println("\n\033[1m\033[35mHere the details about your bus ticket reservation:\033[0m");
        System.out.println("\n\033[1mBooking id : \033[34m" + bookingId + "\033[0m");
        System.out.println("\033[1mCustomer Name:\033[34m" + cusName + "\033[0m");
        System.out.println("\033[1mYou are travel from \033[34m" + fcity + "\033[0m\033[1m To \033[34m" + tcity + "\033[0m");
        System.out.println("\033[1mYou Booked Ticket on \033[34m" + bDate + "\033[0m\033[1m at \033[34m" + time + "\033[0m");
        System.out.println("\033[1mBus you Booked For your travel is \033[34m" + bname + "\033[0m\033[1m with \033[34m" + Ac + "\033[0m");
        System.out.println("\033[1mThe total cost of your bus booking is :₹ \033[34m" + rate + "\033[0m");
    }
}
