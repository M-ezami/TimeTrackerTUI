package TimeTrackerTui.Controller;

import TimeTrackerTui.window.*;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;
// instead of passing gui instance think about a screen manager class
public class AppController {

    private final CalendarController calendarController;
    private MultiWindowTextGUI gui;
    WindowManager windowManager;

    public AppController() {
        this.calendarController = new CalendarController();
    }

    public void startApp() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        screen.startScreen();
        this.gui = new MultiWindowTextGUI(screen);
        windowManager = new WindowManager(gui, calendarController);
        windowManager.mainMenu();

    }

    public void stopApp() throws IOException {
        gui.getScreen().stopScreen();
    }
}
