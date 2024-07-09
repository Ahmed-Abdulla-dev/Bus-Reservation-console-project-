package com.project.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.project.dbcls.BusDAO;

public class Bus {

    public static void displayBusInfo(int routeId, LocalDate bDate) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\n\033[32m\033[1mBased on your route. The available Buses are:\033[0m\n");
            ResultSet buses = BusDAO.getBuses(routeId);
            List<Integer> bId = new ArrayList<>();
            int i = -1;
            System.out.println("\033[4m\033[1m\033[35m  Bus Number  |" + "   Bus Name   |" + "  Ac/Non-Ac  \033[0m");
            while (buses.next()) {
                bId.add(buses.getInt(1));
                System.out.println("\033[34m\033[1m     " + buses.getInt(1) + "        " + buses.getString(2) + "       " + buses.getString(3) + "\033[0m");
                i++;
            }
            System.out.print("\n\033[1mEnter the bus Number you want to book:\033[0m");
            int busNo = scanner.nextInt();
            scanner.nextLine();
            if (busNo != 0 && busNo == bId.get(0) || busNo <= bId.get(i)) {
                available.isAvailable(routeId, bDate, busNo);
            } else {
                System.out.println("\n\033[31m\033[1mPlease Enter a valid bus No!\033[0m\n");
                displayBusInfo(routeId, bDate);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NoSuchElementException ex) {
            System.out.println(ex);
        }
    }
}
