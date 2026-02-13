package TimeTrackerTui.dataLayer;

import TimeTrackerTui.enums.Priority;
import TimeTrackerTui.enums.Status;
import TimeTrackerTui.records.ToDoRecord;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CalendarManager {

    private final File file = new File("/home/mosa/Storage/projects/Timer/src/main/java/resources/toDoCalender.csv");
    private final List<ToDoRecord> toDos = new ArrayList<>();
    public CalendarManager() {
        if (file.exists()) {
            loadFromCsv();
        }
    }

    public void loadFromCsv() {
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
           br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] fields = line.split(",");

                LocalDateTime creationTime = LocalDateTime.parse(fields[0]);
                LocalDateTime dueBy = LocalDateTime.parse(fields[1]);
                String title = fields[2];
                Status status = Status.valueOf(fields[3]);
                Priority priority = Priority.valueOf(fields[4]);
                ToDoRecord record = new ToDoRecord(creationTime, dueBy, title, status, priority);
                toDos.add(record);
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public void addTask(ToDoRecord toDo) {
        toDos.add(toDo);
        saveToCsv();
    }

    public List <ToDoRecord> getToDos(){
        return toDos;
    }

    public void saveToCsv() {
        try (FileWriter fw = new FileWriter(file, false)) {

            fw.write("CreationTime,DueBy,Title,Status,Priority\n");

            for (ToDoRecord task : toDos) {
                String line = String.format("%s,%s,%s,%s,%s\n",
                        task.creationTime(),
                        task.dueBy(),
                        task.title(),
                        task.status(),
                        task.priority()
                );
                fw.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCalendar(){
        String RESET = "\033[0m";
        String creationTimeColor = "\033[0;32m";
        String dueByColor = "\033[0;33m";
        String titleColor = "\033[0;34m";
        String statusColor = "\033[0;35m";
        String priorityColor = "\033[0;36m";

        System.out.println("--- YOUR CALENDAR ---");
        for (ToDoRecord toDo : toDos){
            System.out.print(creationTimeColor + "Created: " + toDo.creationTime() + " | ");
            System.out.print(dueByColor + "Due: " + toDo.dueBy() + " | ");
            System.out.print(titleColor + "Task: " + toDo.title() + " | ");
            System.out.print(statusColor + "Status: " + toDo.status() + " | ");
            System.out.println(priorityColor + "Priority: " + toDo.priority() + RESET);
        }
    }

    public void sortByDueDate(){
        toDos.sort(ToDoRecord.BY_DUE_DATE);
        printCalendar();
    }

    public void sortByDueDateReversed(){
        toDos.sort(ToDoRecord.BY_DUE_DATE_REV);
        printCalendar();
    }

    public void sortByPriority(){
        toDos.sort(Comparator.comparing(ToDoRecord::priority));
    }



    }





