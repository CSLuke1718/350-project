
package ambientclock;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Bryan Banuelos
 */
public class TimeConverter {
    
    
    public static int[] Convert(GoalData goal) {
    
        int  days, extraTime, hours,
        minutes, seconds;
        
        long unixTime = Instant.now().getEpochSecond();
        long diff =  goal.getDueDate() - unixTime;
        // Calculate total days unix time T
        days = (int)(diff / (24 * 60 * 60));
        extraTime = (int)(diff % (24 * 60 * 60));
        
        // Calculating HH:MM:YYYY
        hours = extraTime / 3600;
        minutes = (extraTime % 3600) / 60;
        seconds = (extraTime % 3600) % 60;
        
        int[] time = new int[4];
        
        time[0] = days;
        time[1] = hours;
        time[2] = minutes;
        time[3] = seconds;

        return time;
        
    }

}
