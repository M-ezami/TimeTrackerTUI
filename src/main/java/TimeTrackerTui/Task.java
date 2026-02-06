package TimeTrackerTui;




public class Task {
private Time time;
private String title;
private int focusRating;
int index ;
public Task(String title, Time time) {
    this.title = title;
    this.time = time;
    
}

public void setTitle(String title) {
	this.title = title;
	}

public void setRating(int rating) {
	this.focusRating = rating;
}

public String getTitle() {
    return this.title;
}

public TaskRecord createRecord(int index) {
    return new TaskRecord(
    	this.index = index,
        this.title,
        this.time.getStartTime(),
        this.time.getEndTime(),
        this.time.getTimeSpendFormatted(),
        this.focusRating
    );
}


	



	
}
