package org.pbv.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PurpleButton extends JButton {

    private static final Color DARK_PURPLE = new Color(25, 11, 31, 255);
    private static final Color HOVER_COLOR = new Color(81, 20, 105, 249);

    private boolean hovering = false;

    public PurpleButton(String text) {
        super(text);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                hovering = true;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                hovering = false;
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());

        g.setColor(DARK_PURPLE);
        g.fillRect(0, 0, getWidth(), getHeight() - 1);

        g.setColor(new Color(65, 40, 92, 255));
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        g.setColor(hovering ? HOVER_COLOR : Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fm = g.getFontMetrics();
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(getText(), (getWidth() - fm.stringWidth(getText())) / 2, y);

    }
}
