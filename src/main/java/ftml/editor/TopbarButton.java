package ftml.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TopbarButton extends JButton {
    private final Map<TextAttribute,Object> map = new HashMap<TextAttribute,Object>();
    public TopbarButton(String str) {
        super(str);
        setPreferredSize(new Dimension(10,20));
        __init__();
    }

    private void __init__() {
        setBackground(new Color(0,0,0,0));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBackground(new Color(0,0,0,10));
                map.put(TextAttribute.WEIGHT,TextAttribute.WEIGHT_BOLD);
                setFont(new Font(map));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(new Color(0,0,0,0));
                map.remove(TextAttribute.WEIGHT,TextAttribute.WEIGHT_BOLD);
                setFont(new Font(map));
            }
        });
    }

    //? <public_control_method> because $ Listener{click} -X-> tab.out.change (difficult & not_modular_design)
    //? out.Control A.(--> $ | --> tab.out.Change)
    public void Focus(String type){
        if (Objects.equals(type, "focus")){
            map.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
            setFont(new Font(map));
        } else if (Objects.equals(type,"outFocus")) {
            map.remove(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
            setFont(new Font(map));
        }
    }
}
