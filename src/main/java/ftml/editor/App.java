package ftml.editor;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App{
    public static void main(String[] args) throws Exception {
        FlatMacLightLaf.setup();
        JFrame frame = new JFrame("Wikitext Editor");
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(5,5,5,5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,600);

        /*
        border_layout ::=
            ^ toolbar ::= ^ tab + _ toolbox
            < user_layout left + editor JCEF center + high-level-func-workdesk right
            _ status ::= word_num & view_type & etc...
         */
        JPanel window = new JPanel();
        window.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,4,30,0));

        panel.add(new TopbarButton("一般"));
        panel.add(new TopbarButton("插入"));
        panel.add(new TopbarButton("视图"));
        panel.add(new TopbarButton("构建"));
        for (int i = 0;i<panel.getComponentCount();i++) {
            int finalI = i;
            panel.getComponent(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (panel.getComponent(finalI) instanceof TopbarButton) {
                        var a =  (TopbarButton) panel.getComponent(finalI);
                        a.Focus("focus");
                        for (int erp = 0; erp<panel.getComponentCount(); erp++) {
                            final int w = erp;
                            if (w != finalI) {
                                var wrp =  panel.getComponent(w);
                                if(wrp instanceof TopbarButton) {
                                    ((TopbarButton) wrp).Focus("outFocus");
                                }
                            }
                        }
                    }
                }
            });
        }
        window.add(panel,BorderLayout.NORTH);
        frame.add(window);
        frame.setVisible(true);
    }
}

