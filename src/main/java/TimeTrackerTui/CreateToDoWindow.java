package TimeTrackerTui;

import com.googlecode.lanterna.gui2.*;

public class CreateToDoWindow extends BasicWindow {

    public CreateToDoWindow(MultiWindowTextGUI gui, AppController appController) {


        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));

        Label titleLabel = new Label("title");
        panel.addComponent(titleLabel);

        TextBox titleBox = new TextBox();
        panel.addComponent(titleBox, LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        Label dateLabel = new Label("title");
        panel.addComponent(dateLabel);
        TextBox dateBox = new TextBox();
        panel.addComponent(dateBox, LinearLayout.createLayoutData(LinearLayout.Alignment.Center));


        panel.addComponent(new Button("Save", () -> {
        }), LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        setComponent(panel);



    }
}
