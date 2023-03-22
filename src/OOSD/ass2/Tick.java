package OOSD.ass2;

/** Class to keep track of time passed while the game runs
 *  refreshes the input function according to the tickValue
 */
public class Tick {
    // tickValue is the rate at which the stimulation refreshes
    // count is the total no of complete ticks since the start of the program
    public final double tickValue;
    public final int maxTick;
    public static int count=0;
    public long lastTime=System.currentTimeMillis();
    public long currTime=0;
    public long timePassed=0;
    public boolean tickComplete = false;

    public Tick(double tick, int maxTick) {
        this.tickValue = tick;
        this.maxTick = maxTick;
    }

    public void run(){
        if(currTime==0){
            currTime=System.currentTimeMillis();
            timePassed+=(currTime-lastTime);
        }
        else if(currTime>0){
            lastTime=currTime;
            currTime=System.currentTimeMillis();
            timePassed+=(currTime-lastTime);
        }

        if(timePassed>=tickValue){
            count++;
            timePassed=0;
            tickComplete =true;
        }
        else{
            tickComplete=false;
        }

    }
}
