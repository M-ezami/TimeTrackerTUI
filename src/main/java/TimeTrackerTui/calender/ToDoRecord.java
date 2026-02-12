package TimeTrackerTui.calender;

import java.time.LocalDateTime;
import java.util.Comparator;

public record ToDoRecord(LocalDateTime creationTime, LocalDateTime dueBy, String title, Status status, Priority priority ) {

    public static final Comparator<ToDoRecord> BY_DUE_DATE =
            Comparator.comparing(ToDoRecord::dueBy);

    public static final Comparator<ToDoRecord> BY_DUE_DATE_REV =
            Comparator.comparing(ToDoRecord::dueBy).reversed();
}


