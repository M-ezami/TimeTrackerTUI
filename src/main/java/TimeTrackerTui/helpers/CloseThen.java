package TimeTrackerTui.helpers;

import com.googlecode.lanterna.gui2.BasicWindow;

public class CloseThen {
    private final BasicWindow window;

    public CloseThen(BasicWindow window) {
        this.window = window;
    }

    public Runnable wrap(Runnable action) {
        return () -> {
            window.close();  // close current window
            action.run();    // then run the action
        };
    }
}
