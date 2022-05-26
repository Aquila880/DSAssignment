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
        long elapsedTime = endTime - startTime;
        
        return elapsedTime / 1000000000;
    }
    
    public long currentTime() {
        long time;
        long temp = this.realStartTime + this.elapsedTime();
        
        return temp;
    }
}
