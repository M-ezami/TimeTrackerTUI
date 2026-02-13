package TimeTrackerTui.window;

import TimeTrackerTui.Controller.CalendarController;
import TimeTrackerTui.records.ToDoRecord;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;

import java.util.List;

public class CalendarWindow extends BasicWindow {

    public CalendarWindow(WindowManager gui, CalendarController calendarController) {
        super("Calendar");

        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        Table<String> table = new Table<>("Title", "Priority", "Status", "Due", "Created");

        List<ToDoRecord> tasks = calendarController.getToDos();

        for (ToDoRecord task : tasks) {
            table.getTableModel().addRow(
                    task.title(),
                    task.priority().toString(),
                    task.status().toString(),
                    task.dueBy().toString(),
                    task.creationTime().toString()
            );
        }

        panel.addComponent(table);
        panel.addComponent(new Button("Back", this::close));

        setComponent(panel);
    }
}
