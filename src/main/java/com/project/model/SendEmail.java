package com.project.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    private static int bookingId, rate;
    private static String fcity, tcity, time, bname, Ac, cusName, email;
    private static LocalDate bDate;
    private static final String USERNAME = "vipahmedabdulla76@gmail.com";
    private static final String PASSWORD = "hded obae aacn ucld";

    public SendEmail(int bookingId, String fcity, String tcity, String time, String bname, String Ac,
            int rate, LocalDate bDate, String cusName, String email) {
        SendEmail.bookingId = bookingId;
        SendEmail.rate = rate;
        SendEmail.fcity = fcity;
        SendEmail.tcity = tcity;
        SendEmail.time = time;
        SendEmail.bname = bname;
        SendEmail.cusName = cusName;
        SendEmail.Ac = Ac;
        SendEmail.email = email;
        SendEmail.bDate = bDate;
    }

    public void emailReceipt() throws IOException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Create the receipt file (for example, a simple text file)
            File receiptFile = createReceiptFile();

            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Booking Confirmation");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Dear " + cusName + ",\n\nYour Recent Bus Reservation via Apple Transport Agency is Successfully completed. Please find your receipt attached below .\n\nThank you for Choosing our Agency!");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is the attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(receiptFile);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(receiptFile.getName());
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("\n\n\033[7m\033[1mYour Booking confirmation Email will be send shortly!\033[0m");

            try {
                // Delete the temporary receipt file
                Files.delete(receiptFile.toPath());
            } catch (IOException ex) {
            }

        } catch (MessagingException e) {
            if (e instanceof MessagingException && e.getCause() instanceof UnknownHostException) {
                System.err.println("No internet connection.");
            } else {
                System.err.println("Error sending email: " + e.getMessage());
            }
        }
    }

    private static File createReceiptFile() throws IOException {
        // Create a temporary file for the receipt
        File receiptFile = File.createTempFile("receipt", ".txt");

        // Write some content to the receipt file (e.g., a simple text receipt)
        try (FileOutputStream fos = new FileOutputStream(receiptFile)) {
            String receiptContent = "\nHere the details about your bus ticket reservation:\n\nBooking id : " + bookingId + "\nCustomer Name:" + cusName + "\nYou are travel from " + fcity + " To " + tcity + "\nYou Booked Ticket on " + bDate + " at " + time + "\nBus you Booked For your travel is " + bname + " with " + Ac + "\nThe total cost of your bus booking is :₹ " + rate + "\n\nThanks for choosing Apple Reservation Agency..........................!\n";
            fos.write(receiptContent.getBytes());
        }

        return receiptFile;
    }
}
