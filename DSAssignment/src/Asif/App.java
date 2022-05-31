package Asif;

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class App {
    
    // Main program
    public static void main(String[] args) {
        
        // Display real world time in HourHourminuteminute format
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        LocalTime today = LocalTime.now();
        String realTime = today.format(format);
        
        long realstartTime = Long.parseLong(realTime); // program start real world time
        
        // This counts the time since program starts
        FakeTime time = new FakeTime(realstartTime);
        
        LinkedList<Customer> cstmr = new LinkedList<Customer>();
        LinkedList<Driver> drvr = new LinkedList<Driver>();
        
        cstmr.add(new Customer("Ray", "reached", 1450, 5, 9.26, -78.31, 1.11, -91.23));
        cstmr.add(new Customer("John", "picked up", 1730, 5, 3.62, 42.91, 76.66, 5.1));
        cstmr.add(new Customer("Adam", "pending", 1730, 4, 31.62, 2.91, -76.66, 5.1));
        cstmr.add(new Customer("Kobe", "waiting", 1840, 5, -3.62, -42.91, 76.66, -5.1));
        
        drvr.add(new Driver("available", 5, 34.65, 9.12));
        drvr.add(new Driver("not available", 5, 3.65, 91.12));
        drvr.add(new Driver("available", 4, -34.23, 77.65));
        
        while(true) {
            String s = homePage(time.currentTime());
        
            // Open menu according to input
            if (s.equals("A")) {
                sysDash(time, cstmr, drvr);
            }
            else if (s.equals("B")) {
                System.out.println("Customer View");
            }
            else {
                System.out.println("Driver overview");
            }
        }
    }
    
    // Methods
    // System dashboard with updated customer and driver information
    public static void sysDash(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        long listtime = time.currentTime();
        // Customer requests list
        System.out.printf("Requests List (List Last Updated Time : %04d\n", listtime); 
        System.out.printf("(Current time : %04d)\n", time.currentTime());
        System.out.println("======================================================================================================");
        System.out.println("Customer  Status       Expected Arrival Time  Capacity    Starting Point       Destination");
        for (int i = 0; i < cstmr.getSize(); i++) {
            System.out.println(cstmr.get(i).toString());
        }
        System.out.println("======================================================================================================\n\n");
        
        // Drivers list
        System.out.printf("Requests List (List Last Updated Time : %04d\n", listtime); 
        System.out.printf("(Current time : %04d)\n", time.currentTime());
        System.out.println("=====================================================================");
        System.out.println("Driver A   Status         Capacity   Location            Customer");
        for (int i = 0; i < drvr.getSize(); i++) {
            System.out.println("Driver " + (i + 1) + "   " + drvr.get(i).toString());
        }
        System.out.println("=====================================================================\n");
    }
    
    public static String homePage(long time) {
        // Home Page
        System.out.println("Welcome to Customer always right E-hailing Application!");
        System.out.println("Options :");
        System.out.printf("(Current time : %04d)\n", time);
        
        // Ask for user input
        Scanner sc = new Scanner(System.in);
        String s = "";
        
        while (true) {
            System.out.println("A - View System Dashboad");
            System.out.println("B - Enter Customer View");
            System.out.println("C - Add / Remove Driver\n");
            System.out.print(">> ");
            s = sc.next();
            if (s.equals("A") || s.equals("B") || s.equals("C"))
                break;
        }
        
        return s;
    }
}
