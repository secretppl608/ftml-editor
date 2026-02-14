package ftml.editor;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class HandleButton {
    public static Button create(String str, String needClass, Control control) {
        var s = new Text(str);
        control.control(s);
        var i = new Button();
        i.setGraphic(s);
        i.getStyleClass().add("b-button");
        i.getStyleClass().add(needClass);
        return i;
    }

    public void hb(Button b) {
        Node gc = b.getGraphic();
        boolean a = gc.getStyleClass().contains("b-button-clicked");
        if (!a) {
            gc.getStyleClass().add("b-button-clicked");
        } else {
            gc.getStyleClass().remove("b-button-clicked");
        }
    }
}
