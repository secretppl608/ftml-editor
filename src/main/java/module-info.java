module ftml.editor {
    requires transitive jcefmaven;
    requires transitive jcef;
    requires org.jetbrains.annotations;
    requires java.desktop;
    requires com.formdev.flatlaf;
    requires jdk.xml.dom;

    exports ftml.editor;
}
