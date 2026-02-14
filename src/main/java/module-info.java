module ftml.editor {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;

    opens ftml.editor to javafx.fxml;

    exports ftml.editor;
}
