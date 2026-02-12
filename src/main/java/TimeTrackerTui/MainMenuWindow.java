package TimeTrackerTui;

import com.googlecode.lanterna.gui2.*;

import java.io.IOException;

public class MainMenuWindow extends BasicWindow {

    public MainMenuWindow(MultiWindowTextGUI gui, AppController appController) {
        super("Main Menu");

        Panel panel = new Panel(new GridLayout(2));

        panel.addComponent(new Button("Task Menu", () -> {

            TaskWindow taskWindow = new TaskWindow(gui, appController);
            gui.addWindowAndWait(taskWindow);

        }));



        panel.addComponent(new Button("ToDo Menu", () -> {
            this.close();
            ToDoWindow toDoWindow = new ToDoWindow(gui, appController);
            gui.addWindowAndWait(toDoWindow);
        }));

        Button exitButton = new Button("Exit", () -> {
            try {
                gui.getScreen().stopScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        panel.addComponent(exitButton);



        setComponent(panel);
    }
}
