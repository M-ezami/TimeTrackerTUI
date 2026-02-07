package TimeTrackerTui;

public class Task {
private final Timer timer;
private final String title;
private int focusRating;
int index ;
// create our own timer object maybe because timer is a neccesity for task tui doesnt need its own timer for any reason really
public Task(String title, Timer timer) {
    this.title = title;
    this.timer = timer;
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
        this.timer.getStartTime(),
        this.timer.getEndTime(),
        this.timer.getTimeSpendFormatted(),
        this.focusRating
    );

}


	



	
}
