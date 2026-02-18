package ftml.editor;

import javax.swing.*;
import java.awt.*;

public class ToolBox {
    private final JPanel panel;
    enum Type {
        a,b,c,d
    }
    public ToolBox(Type type) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        this.panel = panel;
        __init__();
    }

    // todo should return complete toolbox with out -> $(type)
    // todo paint every component & put & return new tab.build

    private void __init__() {
        // do tab.init
    }

    public JPanel getPanel() {
        return panel;
    }
}

