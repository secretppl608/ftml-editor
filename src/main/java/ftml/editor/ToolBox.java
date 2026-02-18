package ftml.editor;

import javax.swing.*;
import java.awt.*;

public class ToolBox {
    private final JPanel panel;
    public ToolBox(JComponent... component) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        // panel.add(component);
        this.panel = panel;
        __init__();
    }

    // todo paint every component & put & return new tab.build

    private void __init__() {
        // do tab.init
    }

    public JPanel getPanel() {
        return panel;
    }
}

