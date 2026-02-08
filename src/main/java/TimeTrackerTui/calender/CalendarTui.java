package TimeTrackerTui.Calender;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CalendarTui {

    private final Scanner scanner = new Scanner(System.in);
    calendarManager manager = new calendarManager();

    public void printCalendar() {
        manager.printCalendar();
    }

    public void createToDo() {
        System.out.println("title of ur to do?");
        String title = scanner.nextLine();

        System.out.println("whats the priority of this. (HIGH, MEDIUM, LOW)?");
        String priorityInput = scanner.nextLine().toUpperCase().trim();

        System.out.println("whats the day this is due");
        String dueDay = scanner.nextLine();
        System.out.println("whats the time this is due (16 00 = 16:00)");
        String dueTime = scanner.nextLine();
        LocalDateTime dT = buildDueTime(dueDay, dueTime);

        Priority p = Priority.valueOf(priorityInput);
        Status status = Status.PENDING;

        LocalDateTime creationTime = LocalDateTime.now();

        ToDoRecord toDo = new ToDoRecord(creationTime, dT, title, status, p);
        manager.addTask(toDo);
    }

    private LocalDateTime buildDueTime(String Day, String time) {
        int days = Integer.parseInt(Day);
        LocalDateTime now = LocalDateTime.now();
        String[] parts = time.split(":");
        return LocalDateTime.of(now.getYear(), now.getMonth(), days,
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]));
    }

    public void calendarMenu() {
        System.out.println("1: show calendar\n 2: add task to calendar");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                printCalendar();
                break;
            case 2:
                createToDo();
                break;
            case 3:
                break;
            default:
                System.out.println("\nInvalid input. Use 's', 'p', or 'r'.");

        }
    }
}



