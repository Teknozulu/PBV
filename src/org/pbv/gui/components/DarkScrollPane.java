package org.pbv.gui.components;

import javax.swing.*;
import java.awt.*;

public class DarkScrollPane extends JScrollPane {

    public DarkScrollPane(Component c) {
        super(c);
        setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        });
        getVerticalScrollBar().setUI(new DarkScrollBarUI(true));
        getHorizontalScrollBar().setUI(new DarkScrollBarUI(false));
        setBorder(BorderFactory.createLineBorder(new Color(65, 40, 92, 255)));
    }

    @Override
    public JScrollBar createVerticalScrollBar() {
        JScrollBar jScrollbar = new JScrollBar(JScrollBar.VERTICAL);
        jScrollbar.setPreferredSize(new Dimension(11, 11));
        return jScrollbar;
    }

    @Override
    public JScrollBar createHorizontalScrollBar() {
        JScrollBar jScrollbar = new JScrollBar(JScrollBar.HORIZONTAL);
        jScrollbar.setPreferredSize(new Dimension(11, 11));
        return jScrollbar;
    }
}
