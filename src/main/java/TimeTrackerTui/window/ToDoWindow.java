package TimeTrackerTui.window;

import TimeTrackerTui.Controller.CalendarController;
import com.googlecode.lanterna.gui2.*;

public class ToDoWindow extends BasicWindow {

    public ToDoWindow(WindowManager windowManager, CalendarController calendarController) {
        super("ToDo Menu");

        Panel panel = new Panel(new GridLayout(2));

        panel.addComponent(new Button("Create ToDo", () -> {
            this.close();
            windowManager.createToDoMenu();
        }));

        setComponent(panel);
    }
}
