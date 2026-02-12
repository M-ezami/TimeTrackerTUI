package TimeTrackerTui;

import com.googlecode.lanterna.gui2.*;

public class ToDoWindow extends BasicWindow {

    public ToDoWindow(MultiWindowTextGUI gui, AppController appController){


        Panel panel = new Panel(new GridLayout(2));
        panel.addComponent(new Button("Create a to do", () -> {
            this.close();
            CreateToDoWindow createToDoWindow = new CreateToDoWindow(gui, appController);
            gui.addWindowAndWait(createToDoWindow);
        }));
        setComponent(panel);
    }

}
