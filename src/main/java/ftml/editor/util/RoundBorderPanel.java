package ftml.editor.util;

import javax.swing.*;
import java.awt.*;

public class RoundBorderPanel extends JPanel {
    public RoundBorderPanel(){
        super();
        setOpaque(false);
    }
    public RoundBorderPanel(LayoutManager layoutManager){
        super(layoutManager);
        setOpaque(false);
    }
    @Override
    protected void paintBorder(Graphics g) {

    }
}
