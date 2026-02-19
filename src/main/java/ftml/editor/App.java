package ftml.editor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;

import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import me.friwi.jcefmaven.UnsupportedPlatformException;
import me.friwi.jcefmaven.impl.progress.ConsoleProgressHandler;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefDisplayHandlerAdapter;
import org.cef.handler.CefLoadHandlerAdapter;
import org.jetbrains.annotations.NotNull;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import ftml.editor.util.*;

public class App {
    private static int panelWidth;
    private static final Color baseColor = new Color(0xE4E2E3);
    private static String loadUrl = "";
    public static void main(String[] args) throws Exception {
        FlatMacLightLaf.setup();
        JFrame frame = new JFrame("Wikitext Editor");
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(App.class.getResource("/assets/ftml-icon.jpg")));

        loadUrl = Objects.requireNonNull(App.class.getResource("/web/show.html")).toURI().toString();
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
        originGod_StartUp(window);

        window.setBackground(baseColor);
        window.setOpaque(true);
        frame.add(window);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CefApp.getInstance().dispose();
            }
        });
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
        topArea.setPreferredSize(new Dimension(panelWidth, 120));
        topArea.setOpaque(false);
        topArea.add(panel,BorderLayout.NORTH);
        createTopAreaInnerControlBoxOuterWhiteBox(topArea);
        parentPanel.add(topArea, BorderLayout.NORTH);
    }

    // ? need param <= ToolBox.java constructor.getPanel()
    private static void createTopAreaInnerControlBoxOuterWhiteBox(JPanel parentPanel) {
        JPanel topAreaInnerControlBoxOuterWhiteBox =  new RoundBorderPanel();
        topAreaInnerControlBoxOuterWhiteBox.setBackground(Color.WHITE);
        topAreaInnerControlBoxOuterWhiteBox.setOpaque(true);
        // todo need child panel addTo $
        topAreaInnerControlBoxOuterWhiteBox.setPreferredSize(new Dimension(panelWidth, 100));
        var a = new JPanel();
        var b = new JPanel();
        b.setOpaque(false);
        a.setOpaque(false);
        parentPanel.add(a, BorderLayout.EAST);
        parentPanel.add(b, BorderLayout.WEST);
        topAreaInnerControlBoxOuterWhiteBox.setBorder(BorderFactory.createLineBorder(Color.black,1,true));
        parentPanel.add(topAreaInnerControlBoxOuterWhiteBox, BorderLayout.CENTER);
    }

    // 原神，启动！！！
    private static void originGod_StartUp(JPanel parentPanel) throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException {
        System.out.println("原神，启动！");
        //Create a new CefAppBuilder instance
        CefAppBuilder builder = new CefAppBuilder();

//Configure the builder instance
        builder.setInstallDir(new File("jcef-bundle")); //Default
        builder.setProgressHandler(new ConsoleProgressHandler()); //Default
        builder.addJcefArgs("--disable-gpu"); //Just an example
        builder.getCefSettings().windowless_rendering_enabled = false; //Default - select OSR mode
//Set an app handler. Do not use CefApp.addAppHandler(...), it will break your code on macOS!
        builder.setAppHandler(new MavenCefAppHandlerAdapter(){});
//Build a CefApp instance using the configuration above
        CefApp cefApp = builder.build();
        CefClient client = cefApp.createClient();

        CefBrowser browser = client.createBrowser(loadUrl, false, false);
        parentPanel.add((Component) browser.getUIComponent(),BorderLayout.CENTER);
    }
}

