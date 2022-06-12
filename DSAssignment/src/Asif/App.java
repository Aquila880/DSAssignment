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
        
        // Dashboard check
        cstmr.add(new Customer("Ray", "reached", 1450, 5, 9.26, -78.31, 1.11, -91.23));
        cstmr.add(new Customer("John", "picked up", 1730, 5, 3.62, 42.91, 76.66, 5.1));
        cstmr.add(new Customer("Adam", "pending", 1730, 4, 31.62, 2.91, -76.66, 5.1));
        cstmr.add(new Customer("Kobe", "waiting", 1840, 5, -3.62, -42.91, 76.66, -5.1));
        
        drvr.add(new Driver("available", 5, 34.65, 9.12));
        drvr.add(new Driver("not available", 5, 3.65, 91.12));
        drvr.add(new Driver("available", 4, -34.23, 77.65));
        
        // Timer object to rerun method after specified time
        Timer timer = new Timer();
        double d = distance(9.12, 34.65, 9.15, 34.69);
        System.out.println(d);
        
        // Main program
        while(true) {
            String s = homePage(time.currentTime());
        
            // Open menu according to input
            switch (s) {
                case "A":
                    sysDash(time, cstmr, drvr);
                    break;
                case "B":
                    //timer.schedule(new TimerTask() {
                        //@Override
                        //public void run() {
                            customerView(time, cstmr, drvr);
                        //}
                    //}, 0, 5000);
                    break;
                default:
                    System.out.println("Driver overview");
                    break;
            }
        }
    }
    
    // ~~Methods~~
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
    
    
    // A - System dashboard with updated customer and driver information
    public static void sysDash(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        
        // List update function needs to be written here                        ~~~~~~
        long listtime = time.currentTime();
        
        // Customer requests list
        System.out.printf("Requests List (List Last Updated Time : %04d\n", listtime); 
        System.out.printf("(Current time : %04d)\n", time.currentTime());
        System.out.println("======================================================================================================");
        System.out.println("Customer  Status       Expected Arrival Time  Capacity    Starting Point       Destination");
        for (int i = 0; i < cstmr.getSize(); i++) {
            System.out.println(cstmr.get(i).dashInfo());
        }
        System.out.println("======================================================================================================\n\n");
        
        // Drivers list
        System.out.printf("Requests List (List Last Updated Time : %04d\n", listtime); 
        System.out.printf("(Current time : %04d)\n", time.currentTime());
        System.out.println("=====================================================================");
        System.out.println("Driver A   Status         Capacity   Location            Customer");
        for (int i = 0; i < drvr.getSize(); i++) {
            System.out.println("Driver " + (i + 1) + "   " + drvr.get(i).dashInfo());
        }
        System.out.println("=====================================================================\n");
    }
    
    // B - Customer View related section
    public static void customerView(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        main:
        while(true) {
            System.out.println("You are in customer view now (Enter \"exit\" to go back to homepage):");
            System.out.println("Options :");
            System.out.println("A - Create customer requests");
            System.out.println("B - Update customer requests");
            System.out.print(">> ");
            
            Scanner sc = new Scanner(System.in);
            String s = sc.next();
            System.out.println();
            
            // Exit back to homepage
            if (s.equals("exit")) break;
            
            // Create customer requests
            if (s.equals("A")) {
                boolean b = customerViewA(time, cstmr, drvr);
                if (b == false) {
                    break main;
                }
            }
        }
    }
    
    // BA - Create customer requests
    public static boolean customerViewA(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        Customer c = new Customer();
        while(true) {
            System.out.println("Enter the details of the customer you want to create (name, Expected arrival time, capacity, starting point, destination)");
            System.out.println("(Enter \"exit\" to go back to homepage):");
            System.out.print(">> ");
                    
            // Take customer info from user
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            System.out.println();
                    
            // Exit back to homepage
            if (s.equals("exit")) return false; 
                    
            // Create customer if user inputs valid customer and add to linkedlist
            if (createCustomerCheck(s)) {
                c = createCustomer(s);
                cstmr.addLast(c);
                break;
            }
        }
                
        System.out.println();
        System.out.println("The request is received, please choose your driver...");
        System.out.println();
        
        customerViewDrvCheck(time, cstmr, drvr, c);
                
        return true;    
    }
    
    // Check driver availability
    public static void customerViewDrvCheck(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr, Customer c) {
        // Customer info for checking available drivers
        int capacity = c.getCapacity();
        long EAT = c.getTime();
        double custlat1 = c.getStartlatitude();
        double custlon1 = c.getStartlongitude();
        double custlat2 = c.getDestlatitude();
        double custlon2 = c.getDestlongitude();
        
        // To store driver info
        double dlat1;
        double dlon1;
        double distance;
        long DT;
                   
        /* This only works if the time required to ferry the customer to destination is less than one day, the EAT format in
        the question doesn't allow for more than day of time */
        for (int i = 0; i < drvr.getSize(); i++) {
            Driver d = drvr.get(i);
                    
            if (d.getCapacity() >= capacity) {
                dlat1 = d.getLatitude();
                dlon1 = d.getLongitude();
                        
                // Distance from driver to customer and from customer to destination
                distance = distance(dlat1, dlon1, custlat1, custlon1) + distance(custlat1, custlon1, custlat2, custlon2);
                System.out.println(distance);
                        
                DT = (long) (distance / d.getSpeed());
                System.out.println(DT);
                        
                /*if (!(DT > 1440)) {
                    if (time.checkFormat(EAT, DT)) {
                        
                    }
                } */
            }
        }
    }
    
    // This could use some work                                                 ~~~~~
    public static boolean createCustomerCheck(String s) {
        String[] string = s.split(" ");
        // >> John 1730 5 latitude, longitude, latitude, longitude
        
        if (string.length == 7) {
            String name = string[0];
            long time = Long.parseLong(string[1]);
            int capacity = Integer.parseInt(string[2]);
            
            double lat1 = Double.parseDouble(string[3]);
            double lon1 = Double.parseDouble(string[4]);
            
            double lat2 = Double.parseDouble(string[5]);
            double lon2 = Double.parseDouble(string[6]);
            
            if (time / 10000 == 0) {
                if ((lat1 <= 90 && lat1 >= -90) && (lon1 <= 180 && lon1 >= -180)) {
                    if ((lat2 <= 90 && lat2 >= -90) && (lon2 <= 180 && lon2 >= -180)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public static Customer createCustomer(String s) {
        Customer c;
        String[] string = s.split(" ");
        String name = string[0];
        long time = Long.parseLong(string[1]);
        int capacity = Integer.parseInt(string[2]);
            
        double lat1 = Double.parseDouble(string[3]);
        double lon1 = Double.parseDouble(string[4]);
            
        double lat2 = Double.parseDouble(string[5]);
        double lon2 = Double.parseDouble(string[6]);
        
        c = new Customer(name, "waiting", time, capacity, lat1, lon1, lat2, lon2);
        
        return c;
    }
    
    /* Haversine method to calculate the distance between two geographical coordinates
    It has been modified to not take elevation into consideration
    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; 
        
        return distance; // Returned in km
    } */
    
    // Spherical law of cosines to calculate distance between two geographical coordinates
    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        double distance = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2-lon1)) * 6371;
        
        return distance;
    }
}
