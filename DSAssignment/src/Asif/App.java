package Asif;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
        LinkedList<Customer> cstmr = new LinkedList<>();
        LinkedList<Driver> drvr = new LinkedList<>();
        
        // Dashboard check
        /*cstmr.add(new Customer("Ray", "reached", 1450, 5, 3.1198, 101.6401, 3.1157, 101.6521));
        cstmr.add(new Customer("John", "picked up", 2215, 5, 3.1368, 101.6431, 3.1325, 101.6304));
        cstmr.add(new Customer("Adam", "pending", 1730, 4, 3.1157, 101.6304, 3.1325, 101.6626));
        cstmr.add(createCustomer("Kobe 1840 5 3.1368 101.6626 3.1134 101.6521"));
        cstmr.add(new Customer("Alphonse", "pending", 1730, 4, 3.1157, 101.6304, 3.1325, 101.6626));
       
        
        
        drvr.add(new Driver("Ralph", "available", 5, 3.1325, 101.6304));
        drvr.add(new Driver("Alfie", "available", 5, 3.1134, 101.6626));
        drvr.add(new Driver("Mara", "available", 5, 3.1157, 101.6521));*/
        
        // Load info from database
        // drvr = loadDriverData();
        // cstmr = loadCustomerData();
        // time.setDay(loadTime());
        
        // Main program
        main:
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
                    driverView(time, cstmr, drvr);
                    break;
                // Exit the program
                case "exit":
                    // storeDriverData(drvr);
                    // storeCustomerData(cstmr);
                    // storeTime(time);
                    break main;
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
            System.out.println("A - View System Dashboard");
            System.out.println("B - Enter Customer View");
            System.out.println("C - Add / Remove Driver\n");
            System.out.print(">> ");
            s = sc.next();
            if (s.equals("A") || s.equals("B") || s.equals("C") || s.equals("exit"))
                break;
        }
        
        return s;
    }
    
    
    // A - System dashboard with updated customer and driver information
    public static void sysDash(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        
        // List update function needs to be written here                        ~~~~~~
        long listtime = listUpdate(time, cstmr, drvr);
        
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
        while(true) {
            System.out.println("You are in customer view now (Enter \"exit\" to go back to homepage):");
            System.out.println("Options :");
            System.out.println("A - Create customer requests");
            System.out.println("B - Update customer requests");
            System.out.println("C - Rate a driver");
            System.out.println("D - Upgrade to premium");
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
                    break;
                }
            }
            
            // Update customer requests
            if (s.equals("B")) {
                customerViewB(time, cstmr, drvr);
                break;
            }
            
            // Rate a driver
            if (s.equals("C")) {
                customerViewC(cstmr, drvr);
                break;
            }
            
            // Upgrade to premium
            if (s.equals("D")) {
                premium(cstmr, drvr);
                break;
            }
        }
    }
    
    // BD - Upgrade to premium
    public static boolean premium(LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        Scanner sc = new Scanner(System.in);
        
        // Ask user for customer name
        System.out.println();
        System.out.println("You will now be upgraded to the premium service");
        System.out.println("Premium members get to cut the queue when updating their requests");
        System.out.println("All you need to do is sell your soul in service to the DEVIL!");
        System.out.println("Enter your name to continue or \"exit\" to remain a boring simpleton");
        System.out.print("Customer name: ");
        String s = sc.next();
        System.out.println();
        
        if (s.equals("exit")) return false;
        
        for (int i = 0; i < cstmr.getSize(); i++) {
            if (cstmr.get(i).getName().equals(s)) {
                cstmr.get(i).setPremium(true);
                return true;
            }
        }
        return false;
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
        
        boolean b = customerViewADrvCheck(time, cstmr, drvr, c);
        if (b == true) {
            return false;
        }
                
        return true;    
    }
    
    // Show customer driver availability
    public static boolean customerViewADrvCheck(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr, Customer c) {
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
        
        
            System.out.println("Driver Availability:");
            // List update function needs to be written here                        ~~~~~~
            long listtime = listUpdate(time, cstmr, drvr);
            System.out.printf("Requests List (List Last Updated Time : %04d\n", listtime); 
            System.out.printf("(Current time : %04d)\n", time.currentTime());
            long diff = time.convToSeconds(time.currentTime()) - time.convToSeconds(listtime);
            System.out.println("=================================================");
            System.out.println("Driver    Capacity    Estimated Arrival Time  Reputation");
        
            /* This only works if the time required to ferry the customer to destination is less than one day, the EAT format in
            the question doesn't allow for more than day of time */
            ArrayList<Integer> list = new ArrayList<>();
        
            for (int i = 0; i < drvr.getSize(); i++) {
                Driver d = drvr.get(i);
                    
                if (d.getStatus().equals("available")) {
                    if (d.getCapacity() >= capacity) {
                        dlat1 = d.getLatitude();
                        dlon1 = d.getLongitude();
                        
                        // Distance from driver to customer and from customer to destination
                        distance = distance(dlat1, custlat1, dlon1, custlon1) + distance(custlat1, custlat2, custlon1, custlon2);
                        
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
            boolean b = customerViewADrvPick(time, cstmr, drvr, c, list);
            if (b == false) {
                return true;
            }
        return false; 
    }
    
    // Let customer pick Driver
    public static boolean customerViewADrvPick(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr, Customer c, ArrayList<Integer> list) {
        Scanner sc = new Scanner(System.in);
        int y;
        
        // Customer info for checking available drivers
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
        long t1;
        
        System.out.println("Enter the number of the driver you want to select (Enter 0 to go back to previous menu):");
        System.out.print(">> ");
        
        // Take the number of driver to be removed from user
        int x = sc.nextInt();
        y = x - 1;
        System.out.println();
            
        // Exit back to previous menu
        if (x == 0) {
            cstmr.get(cstmr.getSize() - 1).setStatus("pending");
            return false;
        }
        
        try {
            // User inputs valid driver number to pick driver
            if (list.contains(y)) {
                Driver d = drvr.get(y);
                dlat1 = d.getLatitude();
                dlon1 = d.getLongitude();
                        
                // Distance from driver to customer and from customer to destination
                d1 = distance(dlat1, custlat1, dlon1, custlon1);
                distance = distance(dlat1, custlat1, dlon1, custlon1) + distance(custlat1, custlat2, custlon1, custlon2);
                
                // Time from driver to customer and from customer to destination
                t1 = (long) (d1 / d.getSpeed());
                t1 = time.convToSeconds(time.currentTime()) + t1;
                t1 = time.convToFormat(t1);
                DT = (long) (distance / d.getSpeed());
                DT = time.convToSeconds(time.currentTime()) + DT;
                DT = time.convToFormat(DT);
                
                // Set values for later when updating list
                int z = cstmr.indexOf(c);
                cstmr.get(z).setStatus("waiting");
                cstmr.get(z).setDriverindex(y);
                cstmr.get(z).setPickuptime(t1);
                cstmr.get(z).setDropofftime(DT);
                cstmr.get(z).setDay(time.getDay());
                drvr.get(y).setDropofftime(DT);
                drvr.get(y).setStatus("not available");
                drvr.get(y).setDestlat(custlat2);
                drvr.get(y).setDestlon(custlon2);
                drvr.get(y).setDay(time.getDay());
                drvr.get(y).getCustomer().setName(cstmr.get(z).getName());
                
                System.out.println("Driver " + x + " is on the way to pick you up.\n");
                
                // Return to prev menu
                TimeUnit.SECONDS.sleep(1);
                return false;
            }
        } catch(InterruptedException ex) {
            System.out.println("There was an interruption");
        }
        return true;
    }
    
    // BB - Update customer requests
    public static void customerViewB(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        // First, check list for premium customers
        for (int i = 0; i < cstmr.getSize(); i++) {
            if (cstmr.get(i).isPremium() == true) {
                if (cstmr.get(i).getStatus().equals("pending")) {
                    customerViewADrvCheck(time, cstmr, drvr, cstmr.get(i));
                    cstmr.get(i).setStatus("waiting");
                }
            }
        }
        
        // Then, for regular customers
        for (int i = 0; i < cstmr.getSize(); i++) {
            if (cstmr.get(i).isPremium() == false) {
                if (cstmr.get(i).getStatus().equals("pending")) {
                    customerViewADrvCheck(time, cstmr, drvr, cstmr.get(i));
                    cstmr.get(i).setStatus("waiting");
                }
            }
        }
    }
    
    // BC - Rate a driver
    public static boolean customerViewC(LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        Scanner sc = new Scanner(System.in);
        
        // Ask user for customer name
        System.out.println();
        System.out.print("Customer name: ");
        String s = sc.next();
        
        // Check if customer name exists in list
        for (int i = 0; i < cstmr.getSize(); i++) {
            if (cstmr.get(i).getName().equals(s)) {
                // Customer has not yet picked a driver. Index is still default value
                if (cstmr.get(i).getDriverindex() == 100) {
                    System.out.println("That customer has not yet picked a driver");
                    return false;
                }
                else {
                    // Let customer rate the driver
                    double d = -1;
                    System.out.print("Rate your driver out of 5: ");
                    while(d < 0 || d > 5) d = sc.nextDouble();
                    
                    // Driver index
                    int j = cstmr.get(i).getDriverindex();
                    
                    // Calculate new average
                    double sum = drvr.get(j).getRepsum() + d;
                    double count = drvr.get(j).getCount() + 1;
                    double avg = sum / count;
                    
                    // Set new values for the driver
                    drvr.get(j).setRepsum(sum);
                    drvr.get(j).setCount(count);
                    drvr.get(j).setRep(avg);
                    System.out.println("Thank you for your rating!");
                    return true;
                }
            }
        }
        System.out.println("That customer does not exist");
        return false;
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
    public static void driverView(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
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
                driverViewB(time, cstmr, drvr);
            }
        }
    }
    
    // CA - Add driver
    public static void driverViewA(LinkedList<Driver> drvr) {
        Scanner sc = new Scanner(System.in);
        Driver d;
        
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
    public static void driverViewB(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        Scanner sc = new Scanner(System.in);
        int x;
        
        // List update function needs to be written here                        ~~~~~~
        long listtime = listUpdate(time, cstmr, drvr);
        
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
                System.out.println("Please select a number from the list");
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
    
    public static long listUpdate(FakeTime time, LinkedList<Customer> cstmr, LinkedList<Driver> drvr) {
        for (int i = 0; i < drvr.getSize(); i++) {
            if (drvr.get(i).getStatus().equals("not available")) {
                time.currentTime();
                if (!(drvr.get(i).getDay() > time.getDay())) {
                    if (time.currentTime() >= drvr.get(i).getDropofftime()) {
                        drvr.get(i).setStatus("available");
                        drvr.get(i).setLatitude(drvr.get(i).getDestlat());
                        drvr.get(i).setLongitude(drvr.get(i).getDestlon());
                        drvr.get(i).getCustomer().setName("-");
                    }
                }
                else {
                    drvr.get(i).setStatus("available");
                    drvr.get(i).setLatitude(drvr.get(i).getDestlat());
                    drvr.get(i).setLongitude(drvr.get(i).getDestlon());
                }
            }
        }
        
        for (int i = 0; i < cstmr.getSize(); i++) {
            if (cstmr.get(i).getStatus().equals("waiting")) {
                time.currentTime();
                if (!(cstmr.get(i).getDay() > time.getDay())) {
                    if (time.currentTime() >= cstmr.get(i).getPickuptime() && time.currentTime() < cstmr.get(i).getDropofftime()) {
                        cstmr.get(i).setStatus("picked up");
                    }
                    else if (time.currentTime() >= cstmr.get(i).getDropofftime()) {
                        cstmr.get(i).setStatus("reached");
                    }
                } 
            }
            if (cstmr.get(i).getStatus().equals("picked up")) {
                time.currentTime();
                if(time.currentTime() >= cstmr.get(i).getDropofftime()) {
                    cstmr.get(i).setStatus("reached");
                }
            }
        }
        
        return time.currentTime();
    }
    
    // Method to clear textfile before writing
    public static void clear(String filename) {
        try {
            FileWriter fwOb = new FileWriter(filename, false); 
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException e) {
           System.out.println("An error occured.");
        }
    }
    
    /*public static void storeTime(FakeTime time) {
        try {
            clear("D:/Time.txt");
            FileWriter myWriter = new FileWriter("D:/Time.txt");
            // Write the time information line by line
            myWriter.write(time.getDay() + " ");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
        }
    }*/
    
    public static void storeDriverData(LinkedList<Driver> drvr) {
        try {
            clear("D:/Driver.txt");
            FileWriter myWriter = new FileWriter("D:/Driver.txt");
            // Write the information of each driver line by line
            for (int i = 0; i < drvr.getSize(); i++) {
                myWriter.write(drvr.get(i).getCapacity() + " " + drvr.get(i).getLatitude() + " " + drvr.get(i).getLongitude() + " " + drvr.get(i).getStatus() + " " + drvr.get(i).getName() + " " + drvr.get(i).getRep() + " " + drvr.get(i).getDropofftime() + " " + drvr.get(i).getDay() + " " + drvr.get(i).getDestlat() + " " + drvr.get(i).getDestlon() + " " + drvr.get(i).getRepsum() + " " + drvr.get(i).getCount() + " " + drvr.get(i).getCustomer().getName() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    public static void storeCustomerData(LinkedList<Customer> cstmr) {
        try {
            clear("D:/Customer.txt");
            FileWriter myWriter = new FileWriter("D:/Customer.txt");
            // Write the information of each customer line by line
            for (int i = 0; i < cstmr.getSize(); i++) {
                myWriter.write(cstmr.get(i).getName() + " " + cstmr.get(i).getStatus() + " " + cstmr.get(i).getCapacity() + " " + cstmr.get(i).getDay() + " " + cstmr.get(i).getTime() + " " + cstmr.get(i).getPickuptime() + " " + cstmr.get(i).getDropofftime() + " " + cstmr.get(i).getStartlatitude() + " " + cstmr.get(i).getStartlongitude() + " " + cstmr.get(i).getDestlatitude() + " " + cstmr.get(i).getDestlongitude() + " " + cstmr.get(i).getDriverindex() + " " + cstmr.get(i).isPremium() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    /*public static int loadTime() {
        // Store the day
        int day = 0;
        
        try {
            Scanner sc = new Scanner(new FileInputStream("D:/Time.txt"));
            
            while(sc.hasNextLine()) {
                String[] s = sc.nextLine().split(" ");
                day = Integer.parseInt(s[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }
        
        return day;
    }*/
    
    public static LinkedList<Driver> loadDriverData() {
        LinkedList<Driver> drvr = new LinkedList<>();
        
        // Driver variables
        int capacity;
        double latitude;
        double longitude;
        String status;
        String name;
        double rep;
        long dropofftime;
        int day;
        double destlat;
        double destlon;
        double repsum;
        double count;
        String custname;
        
        try {
            Scanner sc = new Scanner(new FileInputStream("D:/Driver.txt"));
            
            while(sc.hasNextLine()) {
                String[] s = sc.nextLine().split(" ");
                capacity = Integer.parseInt(s[0]);
                latitude = Double.parseDouble(s[1]);
                longitude = Double.parseDouble(s[2]);
                // Since split is " ", special case for not available status
                if (s[3].equals("not")) {
                    status = "not available";
                    name = s[5];
                    rep = Double.parseDouble(s[6]);
                    dropofftime = Long.parseLong(s[7]);
                    day = Integer.parseInt(s[8]);
                    destlat = Double.parseDouble(s[9]);
                    destlon = Double.parseDouble(s[10]);
                    repsum = Double.parseDouble(s[11]);
                    count = Double.parseDouble(s[12]);
                    custname = s[13];
                }
                else {
                    status = s[3];
                    name = s[4];
                    rep = Double.parseDouble(s[5]);
                    dropofftime = Long.parseLong(s[6]);
                    day = Integer.parseInt(s[7]);
                    destlat = Double.parseDouble(s[8]);
                    destlon = Double.parseDouble(s[9]); 
                    repsum = Double.parseDouble(s[10]);
                    count = Double.parseDouble(s[11]);
                    custname = s[12];
                }
                // Adds driver obj to linked list
                drvr.add(new Driver(capacity, latitude, longitude, status, name, rep, dropofftime, day, destlat, destlon, repsum, count, custname));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }
        
        return drvr;
    }
    
    public static LinkedList<Customer> loadCustomerData() {
        LinkedList<Customer> cstmr = new LinkedList<>();
        
        // Customer variables
        String name, status;
        int capacity, day;
        long time;
        long pickuptime;
        long dropofftime; 
        double startlatitude, startlongitude;
        double destlatitude, destlongitude;
        int driverindex;
        boolean premium;
        
        try {
           Scanner sc = new Scanner(new FileInputStream("D:/Customer.txt"));
           
           while (sc.hasNextLine()) {
               String[] s = sc.nextLine().split(" ");
               name = s[0];
               // Since split is " ", special case for picked up status
               if (s[1].equals("picked")) {
                   status = "picked up";
                   capacity = Integer.parseInt(s[3]);
                   day = Integer.parseInt(s[4]);
                   time = Long.parseLong(s[5]);
                   pickuptime = Long.parseLong(s[6]);
                   dropofftime = Long.parseLong(s[7]);
                   startlatitude = Double.parseDouble(s[8]);
                   startlongitude = Double.parseDouble(s[9]);
                   destlatitude = Double.parseDouble(s[10]);
                   destlongitude = Double.parseDouble(s[11]);
                   driverindex = Integer.parseInt(s[12]);
                   premium = Boolean.parseBoolean(s[13]);
               }
               else {
                   status = s[1];
                   capacity = Integer.parseInt(s[2]);
                   day = Integer.parseInt(s[3]);
                   time = Long.parseLong(s[4]);
                   pickuptime = Long.parseLong(s[5]);
                   dropofftime = Long.parseLong(s[6]);
                   startlatitude = Double.parseDouble(s[7]);
                   startlongitude = Double.parseDouble(s[8]);
                   destlatitude = Double.parseDouble(s[9]);
                   destlongitude = Double.parseDouble(s[10]);
                   driverindex = Integer.parseInt(s[11]);
                   premium = Boolean.parseBoolean(s[12]);
               }
               // Adds customer obj to linked list
               cstmr.add(new Customer(name, status, capacity, day, time, pickuptime, dropofftime, startlatitude, startlongitude, destlatitude, destlongitude, driverindex, premium));
           }
           sc.close();
        } catch (IOException e) {
            System.out.println("The file was not found");
        }
        
        return cstmr;
    }
}
