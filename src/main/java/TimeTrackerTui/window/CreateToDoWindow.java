package TimeTrackerTui.window;

import TimeTrackerTui.Controller.CalendarController;
import TimeTrackerTui.enums.Priority;
import com.googlecode.lanterna.gui2.*;

public class CreateToDoWindow extends BasicWindow {

    public CreateToDoWindow(WindowManager windowManager, CalendarController calendarController) {
        super("Create ToDo");

        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        // In case we want to center something panel.addComponent(dateBox,dueDateDayLabel, LinearLayout.createLayoutData(LinearLayout.Alignment.Center));
        panel.addComponent(new Label("Task Name"));
        TextBox titleBox = new TextBox();
        panel.addComponent(titleBox);

        panel.addComponent(new Label("Due Day of Month"));
        TextBox dayBox = new TextBox();
        panel.addComponent(dayBox);

        panel.addComponent(new Label("Due Time (HH:MM)"));
        TextBox timeBox = new TextBox();
        panel.addComponent(timeBox);

        panel.addComponent(new Label("Priority"));
        TextBox priorityBox = new TextBox();
        panel.addComponent(priorityBox);


        panel.addComponent(new Button("Save", () -> {
            String title = titleBox.getText();
            String day = dayBox.getText();
            String time = timeBox.getText();
            String priorityStr = priorityBox.getText().toUpperCase();

            calendarController.createToDo(title, Priority.valueOf(priorityStr), day, time);
            this.close();
            windowManager.calanderMenu();
        }));

        setComponent(panel);
    }
}
