package ftml.editor;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Button bb = new Button("B");
        Control control = (Text text)->text.setStrikethrough(true);
        Control none = (Text text)->{};
        Button ii = HandleButton.create("I","i-button",none);
        ii.setPadding(new Insets(10,20,8,20));
        Button ss = HandleButton.create("S","s-button",control);
        ss.setPadding(new Insets(9,14,7,14));
        bb.getStyleClass().add("b-button");
        bb.getStyleClass().add("b-button-1");
        bb.setPadding(new Insets(10,10,8,10));
        HandleButton h = new HandleButton();
        bb.setOnMouseClicked((e)->{
            h.hb(bb);
        });
        ii.setOnMouseClicked((e)->{
            h.hb(ii);
        });
        ss.setOnMouseClicked((e)->{
            h.hb(ss);
        });
        HBox hb = new HBox();
        hb.setPadding(new Insets(1,20,1,20));
        hb.setMargin(ii, new Insets(0,0,0,0));
        hb.getChildren().add(bb);
        hb.getChildren().add(ii);
        hb.getChildren().add(ss);
        hb.setSpacing(10);
        AnchorPane ap = new AnchorPane();
        ap.getChildren().add(hb);
        ap.getStylesheets().add(getClass().getResource("/css/button.css").toExternalForm());
        scene = new Scene(ap, 1200, 600);
        stage.setScene(scene);
        stage.setTitle("Wikitext Editor");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}