package TimeTrackerTui.window;

import TimeTrackerTui.Controller.CalendarController;
import com.googlecode.lanterna.gui2.*;

import java.io.IOException;

public class MainMenuWindow extends BasicWindow {

    public MainMenuWindow(WindowManager windowManager, CalendarController calendarController) {
        super("Main Menu");

        Panel panel = new Panel(new GridLayout(2));

        panel.addComponent(new Button("ToDo Menu", () -> {
            this.close();
            windowManager.toDoMenu();
        }));

        panel.addComponent(new Button("Calendar", () -> {
            this.close();
            windowManager.calanderMenu();
        }));

        panel.addComponent(new Button("Exit", () -> {
            try {
                windowManager.stopApp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        setComponent(panel);
    }
}
