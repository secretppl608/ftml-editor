package ftml.editor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class App {
    private static int panelWidth;
    private static final Color baseColor = new Color(0xE4E2E3);
    public static void main(String[] args) throws Exception {
        FlatMacLightLaf.setup();
        JFrame frame = new JFrame("Wikitext Editor");
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(App.class.getResource("/assets/ftml-icon.jpg")));
        Image image = icon.getImage();
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);

        /*
        border_layout ::=
            ^ toolbar ::= ^ tab + _ toolbox
            < user_layout left + editor JCEF center + high-level-func-workdesk right
            _ status ::= word_num & view_type & etc...
         */
        JPanel window = new JPanel();
        window.setLayout(new BorderLayout());

        //? is top tab.change.btn
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));
        panelWidth = frame.getWidth();
        int right = (int) (panelWidth * 0.6);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, right));
        panel.setBackground(baseColor);
        panel.add(new TopbarButton("一般"));
        panel.add(new TopbarButton("插入"));
        panel.add(new TopbarButton("视图"));
        panel.add(new TopbarButton("构建"));
        doSomething(panel);

        createTopArea(panel,window);

        window.setBackground(baseColor);
        window.setOpaque(true);
        frame.add(window);
        frame.setVisible(true);
    }

    // $ ~ main
    private static void doSomething(@NotNull JPanel panel) {
        for (int finalI = 0; finalI < panel.getComponentCount(); finalI++) {
            any(panel, finalI);
        }
    }

    // $ ~ doSomething ~ main
    private static void any(JPanel panel, int finalI) {
        panel.getComponent(finalI).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (panel.getComponent(finalI) instanceof TopbarButton a) {
                    a.Focus(TopbarButton.AccessValue.focus);
                    for (int erp = 0; erp < panel.getComponentCount(); erp++) {
                        if (erp != finalI) {
                            var wrp = panel.getComponent(erp);
                            if (wrp instanceof TopbarButton topbarButton) {
                                topbarButton.Focus(TopbarButton.AccessValue.outFocus);
                            }
                        }
                    }
                }
            }
        });
    }

    private static void createTopArea(JPanel panel,JPanel parentPanel) {
        JPanel topArea = new JPanel();
        topArea.setBackground(baseColor);
        topArea.setLayout(new BorderLayout());
        topArea.setPreferredSize(new Dimension(panelWidth, 170));
        topArea.setOpaque(false);
        topArea.add(panel,BorderLayout.NORTH);
        createTopAreaInnerControlBoxOuterWhiteBox(topArea);
        parentPanel.add(topArea, BorderLayout.NORTH);
    }

    // ? need param <= ToolBox.java constructor.getPanel()
    private static void createTopAreaInnerControlBoxOuterWhiteBox(JPanel parentPanel) {
        JPanel topAreaInnerControlBoxOuterWhiteBox =  new JPanel();
        topAreaInnerControlBoxOuterWhiteBox.setBackground(Color.WHITE);
        topAreaInnerControlBoxOuterWhiteBox.setOpaque(true);
        // todo need child panel addTo $
        topAreaInnerControlBoxOuterWhiteBox.setPreferredSize(new Dimension(panelWidth, 150));
        var a = new JPanel();
        var b = new JPanel();
        b.setOpaque(false);
        a.setOpaque(false);
        parentPanel.add(a, BorderLayout.EAST);
        parentPanel.add(b, BorderLayout.WEST);
        topAreaInnerControlBoxOuterWhiteBox.setBorder(BorderFactory.createLineBorder(Color.black,1,true));
        parentPanel.add(topAreaInnerControlBoxOuterWhiteBox, BorderLayout.CENTER);
    }
}

