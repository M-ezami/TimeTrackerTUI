package TimeTrackerTui.window;

import TimeTrackerTui.Controller.CalendarController;
import com.googlecode.lanterna.gui2.*;

import java.io.IOException;

public class MainMenuWindow extends BasicWindow {

    public MainMenuWindow(MultiWindowTextGUI gui, CalendarController calendarController) {
        super("Main Menu");

        Panel panel = new Panel(new GridLayout(2));

        panel.addComponent(new Button("ToDo Menu", () -> {
            ToDoWindow toDoWindow = new ToDoWindow(gui, calendarController);
            gui.addWindowAndWait(toDoWindow);
            this.close();
        }));

        panel.addComponent(new Button("Calendar", () -> {
            CalendarWindow calendarWindow = new CalendarWindow(gui, calendarController);
            gui.addWindowAndWait(calendarWindow);
            this.close();
        }));

        panel.addComponent(new Button("Exit", () -> {
            try {
                gui.getScreen().stopScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        setComponent(panel);
    }
}
