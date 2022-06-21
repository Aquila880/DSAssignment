package Asif;

import java.util.Random;

public class Driver {
    private int capacity;
    private double latitude, longitude;
    private String status, name;
    private Customer customer;
    private double speed = 500; // m/s (m/minute in faketime)
    private double rep;
    private long dropofftime;
    private int day;
    private double destlat, destlon;
    // Keep track of reputation sum and total number of ratings
    private double repsum;
    private double count = 1;

    public Driver() {
    
    }
    
    public Driver(String name, String status, int capacity, double latitude, double longitude) {
        Random rand = new Random();
        this.name = name;
        this.status = status;
        this.capacity = capacity;
        this.latitude = latitude;
        this.longitude = longitude;
        customer = new Customer();
        customer.setName("-");
        this.rep = 4 + rand.nextDouble();
        this.repsum = this.rep;
    }

    public Driver(int capacity, double latitude, double longitude, String status, String name, double rep, long dropofftime, int day, double destlat, double destlon, double repsum, double count) {
        this.capacity = capacity;
        this.latitude = latitude;
        this.longitude = longitude;
        customer = new Customer();
        customer.setName("-");
        this.status = status;
        this.name = name;
        this.rep = rep;
        this.dropofftime = dropofftime;
        this.day = day;
        this.destlat = destlat;
        this.destlon = destlon;
        this.repsum = repsum;
        this.count = count;
    }
    
    // Print driver information for admin dashboard
    public String dashInfo() {
        switch(this.getStatus()) {
            case "available" :
                return this.getStatus() + "      " + this.getCapacity() + "          " + this.getLongitude() + ", " + this.getLatitude()
                        + "           " + this.getCustomer().getName();
            case "not available" :
                return this.getStatus() + "  " + this.getCapacity() + "          " + this.getLongitude() + ", " + this.getLatitude()
                        + "           " + this.getCustomer().getName();
        }
        return null;
    }
    
    // Accessors and mutators
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRep() {
        return rep;
    }

    public void setRep(double rep) {
        this.rep = rep;
    }

    public long getDropofftime() {
        return dropofftime;
    }

    public void setDropofftime(long dropofftime) {
        this.dropofftime = dropofftime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getDestlat() {
        return destlat;
    }

    public void setDestlat(double destlat) {
        this.destlat = destlat;
    }

    public double getDestlon() {
        return destlon;
    }

    public void setDestlon(double destlon) {
        this.destlon = destlon;
    }

    public double getRepsum() {
        return repsum;
    }

    public void setRepsum(double repsum) {
        this.repsum = repsum;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
