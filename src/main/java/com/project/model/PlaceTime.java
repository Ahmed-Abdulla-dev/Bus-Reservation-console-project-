package com.project.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.project.dbcls.PlaceTimeDAO;

public class PlaceTime {

    public static void setPlaceTime() {
        int iterateCount = 0;
        System.out.println("\033[4m\033[1m\033[35mWELCOME TO APPLES TRANSPORT AGENCY\033[0m");
        System.out.println("\n\033[32m\033[1mThe Available Booking Cities are:\033[0m\n");
        Scanner scan = new Scanner(System.in);
        try {
            ResultSet city = PlaceTimeDAO.getCities();
            while (city.next()) {
                iterateCount++;
                System.out.println("\033[1mRoute: " + iterateCount + " => \033[34m" + city.getString(1) + " TO " + city.getString(2) + "\033[0m");
            }
            System.out.print("\n\033[1mChoose the route you want to travel:\033[0m");
            int routeId = scan.nextInt();
            scan.nextLine();
            if (routeId != 0 && routeId <= iterateCount) {
                System.out.println("\n\033[1m\033[33mThe dates available for the Booking are:\033[0m\n");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                List<LocalDate> nextSevenDays = List.of(
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(2),
                        LocalDate.now().plusDays(3),
                        LocalDate.now().plusDays(4),
                        LocalDate.now().plusDays(5),
                        LocalDate.now().plusDays(6),
                        LocalDate.now().plusDays(7)
                );
                for (LocalDate date : nextSevenDays) {
                    System.out.println("\033[34m\033[1m" + date.format(formatter) + "\033[0m");
                }
                System.out.print("\n\033[1mEnter the date you want to reserver ticket(dd-mm-yyyy):\033[0m");
                String date = scan.nextLine().trim();;
                LocalDate bDate = LocalDate.parse(date, formatter);
                boolean isValidDate = false;
                for (LocalDate n : nextSevenDays) {
                    if (bDate.equals(n)) {
                        Bus.displayBusInfo(routeId, bDate);
                        isValidDate = true;
                        break;
                    }
                }
                if (isValidDate == false) {
                    System.out.println("\n\033[31m\033[1mYou entered wrong Date .Please check and try again\033[0m\n");
                    setPlaceTime();
                }
            } else {
                System.out.println("\n\033[31m\033[1mYou entered wrong Route Id.Please check and try again\033[0m\n");
                setPlaceTime();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NoSuchElementException ex) {
            System.out.println(ex);
        }
    }
}
