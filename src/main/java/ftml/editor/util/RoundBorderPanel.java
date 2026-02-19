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
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.gray);
        g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,20,20);
        g2.dispose();
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0,0,getWidth()-1,getHeight()-1,20,20);
        g2.dispose();
    }
}
