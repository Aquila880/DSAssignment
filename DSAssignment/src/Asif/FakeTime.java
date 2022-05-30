package Asif;

/*The time calculated in this class is fake, so as to facilitate the presentation of this project
As such, 1 second = 1 minute, and 1 minute = 1 hour
Calculations will be done with above considerations but variables will be named according to real standards*/

public class FakeTime {
    private long startTime; // Program start time
    private long realStartTime; //real world start time
    
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
        
        return time;
    }
    
    // Convert the time format (HHmm) in the main program to seconds (1s = 1min)
    public long convToSeconds(long time) {
        long seconds;
        long[] digits = new long[4];
        long hours = 0, minutes = 0;
       
        if ((time / 1000) != 0) {
            for (int i = 0; i < 4; i++) {
                digits[i] = time % 10;
                time = time / 10;
            }
       
            minutes = digits[0] + (digits[1] * 10); 
            hours = digits[2] + (digits[3] * 10);
        }
        else {
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
    
    // Convert seconds (1s = 1min, 1 min = 1h) to HHmm time format (TFormat)
    public long convToTFormat(long seconds) {
        long tformat = 0;
        
        long hours = (seconds % 3600) / 60;
        long minutes = seconds % 60;
        
        tformat = (hours * 100) + minutes;
        
        return tformat;
    }
}
