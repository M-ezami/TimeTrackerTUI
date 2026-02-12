package TimeTrackerTui.task;


import TimeTrackerTui.GitHelper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TaskTui {
    private boolean tuiRunning = true;
    private final Scanner scanner = new Scanner(System.in);
    private Task task;
    private Timer timer;
    private final TaskManager taskManager = new TaskManager();
    private final Screen screen = new Screen();
    private final GitHelper git = new GitHelper();

    public void taskLoop() {
        while (tuiRunning) {
            screen.showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    startTask();
                    break;
                case 2:
                    taskManager.printCsv();
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

    private void startTaskTimer() {
        initializeTaskScreen();
        runTaskTimer();
        System.out.println();
        saveTask();
    }

    private void initializeTaskScreen() {
        screen.clear();
        screen.showTaskPrompt();
        String title = scanner.nextLine();

        timer = new Timer();
        timer.start();
        task = new Task(title, timer);
        screen.showMessage("Task started. Enjoy!");
    }

    public void runTaskTimer() {
        screen.showMessage("Type 's' to save and stop, 'p' to pause, 'r' to resume the task.");
        boolean stop = false;
        boolean paused = false;

        while (!stop) {
            System.out.print("\rCurrent time spent: " + timer.getTimeSpendFormatted() +
                    " (s = stop, p = pause, r = resume): ");
            try {
                if (System.in.available() > 0) {
                    String input = scanner.nextLine().trim().toLowerCase();
                    switch (input) {
                        case "s":
                            timer.stop();
                            stop = true;
                            break;
                        case "p":
                            if (!paused) {
                                timer.pause();
                                paused = true;
                                System.out.println("\nPaused. Type 'r' to resume or 's' to save.");
                            }
                            break;
                        case "r":
                            if (paused) {
                                timer.resume();
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

    private void saveTask() {
        screen.showMessage("\nHow focused were you during this task (1-10)?");
        task.setRating(scanner.nextInt());
        scanner.nextLine();
        taskManager.writeToCsv(task);
        screen.showMessage("Task saved. Total time: " + timer.getTimeSpendFormatted());
    }

    private void deleteRow() {
        screen.showMessage("choose the index you want to delete? ");
        taskManager.printCsv();
        int deleteIndex = scanner.nextInt();
        scanner.nextLine();
        taskManager.deleteRow(deleteIndex);
        screen.showMessage("deleted index: " + deleteIndex);
        taskManager.printCsv();
        screen.showMessage("Row deleted.");
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








