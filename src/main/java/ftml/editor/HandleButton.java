package ftml.editor;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class HandleButton {
    public static Button create(String str,String needClass,Control control){
        var s = new Text(str);
        control.control(s);
        var i = new Button();
        i.setGraphic(s);
        i.getStyleClass().add("b-button");
        i.getStyleClass().add(needClass);
        System.out.println(i.getStyleClass().toString());
        return i;
    }
    public void hb(Button b){
        boolean a = b.getStyleClass().contains("b-button");
        if (a) {
            b.getStyleClass().remove("b-button");
            b.getStyleClass().add("b-button-clicked");
        }
        else{
            b.getStyleClass().remove("b-button-clicked");
            b.getStyleClass().add("b-button");
        }
    }
}

