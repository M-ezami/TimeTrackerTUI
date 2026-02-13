package TimeTrackerTui.dataLayer;

import TimeTrackerTui.helpers.Task;
import TimeTrackerTui.records.TaskRecord;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//1. A method for task to be able to write to the csv
//2. A method for printing the csv to the console 
//2.1 maps colors to indices
//2.2 use those indices to print the colors.
// do sth bout ptt

public class TaskManager {

	private final File file = new File("/home/mosa/Storage/projects/Timer/src/main/java/resources/tasks.csv");

	public void writeToCsv(Task task) {
	    TaskRecord record = task.createRecord(createIndex());
	    boolean isNew = !file.exists();
	    
	    try (FileWriter fw = new FileWriter(file, true)) {
	        if (isNew) {
	            fw.write("Index,Title,Start,End,Duration,FocusRating\n");
	        }
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        fw.write(record.index() + "," +
	        		 record.title() + "," +
	                 record.start().format(formatter) + "," +
	                 record.end().format(formatter) + "," +
	                 record.duration() + "," +
	                 record.focusRating() + "\n");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public int createIndex() {
	    int counter = 0;
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	       br.readLine();
	    	while (br.readLine() != null) {
	            counter++;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return counter +1 ;
	}

	public void printCsv() {
	    final String RESET = "\033[0m";
	    Map<Integer, String> columnColors = new HashMap<>();
	    columnColors.put(0, "\033[0;32m"); 
	    columnColors.put(1, "\033[0;33m");
	    columnColors.put(2, "\033[0;34m");
	    columnColors.put(3, "\033[0;35m"); 
	    columnColors.put(4, "\033[0;36m");
	    columnColors.put(5, "\033[0;37m"); 

	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] fields = line.split(",");
	            for (int i = 0; i < fields.length; i++) {
	            	
	                String color = columnColors.getOrDefault(i, RESET);
	                System.out.print(color + fields[i] +"  ");
	            }
	            System.out.println(RESET); 
	        }
	    } catch (IOException e) {
	        System.out.println("Error reading file.");
	    }
	}
	    
	public void deleteRow(int index) {
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        boolean indexFound = false;
	        
	        String header = br.readLine();
	        sb.append(header).append("\n");

	        int lineCounter = 1;

	        while ((line = br.readLine()) != null) {

	            String[] fields = line.split(",");
	            int taskIndex = Integer.parseInt(fields[0]);
	            
	            if (taskIndex != index) {
	                
	                fields[0] = String.valueOf(lineCounter);
	                lineCounter++;
	                
	                
	                StringBuilder newLine = new StringBuilder();
	                for (int i = 0; i < fields.length; i++) {
	                    newLine.append(fields[i]);
	                    if (i < fields.length - 1) {
	                        newLine.append(",");
	                    }
	                }

	                sb.append(newLine).append("\n");
	            } else {
	            	indexFound = true;
	            }
	            
	        }
	        br.close();
	        if (indexFound ==false) {
	        	System.out.println("index was not found");
	        }else {
	        	System.out.println("index was found and deleted");
	        	

	        FileWriter fw = new FileWriter(file, false);
	        fw.write(sb.toString());
	        fw.close();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
