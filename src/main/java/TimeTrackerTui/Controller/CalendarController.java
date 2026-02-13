package TimeTrackerTui.Controller;

import TimeTrackerTui.dataLayer.CalendarManager;
import TimeTrackerTui.enums.Priority;
import TimeTrackerTui.enums.Status;
import TimeTrackerTui.records.ToDoRecord;
import java.time.LocalDateTime;
import java.util.List;

public class CalendarController {
    CalendarManager calendarManager;

    public CalendarController() {
       calendarManager = new CalendarManager();
    }

    public void createToDo(String title, Priority priority, String day, String Time) {
        LocalDateTime creationTime = LocalDateTime.now();
        Status status = Status.PENDING;
        LocalDateTime dueTime = buildDueTime(day, Time);
        ToDoRecord toDoRecord= new ToDoRecord(creationTime, dueTime, title, status, priority);
        calendarManager.addTask(toDoRecord);
    }

    private LocalDateTime buildDueTime(String Day, String time) {
        int days = Integer.parseInt(Day);
        LocalDateTime now = LocalDateTime.now();
        String[] parts = time.split(":");
        return LocalDateTime.of(now.getYear(), now.getMonth(), days,
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]));
    }

    public List<ToDoRecord> getToDos(){
       return calendarManager.getToDos();
    }


}
