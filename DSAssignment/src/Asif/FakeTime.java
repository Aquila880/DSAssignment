package Asif;

/*The time calculated in this class is fake, so as to facilitate the presentation of this project
As such, 1 second = 1 minute, and 1 minute = 1 hour
Calculations will be done with above considerations but variables will be named according to real standards*/

public class FakeTime {
    private long startTime; // Program start time
    private long realStartTime; //real world start time
    private int day = 0;

    public FakeTime() {
    
    }
    
    // Initializes the program start time and real world start time
    public FakeTime(long realStartTime) {
        this.startTime = System.nanoTime();
        this.realStartTime = realStartTime;
    }
    
    // Finds the number of seconds passed since program start
    public long elapsedTime() {
        long endTime = System.nanoTime();
        long elapsedTime = endTime - this.startTime;
        
        return elapsedTime / 1000000000;
    }
    
    // This method returns the current time in the main program
    public long currentTime() {
        long time;
        
        time = convToSeconds(realStartTime) + elapsedTime();
        // Reset time after each day
        if (time >= 1440) {
            time = time % 1440;
            day++;
        }
        time = convToFormat(time);
        
        return time;
    }
    
    // Convert the time format (HHmm) in the main program to seconds (1s = 1min)
    public long convToSeconds(long time) {
        long seconds;
        long[] digits = new long[4];
        long hours, minutes; // These are actually minutes and seconds respectively
       
        // To check the number of digits
        if ((time / 1000) != 0) {
            // If 4 digits
            for (int i = 0; i < 4; i++) {
                digits[i] = time % 10;
                time = time / 10;
            }
       
            minutes = digits[0] + (digits[1] * 10); 
            hours = digits[2] + (digits[3] * 10);
        }
        else {
            // If 3 digits
            for (int i = 0; i < 3; i++) {
                digits[i] = time % 10;
                time = time / 10;
            }
            
            minutes = digits[0] + (digits[1] * 10);
            hours = digits[2];
        }
       
        seconds = minutes + (hours * 60);
       
        return seconds;
    }
    
    // Convert seconds (1s = 1min, 1 min = 1h) to HHmm time format (Format)
    public long convToFormat(long seconds) {
        long tformat = 0;
        
        long hours = (seconds % 3600) / 60; // Real world minutes
        long minutes = seconds % 60; // Real world seconds
        
        tformat = (hours * 100) + minutes;
        
        return tformat;
    }
    
    // Checks if Driving time is less than Expected Arrival Time
    // EAT is in format, while T is in seconds
    public boolean checkFormat(long EAT, long DT, long diff) {
        EAT = convToSeconds(EAT);
        DT = DT + diff; // The difference between list update time and customer request
        long currentTime = convToSeconds(currentTime());
        
        if (EAT < currentTime) {
            EAT = EAT + 1440;
        }
        
        if ((DT + currentTime) <= EAT) {
            return true;
        }
        
        return false;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
