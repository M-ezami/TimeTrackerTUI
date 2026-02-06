package TimeTrackerTui;
import org.eclipse.jgit.api.Git;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TUI {

    private boolean tuiRunning = true;
    private final Scanner scanner = new Scanner(System.in);
    private Task task;
    private Time time;
    private final Csv csv = new Csv();
    private final Screen screen = new Screen();
    private final gitHelper git = new gitHelper();

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

    public void startNewTask(){
        screen.showMessage("Type 'save' to save the task.");
        startNewTaskTimer();
        System.out.println("Is this task part of a Git project and do you want to commit changes? (yes/no/some)");
        String gitInput = scanner.nextLine().trim();

        boolean isGitTask = false;

        if (gitInput.equalsIgnoreCase("yes") || gitInput.equalsIgnoreCase("some")) {
            isGitTask = true;

            System.out.println("Enter the path to your Git repository:");
            String repoPath = scanner.nextLine().trim();
            File repoDir = new File(repoPath);

            if (!repoDir.exists() || !new File(repoDir, ".git").exists()) {
                System.out.println("Invalid Git repository path. Skipping Git commit.");
                isGitTask = false;
            } else {
                try {
                    git.setRepoDir(repoDir);
                } catch (IOException e) {
                    System.out.println("Failed to open Git repository. Skipping Git commit.");
                    e.printStackTrace();
                    isGitTask = false;
                }
            }
        }

        if (isGitTask) {
            if (gitInput.equalsIgnoreCase("yes")) {
                try {
                    git.commitChanges("Completed task: " + task.getTitle());
                    System.out.println("All changes committed successfully!");
                } catch (Exception e) {
                    System.out.println("Failed to commit changes.");
                    e.printStackTrace();
                }
            } else if (gitInput.equalsIgnoreCase("some")) {
                // implemnt something here bruh wtffffff
                System.out.println("Selective commit not implemented yet.");
            }
        }
    }


    private void startNewTaskTimer() {
        initializeTaskScreen();
        screen.showMessage("Type 'save' to stop & save the task.");

        boolean stop = false;

        while (!stop) {
            System.out.print("\rCurrent time spent: " + time.getTimeSpendFormatted() + " (type 'save' to stop) ");
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

        System.out.println();
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
