package TimeTrackerTui;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Time {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration timeSpend;

    public Time() {
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.timeSpend = Duration.ZERO;
    }

    public void start() {
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.timeSpend = Duration.ZERO;
    }

    public void pause() {
    	
    }

    public void stop() {
        this.endTime = LocalDateTime.now();
        this.timeSpend = Duration.between(startTime, endTime);
    }
    public LocalDateTime getStartTime() {
    	return startTime;
    }
    
    public LocalDateTime getEndTime() {
    	return endTime;
    }

    public long getHoursSpend() {
        return getTimeSpend().toHours();
    }

    public long getMinutesSpend() {
        return getTimeSpend().toMinutes() % 60;
    }

    public long getSecondsSpend() {
        return getTimeSpend().getSeconds() % 60;
    }
    
    public Duration getTimeSpend() {
        if (endTime != null) {
            return timeSpend;
        } else {
            return Duration.between(startTime, LocalDateTime.now());
        }
    }

    public String getTimeSpendFormatted() {
        
        return String.format("%02d:%02d:%02d", getHoursSpend(), getMinutesSpend(), getSecondsSpend());
    }

    public void printDuration() {
        System.out.println(getTimeSpendFormatted());
    }

    public void printStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Start time: " + startTime.format(formatter));
    }

    
}
