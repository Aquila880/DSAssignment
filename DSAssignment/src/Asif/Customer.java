package Asif;

public class Customer {
    private String name, status;
    private int capacity, time;
    private double startlatitude, startlongitude;
    private double destlatitude, destlongitude;

    public Customer(String name, String status, int time, int capacity, double startlatitude, double startlongitude, double destlatitude, double destlongitude) {
        this.name = name;
        this.status = status;
        this.time = time;
        this.capacity = capacity;
        this.startlatitude = startlatitude;
        this.startlongitude = startlongitude;
        this.destlatitude = destlatitude;
        this.destlongitude = destlongitude;
    }
    
    public String print() {
        if (this.getName().length() == 3) {
            return this.getName() + "       " + this.getStatus() + "     " + this.getTime() + "                   " + this.getCapacity() + "         "
            + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
        }
        else if (this.getName().length() == 4) {
            return this.getName() + "      " + this.getStatus() + "     " + this.getTime() + "                   " + this.getCapacity() + "         "
            + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
        }
        else if (this.getName().length() == 5) {
            return this.getName() + "     " + this.getStatus() + "     " + this.getTime() + "                   " + this.getCapacity() + "         "
            + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
        }
        else if (this.getName().length() == 6) {
            return this.getName() + "    " + this.getStatus() + "     " + this.getTime() + "                   " + this.getCapacity() + "         "
            + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
        }
        else {
            return this.getName() + "   " + this.getStatus() + "     " + this.getTime() + "                   " + this.getCapacity() + "         "
            + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
        }
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getStartlatitude() {
        return startlatitude;
    }

    public void setStartlatitude(double startlatitude) {
        this.startlatitude = startlatitude;
    }

    public double getStartlongitude() {
        return startlongitude;
    }

    public void setStartlongitude(double startlongitude) {
        this.startlongitude = startlongitude;
    }

    public double getDestlatitude() {
        return destlatitude;
    }

    public void setDestlatitude(double destlatitude) {
        this.destlatitude = destlatitude;
    }

    public double getDestlongitude() {
        return destlongitude;
    }

    public void setDestlongitude(double destlongitude) {
        this.destlongitude = destlongitude;
    }
    
}
