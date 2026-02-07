package TimeTrackerTui;


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
                    startTask();
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
        screen.showMessage("choose the index you want to delete? ");
        csv.printCsv();
        int deleteIndex = scanner.nextInt();
        scanner.nextLine();
        csv.deleteRow(deleteIndex);
        screen.showMessage("deleted index: " + deleteIndex);
        csv.printCsv();
        screen.showMessage("Row deleted.");
    }

    private void saveTask() {
        screen.showMessage("\nHow focused were you during this task (1-10)?");
        task.setRating(scanner.nextInt());
        scanner.nextLine();
        csv.writeToCsv(task);
        screen.showMessage("Task saved. Total time: " + time.getTimeSpendFormatted());
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

    private void startTaskTimer() {
        initializeTaskScreen();
        runTaskTimer();

        System.out.println();
        saveTask();
    }

    public void runTaskTimer() {
        screen.showMessage("Type 's' to stop, 'p' to pause, 'r' to resume the task.");
        boolean stop = false;
        boolean paused = false;

        while (!stop) {
            System.out.print("\rCurrent time spent: " + time.getTimeSpendFormatted() +
                    " (s = stop, p = pause, r = resume): ");
            try {
                if (System.in.available() > 0) {
                    String input = scanner.nextLine().trim().toLowerCase();
                    switch (input) {
                        case "s":
                            time.stop();
                            stop = true;
                            break;
                        case "p":
                            if (!paused) {
                                time.pause();
                                paused = true;
                                System.out.println("\nPaused. Type 'r' to resume or 's' to save.");
                            }
                            break;
                        case "r":
                            if (paused) {
                                time.resume();
                                paused = false;
                            }
                            break;
                        default:
                            System.out.println("\nInvalid input. Use 's', 'p', or 'r'.");
                    }
                }

                Thread.sleep(1000);

            } catch (IOException e) {
                System.out.println("\nError reading input. Stopping task.");
                stop = true;
            } catch (InterruptedException e) {
                System.out.println("\nTimer interrupted. Stopping task.");
                stop = true;
            }
        }
    }

    public void startTask() {
        startTaskTimer();

        String gitInput = null;
        while (gitInput == null) {
            System.out.println("Is this task part of a Git project and do you want to commit changes, s for some? (y/n/s)");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("y") || line.equalsIgnoreCase("n") || line.equalsIgnoreCase("s")) {
                gitInput = line;
            } else {
                System.out.println("Invalid input, please type yes, no, or some.");
            }
        }
        switch (gitInput.toLowerCase()) {
            case "y":
                checkRepoPath();
                commitAll();
                break;
            case "s":
                checkRepoPath();
                break;
            case "n":
                System.out.println("Skipping Git commits.");
                break;


        }
    }

    public void checkRepoPath() {
        File repoDir = null;
        while (repoDir == null) {
            System.out.println("Enter the path to your Git repository:");
            String repoInput = scanner.nextLine().trim();
            File repoPath = new File(repoInput);

            if (!repoPath.exists() || !new File(repoPath, ".git").exists()) {
                System.out.println("Invalid Git repository path. Try again.");
            } else {
                try {
                    repoDir = repoPath;
                    git.setRepoDir(repoDir);
                } catch (IOException e) {
                    System.out.println("Failed to open Git repository. Skipping Git commit.");
                    e.printStackTrace();
                }


            }
        }
    }

    public void commitAll(){
            try {
                git.commitChanges("Completed task: " + task.getTitle());
                System.out.println("All changes committed successfully!");
            } catch (Exception e) {
                System.out.println("Failed to commit changes.");
                e.printStackTrace();
            }


    }



}








