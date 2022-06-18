package Asif;

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
        
        drvr.add(new Driver("Ralph", "available", 5, 3.1325, 101.6304));
        drvr.add(new Driver("Alfie", "not available", 5, 3.1134, 101.6626));
        drvr.add(new Driver("Mara", "available", 4, 3.1157, 101.6521));
        
        // Timer object to rerun method after specified time
        Timer timer = new Timer();
        
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
                case "C":
                    driverView(time, drvr);
                    break;
                default:
                    System.out.println("Enter A, B or C");
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
            // >> John 1730 5 latitude longitude latitude longitude
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
    
    // Show customer driver availability
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
        
        // Pickup and dropoff distances/times
        double d1;
        double d2;
        long t1 = 0;
        long t2 = 0;
        
        System.out.println("Driver Availability:");
        // List update function needs to be written here                        ~~~~~~
        long listtime = time.currentTime();
        System.out.printf("Requests List (List Last Updated Time : %04d\n", listtime); 
        System.out.printf("(Current time : %04d)\n", time.currentTime());
        long diff = time.convToSeconds(time.currentTime()) - time.convToSeconds(listtime);
        System.out.println("=================================================");
        System.out.println("Driver    Capacity    Estimated Arrival Time  Reputation");
        
        /* This only works if the time required to ferry the customer to destination is less than one day, the EAT format in
        the question doesn't allow for more than day of time */
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for (int i = 0; i < drvr.getSize(); i++) {
            Driver d = drvr.get(i);
                    
            if (d.getStatus().equals("available")) {
                if (d.getCapacity() >= capacity) {
                    dlat1 = d.getLatitude();
                    dlon1 = d.getLongitude();
                        
                    // Distance from driver to customer and from customer to destination
                    d1 = distance(dlat1, custlat1, dlon1, custlon1);
                    d2 = distance(custlat1, custlat2, custlon1, custlon2);
                    distance = distance(dlat1, custlat1, dlon1, custlon1) + distance(custlat1, custlat2, custlon1, custlon2);
                    
                    t1 = (long) (d1 / d.getSpeed());
                    t1 = time.convToSeconds(time.currentTime()) + t1;
                    t1 = time.convToFormat(t1);
                    t2 = (long) (d1 / d.getSpeed());
                    t2 = time.convToSeconds(time.currentTime()) + t2;
                    t2 = time.convToFormat(t2);
                    DT = (long) (distance / d.getSpeed());
                    
                    if (!(DT > 1440)) {
                        if (time.checkFormat(EAT, DT, diff)) {
                            DT = time.convToSeconds(time.currentTime()) + DT;
                            DT = time.convToFormat(DT);
                            System.out.printf("Driver " + (i + 1) + "  " + drvr.get(i).getCapacity() + "           " + DT + "                    %.1f/5.0\n", drvr.get(i).getRep());
                            list.add(i);
                        }
                    }
                }    
            }
        }
        
        customerViewDrvPick(time, cstmr, drvr, c, t1, t2);
    }
    
    // Let customer pick Driver
    public static void customerViewDrvPick(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr, Customer c, long t1, long t2) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch(InterruptedException ex) {
          ex.printStackTrace();
        }
    }   
    
    // These two methods could use some work                                    ~~~~~
    public static boolean createCustomerCheck(String s) {
        String[] string = s.split(" ");
        // >> John 1730 5 latitude longitude latitude longitude
        
        if (string.length == 7) {
            long time = Long.parseLong(string[1]);
            
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
    
    // C - Add / Remove Driver
    public static void driverView(FakeTime time, LinkedList<Driver> drvr) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("Are you trying to add or remove a driver? (Enter \"exit\" to go back to homepage):");
            System.out.println("Options:");
            System.out.println("A - Add new driver");
            System.out.println("B - Remove driver");
            System.out.println();
            
            String s = sc.next();
            
            // Exit back to homepage
            if (s.equals("exit")) break;
            
            if (s.equals("A")) {
                driverViewA(drvr);
            }
            
            if (s.equals("B")) {
                System.out.println();
                driverViewB(time, drvr);
            }
        }
    }
    
    // CA - Add driver
    public static void driverViewA(LinkedList<Driver> drvr) {
        Scanner sc = new Scanner(System.in);
        Driver d = new Driver();
        
        while(true) {
            // Take driver info from user
            System.out.println("Enter the details of the driver you want to create (name, capacity, location):"); // John 5 latitude longitude
            String s = sc.nextLine();
            System.out.println();
        
            if (createDriverCheck(s)) {
                d = createDriver(s);
                drvr.addLast(d);
                System.out.println("Driver is successfully registered!\n");
                break;
            }
        }
    }
    
    public static boolean createDriverCheck(String s) {
        String[] string = s.split(" ");
        // >> John 5 latitude longitude
        
        if (string.length == 4) {
            String name = string[0];
            int capacity = Integer.parseInt(string[1]);
            
            double lat1 = Double.parseDouble(string[2]);
            double lon1 = Double.parseDouble(string[3]);
            
            if ((lat1 <= 90 && lat1 >= -90) && (lon1 <= 180 && lon1 >= -180)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static Driver createDriver(String s) {
        Driver d;
        String[] string = s.split(" ");
        
        String name = string[0];
        int capacity = Integer.parseInt(string[1]);
            
        double lat1 = Double.parseDouble(string[2]);
        double lon1 = Double.parseDouble(string[3]);
        
        d = new Driver(name, "available", capacity, lat1, lon1);
        
        return d;
    }
    
    // CB - Remove driver
    public static void driverViewB(FakeTime time, LinkedList<Driver> drvr) {
        Scanner sc = new Scanner(System.in);
        int x;
        
        // List update function needs to be written here                        ~~~~~~
        long listtime = time.currentTime();
        
        while(true) {
            // Drivers list
            System.out.printf("Requests List (List Last Updated Time : %04d\n", listtime); 
            System.out.printf("(Current time : %04d)\n", time.currentTime());
            System.out.println("=====================================================================");
            System.out.println("Driver A   Status         Capacity   Location            Customer");
            for (int i = 0; i < drvr.getSize(); i++) {
                System.out.println("Driver " + (i + 1) + "   " + drvr.get(i).dashInfo());
            }
            System.out.println("=====================================================================\n");
            
            System.out.println("Enter the number of the driver you want to delete (Enter 0 to go back to previous menu):");
            System.out.print(">> ");
            
            // Take the number of driver to be removed from user
            x = sc.nextInt();
            System.out.println();
            
            // Exit back to homepage
            if (x == 0) {
                break;
            }
            
            // User inputs valid driver number to be removed
            if (x > 0 && x <= drvr.getSize()) {
                drvr.remove(x - 1);
                System.out.println("Driver " + x + " has been removed.");
                System.out.println();
            }
            else {
                System.out.println("Please select a driver number in the list");
            }
        }
    }
    
    /* Haversine method to calculate the distance between two geographical coordinates
    It has been modified to not take elevation into consideration */
    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // Returned in meters
        
        return distance;
    }
    
    /* Spherical law of cosines to calculate distance between two geographical coordinates
    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        double distance = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2-lon1)) * 6371;
        
        return distance;
    } */
}
