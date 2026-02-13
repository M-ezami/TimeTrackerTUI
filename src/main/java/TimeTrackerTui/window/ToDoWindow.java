package TimeTrackerTui.window;

import TimeTrackerTui.Controller.CalendarController;
import com.googlecode.lanterna.gui2.*;

public class ToDoWindow extends BasicWindow {

    public ToDoWindow(MultiWindowTextGUI gui, CalendarController calendarController) {
        super("ToDo Menu");

        Panel panel = new Panel(new GridLayout(2));

        panel.addComponent(new Button("Create ToDo", () -> {
            CreateToDoWindow createToDoWindow = new CreateToDoWindow(gui, calendarController);
            gui.addWindowAndWait(createToDoWindow);
            this.close();
        }));

        setComponent(panel);
    }
}
