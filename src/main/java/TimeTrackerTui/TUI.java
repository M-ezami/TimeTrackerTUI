package TimeTrackerTui;
import java.util.Scanner;

public class TUI {

    private boolean tuiRunning = true;
    private final Scanner scanner = new Scanner(System.in);
    private Task task;
    private Time time;
    private final Csv csv = new Csv();
    private final Screen screen = new Screen();

    public void mainLoop() {
        while (tuiRunning) {
            screen.showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    startNewTask();
                    break;
                case 2:
                    csv.printCsv();
                    break;
                case 3:
                    deleteRow();
                    break;
                case 4:
                    tuiRunning = false;
                    break;
                default:
                    screen.showMessage("Invalid choice. Try again.");
            }
        }

        screen.showMessage("Goodbye Master!");
    }

    private void deleteRow() {
        screen.showMessage("choose the index you want to delete? " );
         csv.printCsv();
        int deleteIndex = scanner.nextInt();
        scanner.nextLine();
        csv.deleteRow(deleteIndex);
        screen.showMessage("deleted index: " + deleteIndex);
        csv.printCsv();
        screen.showMessage("Row deleted.");
    }

    private void initializeTaskScreen() {
        screen.clear();
        screen.showTaskPrompt();
        String title = scanner.nextLine();

        time = new Time();
        time.start();
        task = new Task(title, time);
        screen.showMessage("Task started. Enjoy!");
    }

    private void startNewTask() {
        initializeTaskScreen();
        screen.showMessage("Type 'save' to stop & save the task.");

        boolean stop = false;

        while (!stop) {
        
            System.out.print("\rCurrent time spent: " + time.getTimeSpendFormatted());
            System.out.flush();

            try {
                if (System.in.available() > 0) {
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("save")) {
                    	time.stop();
                    	stop = true;
                    }
                }

                Thread.sleep(1000);
            } catch (Exception e) {
                break;
            }
        }
        
        saveTask();
    }


    private void saveTask() {
        screen.showMessage("\nHow focused were you during this task (1-10)?");
        task.setRating(scanner.nextInt());
        scanner.nextLine(); 
        csv.writeToCsv(task);
        screen.showMessage("Task saved. Total time: " + time.getTimeSpendFormatted());
    }
}
