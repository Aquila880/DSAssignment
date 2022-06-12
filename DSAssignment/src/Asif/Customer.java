package Asif;

public class Customer {
    private String name, status;
    // Number of passengers and (number of days passed since request)
    private int capacity, day; // to check the day the customer request was made
    // expected arrival time
    private long time;
    private double startlatitude, startlongitude;
    private double destlatitude, destlongitude;

    public Customer() {
        
    }

    public Customer(String name, String status, long time, int capacity, double startlatitude, double startlongitude, double destlatitude, double destlongitude) {
        this.name = name;
        this.status = status;
        this.time = time;
        this.capacity = capacity;
        this.startlatitude = startlatitude;
        this.startlongitude = startlongitude;
        this.destlatitude = destlatitude;
        this.destlongitude = destlongitude;
    }
    
    // Print customer information for admin dashboard
    public String dashInfo() {
        switch(this.getStatus()) {
            case "reached" :
                switch (this.getName().length()) {
                    case 2 :
                        return this.getName() + "        " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 3 :
                        return this.getName() + "       " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 4 :
                        return this.getName() + "      " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() 
                                    + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 5 :
                        return this.getName() + "     " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          "
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 6 :
                        return this.getName() + "    " + this.getStatus() + "      " + this.getTime() + "                   " +
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                }
            case "picked up" :
                switch (this.getName().length()) {
                    case 2:
                        return this.getName() + "        " + this.getStatus() + "    " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 3:
                        return this.getName() + "       " + this.getStatus() + "    " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 4:
                        return this.getName() + "      " + this.getStatus() + "    " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() 
                                    + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 5:
                        return this.getName() + "     " + this.getStatus() + "    " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          "
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 6:
                        return this.getName() + "    " + this.getStatus() + "    " + this.getTime() + "                   " +
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                }
            case "pending" :
                switch (this.getName().length()) {
                    case 2:
                        return this.getName() + "        " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 3:
                        return this.getName() + "       " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 4:
                        return this.getName() + "      " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() 
                                    + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 5:
                        return this.getName() + "     " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          "
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 6:
                        return this.getName() + "    " + this.getStatus() + "      " + this.getTime() + "                   " +
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                }
            case "waiting" :
                switch (this.getName().length()) {
                    case 2:
                        return this.getName() + "        " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 3:
                        return this.getName() + "       " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 4:
                        return this.getName() + "      " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() 
                                    + "          " + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 5:
                        return this.getName() + "     " + this.getStatus() + "      " + this.getTime() + "                   " + 
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          "
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                    case 6:
                        return this.getName() + "    " + this.getStatus() + "      " + this.getTime() + "                   " +
                                this.getCapacity() + "           " + this.getStartlatitude() + ", " + this.getStartlongitude() + "          " 
                                    + this.getDestlatitude() + " , " + this.getDestlongitude();
                }
        }
        
        return null;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
