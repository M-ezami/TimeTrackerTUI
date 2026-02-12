package TimeTrackerTui.task;


import java.time.LocalDateTime;

public record TaskRecord(int index, String title, LocalDateTime start, LocalDateTime end, String duration, int focusRating) {

}