package Asif;

public class Driver {
    private int capacity;
    private double latitude, longitude;
    private String status;
    private Customer customer;
    double speed = 1000; // m/s

    public Driver() {
    
    }
    
    public Driver(String status, int capacity, double latitude, double longitude) {
        this.status = status;
        this.capacity = capacity;
        this.latitude = latitude;
        this.longitude = longitude;
        customer = new Customer();
        customer.setName("-");
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
}
