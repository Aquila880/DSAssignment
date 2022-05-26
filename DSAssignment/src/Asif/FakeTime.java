package Asif;

public class FakeTime {
    private long startTime;
    private long realStartTime;
    
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
        long time = 0;
        
        long seconds = convToSeconds(realStartTime) + elapsedTime();
        // long hours = Math.round(Math.floor(seconds / 3600));
        // long minutes = Math.round(Math.floor((seconds % 3600) / 60));
        
        /*long[] digits = new long[4];
        int x = 0;
        int y = 2;
        
        while (hours > 0) {
            digits[x] = hours % 10;
            hours = hours / 10;
            x++;
        }
        while (minutes > 0) {
            digits[y]  = minutes % 10;
            minutes = minutes / 10;
            y++;
        }
        
        time = (digits[1] * 1000) + (digits[0] * 100) + (digits[3] * 10) + (digits[2] * 1);*/
        
        return seconds;
    }
    
    // This converts the time format in the main program/realStartTime to seconds
    public long convToSeconds(long time) {
       long seconds;
       long[] digits = new long[4];
       
       for (int i = 0; i < 4; i++) {
           digits[i] = time % 10;
           time = time / 10;
       }
       
       long minutes = digits[0] + (digits[1] * 10); 
       long hours = digits[2] + (digits[3] * 10);
       
       seconds = (minutes * 60) + (hours * 3600);
       
       return seconds;
    }
}
