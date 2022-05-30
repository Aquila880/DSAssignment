package Asif;

public class Customer {
    private String name, status;
    // private Faketime time;
    private int capacity;
    private long startlatitude, startlongitude;
    private long destlatitude, destlongitude;

    public Customer(String name, int capacity, long startlatitude, long startlongitude, long destlatitude, long destlongitude) {
        this.name = name;
        this.status = "waiting";
        this.capacity = capacity;
        this.startlatitude = startlatitude;
        this.startlongitude = startlongitude;
        this.destlatitude = destlatitude;
        this.destlongitude = destlongitude;
    }

    // Accessors and mutators
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getStartlatitude() {
        return startlatitude;
    }

    public void setStartlatitude(long startlatitude) {
        this.startlatitude = startlatitude;
    }

    public long getStartlongitude() {
        return startlongitude;
    }

    public void setStartlongitude(long startlongitude) {
        this.startlongitude = startlongitude;
    }

    public long getDestlatitude() {
        return destlatitude;
    }

    public void setDestlatitude(long destlatitude) {
        this.destlatitude = destlatitude;
    }

    public long getDestlongitude() {
        return destlongitude;
    }

    public void setDestlongitude(long destlongitude) {
        this.destlongitude = destlongitude;
    }
    
    
}
