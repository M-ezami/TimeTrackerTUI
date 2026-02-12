package TimeTrackerTui;

import TimeTrackerTui.calender.CalendarManager;
import TimeTrackerTui.task.TaskManager;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class AppController {

    CalendarManager calendarManager;
    TaskManager taskManager;

    public AppController(){
        calendarManager = new CalendarManager();
        taskManager = new TaskManager();
    }
    public void startApp() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        screen.startScreen();
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);
        MainMenuWindow mainMenu = new MainMenuWindow(gui,this);
        gui.addWindowAndWait(mainMenu);
    }

    public void createToDo(){


    }


}
