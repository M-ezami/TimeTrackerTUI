package TimeTrackerTui.Calender;

import java.time.LocalDateTime;

public record ToDoRecord(LocalDateTime creationTime, LocalDateTime startTime, String Title, Status status, Priority priority ) {

}

