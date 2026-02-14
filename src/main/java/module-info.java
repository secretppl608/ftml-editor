module ftml.editor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;

    opens ftml.editor to javafx.fxml;
    exports ftml.editor;
}
