package TimeTrackerTui.window;

import com.googlecode.lanterna.gui2.*;
import java.util.Arrays;

public class BaseWindow extends BasicWindow {
    public BaseWindow() {
        setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.NO_DECORATIONS));
    }
    public BaseWindow(String title) {
        super(title);
        setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.NO_DECORATIONS));

    }

}
