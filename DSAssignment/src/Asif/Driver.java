package Asif;

public class Driver {
    private int number ,capacity;
    private long latitude, longitude;
    private String status, customer;
    
    public Driver(int number, int capacity, long latitude, long longitude) {
        this.number = number;
        this.status = "available";
        this.capacity = capacity;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // Accessors and mutators
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
