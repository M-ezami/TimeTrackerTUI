package TimeTrackerTui.window;

import TimeTrackerTui.Controller.CalendarController;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import java.io.IOException;
import java.util.LinkedList;

public class WindowManager {

    private final MultiWindowTextGUI gui;
    private final CalendarController calendarController;
    private final LinkedList<BasicWindow> history = new LinkedList<>();

    public WindowManager(MultiWindowTextGUI gui, CalendarController calendarController) {
        this.gui = gui;
        this.calendarController = calendarController;
    }
    public void openWindow(BasicWindow window){
        history.add(window);
        gui.addWindowAndWait(window);
    }

    public void back(BasicWindow window) {
        if (!history.isEmpty()) {
            history.removeLast();
            history.getLast();
        }
    }

    public void stopApp() throws IOException {
        gui.getScreen().stopScreen();
    }

    public void mainMenu(){
        MainMenuWindow mainMenuWindow = new MainMenuWindow(this,calendarController);
        openWindow(mainMenuWindow);
    }

    public void calanderMenu(){
        CalendarWindow calendarWindow = new CalendarWindow(this,calendarController);
        openWindow(calendarWindow);
    }

    public void toDoMenu(){
    ToDoWindow toDoWindow = new ToDoWindow(this,calendarController);
        openWindow(toDoWindow);
    }

    public void createToDoMenu(){
        CreateToDoWindow createToDoWindow = new CreateToDoWindow(this,calendarController);
        openWindow(createToDoWindow);
    }
}
