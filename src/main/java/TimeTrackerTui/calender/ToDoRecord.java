package TimeTrackerTui.Calender;

import java.time.LocalDateTime;

public record ToDoRecord(LocalDateTime creationTime, LocalDateTime dueBy, String title, Status status, Priority priority ) {

}

