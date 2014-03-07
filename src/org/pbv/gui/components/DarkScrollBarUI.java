package org.pbv.gui.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class DarkScrollBarUI extends BasicScrollBarUI {

    private boolean vertical;
    private static final Color BLOCK_COLOR = new Color(65, 40, 92, 255);

    public DarkScrollBarUI(boolean vertical) {
        this.vertical = vertical;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(BLOCK_COLOR);
        g.fillRect(vertical ? (thumbBounds.x + thumbBounds.width - 8) : thumbBounds.x + 2, vertical ? (thumbBounds.y + 2) : (thumbBounds.y + thumbBounds.height - 8), vertical ? 5 : thumbBounds.width, vertical ? thumbBounds.height : 5);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(Color.BLACK);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        if (vertical) {
            trackBounds.setSize(trackBounds.width / 2, trackBounds.height);
        } else {
            trackBounds.setSize(trackBounds.width, trackBounds.height / 2);
        }
        g.setColor(Color.BLACK);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
        paintThumb(g, c, getThumbBounds());

    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }
}
