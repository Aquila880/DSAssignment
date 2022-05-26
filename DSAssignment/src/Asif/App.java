package Asif;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        // Setup application start time
        long startTime = 0;
        startTime = System.nanoTime();
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Home Page
        System.out.println("Welcome to Customer always right E-hailing Application!");
        System.out.println("Options :");
        System.out.println("Current time : " + TimeElapsed(startTime));
        
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
        
        if (s.equals("A")) {
            System.out.println("System dashboard");
        }
        else if (s.equals("B")) {
            System.out.println("Customer View");
        }
        else {
            System.out.println("Driver overview");
        }
    }
    
    public static long TimeElapsed(long startTime) {
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        
        return elapsedTime;
    }
}
