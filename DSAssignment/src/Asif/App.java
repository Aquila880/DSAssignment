package Asif;

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    
    public static void main(String[] args) {
        
        // Display real world time in HourHourminuteminute format
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        LocalTime today = LocalTime.now();
        String realTime = today.format(format);
        
        long realstartTime = Long.parseLong(realTime); // program start real world time
        
        // This counts the time since program starts
        FakeTime time = new FakeTime(realstartTime);
        
        // Example customers and drivers
        LinkedList<Customer> cstmr = new LinkedList<Customer>();
        LinkedList<Driver> drvr = new LinkedList<Driver>();
        
        cstmr.add(new Customer("Ray", "reached", 1450, 5, 9.26, -78.31, 1.11, -91.23));
        cstmr.add(new Customer("John", "picked up", 1730, 5, 3.62, 42.91, 76.66, 5.1));
        cstmr.add(new Customer("Adam", "pending", 1730, 4, 31.62, 2.91, -76.66, 5.1));
        cstmr.add(new Customer("Kobe", "waiting", 1840, 5, -3.62, -42.91, 76.66, -5.1));
        
        drvr.add(new Driver("available", 5, 34.65, 9.12));
        drvr.add(new Driver("not available", 5, 3.65, 91.12));
        drvr.add(new Driver("available", 4, -34.23, 77.65));
        
        Timer timer = new Timer();
        
        // Main program
        while(true) {
            String s = homePage(time.currentTime());
        
            // Open menu according to input
            switch (s) {
                case "A":
                    SysDash(time, cstmr, drvr);
                    break;
                case "B":
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            CustomerView(time, cstmr, drvr);
                        }
                    }, 0, 5000);
                    break;
                default:
                    System.out.println("Driver overview");
                    break;
            }
        }
    }
    
    // ~~Methods~~
    // System dashboard with updated customer and driver information
    public static void SysDash(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
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
    
    public static void CustomerView(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        
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
    
    /* Haversian method to calculate the distance between two geographical coordinates
    It has been modified to not take elevation into consideration */
    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        
        return distance;
    }
}
