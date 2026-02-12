package TimeTrackerTui.task;

public class Screen {

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void showMainMenu() {
        System.out.println("======== MAIN MENU ========");
        System.out.println("1: Start a new task with a timer");
        System.out.println("2: print a csv");
        System.out.println("3: Delete a row");
        System.out.println("4: Exit");
        System.out.println("Enter your choice: ");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskPrompt() {
        System.out.println("=== NEW TASK SCREEN ===");
        System.out.print("Enter title for your task: ");
    }
}
