package ftml.editor;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
        //!=======================选项卡===============================================
        Tab t1 = new Tab("一般");
        Tab t2 = new Tab("插入");
        TabPane tp1 = new TabPane();
        //!========================选项卡控件===============================================
        Control none = (Text text)->{};
        Button bb = HandleButton.create("B","b-button-1",none);
        Control control = (Text text)->text.setStrikethrough(true);
        Button ii = HandleButton.create("I","i-button",none);
        ii.setPadding(new Insets(10,20,8,20));
        Button ss = HandleButton.create("S","s-button",control);
        ss.setPadding(new Insets(9,14,7,14));
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
        HBox.setMargin(ii, new Insets(0,0,0,0));
        hb.getChildren().add(bb);
        hb.getChildren().add(ii);
        hb.getChildren().add(ss);
        hb.setSpacing(10);
        hb.setStyle("-fx-background-color: rgba(0,0,0,0.1)");
        AnchorPane ap = new AnchorPane();
        t1.setContent(hb);
        tp1.getTabs().addAll(t1,t2);
        // !==========================添加选项卡及其控件到整体==================================================
        ap.getChildren().add(tp1);
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