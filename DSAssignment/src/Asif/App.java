package Asif;

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class App {

    public static void main(String[] args) {
        
        // Display real world time in HourHourminuteminute format
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        LocalTime today = LocalTime.now();
        String realTime = today.format(format);
        
        long realstartTime = Long.parseLong(realTime); // program start real world time
        
        // This counts the time since program starts
        FakeTime time = new FakeTime(realstartTime);
        
        // Home Page
        System.out.println("Welcome to Customer always right E-hailing Application!");
        System.out.println("Options :");
        System.out.printf("Current time : %04d\n", realstartTime);
        
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
        
        // Open menu according to input
        if (s.equals("A")) {
            System.out.printf("%04d\n", time.currentTime());
            System.out.println("System dashboard");
        }
        else if (s.equals("B")) {
            System.out.println("Customer View");
        }
        else {
            System.out.println("Driver overview");
        }
    }
}
