package Asif;

public class Driver {
    private int capacity;
    private long latitude, longitude;
    private String status;
    private Customer customer;
    
    public Driver(String status, int capacity, long latitude, long longitude) {
        this.status = status;
        this.capacity = capacity;
        this.latitude = latitude;
        this.longitude = longitude;
        customer = new Customer();
        customer.setName("-");
    }
    
    @Override
    public String toString() {
        switch(this.getStatus()) {
            case "available" :
                return this.getStatus() + "    " + this.getCapacity() + "          " + this.getLongitude() + ", " + this.getLatitude()
                        + "     " + this.getCustomer().getName();
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
