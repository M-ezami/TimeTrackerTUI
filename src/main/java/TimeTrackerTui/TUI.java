package TimeTrackerTui;

import TimeTrackerTui.calender.CalendarTui;
import TimeTrackerTui.task.TaskTui;

import java.util.Scanner;

public class TUI {

    boolean running = true;
    TaskTui taskTui = new TaskTui();
    CalendarTui calendarTui = new CalendarTui();
    private final Scanner scanner = new Scanner(System.in);

    public void tCCLoop(){
        System.out.println("type 1 for calendar menu and 2 for task menu");
        while (running){
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    calendarTui.calendarMenu();
                case 2:
                    taskTui.taskLoop();
            }
        }
    }
}
