package TimeTrackerTui.helpers;

import java.time.LocalDateTime;
import java.time.Duration;
public class Timer {

    private LocalDateTime sessionStart;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration timeSpend;
    private boolean isRunning;
    private LocalDateTime pauseStartTime;
    private Duration totalPausedTime;

    public Timer() {
        this.sessionStart = null;
        this.startTime = null;
        this.endTime = null;
        this.timeSpend = Duration.ZERO;
        this.isRunning = false;
        totalPausedTime = Duration.ZERO;
        this.pauseStartTime = null;
    }

    public void start() {
        sessionStart = LocalDateTime.now();
        startTime = sessionStart;
        endTime = null;
        timeSpend = Duration.ZERO;
        isRunning = true;
    }

    public void pause() {
        if (isRunning) {
            timeSpend = timeSpend.plus(Duration.between(startTime, LocalDateTime.now()));
            pauseStartTime = LocalDateTime.now();
            isRunning = false;

        }
    }

    public void resume() {
        if (!isRunning ) {
            totalPausedTime = totalPausedTime.plus(Duration.between(pauseStartTime, LocalDateTime.now()));
            startTime = LocalDateTime.now();
            pauseStartTime = null;
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            timeSpend = timeSpend.plus(Duration.between(startTime, LocalDateTime.now()));
            isRunning = false;
        }
        endTime = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return sessionStart;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public Duration getTimeSpend() {
        if (isRunning) {
            return timeSpend.plus(Duration.between(startTime, LocalDateTime.now()));
        } else {
            return timeSpend;
        }
    }
    public String getTimeSpendFormatted() {
        long hours = getTimeSpend().toHours();
        long minutes = getTimeSpend().toMinutes() % 60;
        long seconds = getTimeSpend().getSeconds() % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

