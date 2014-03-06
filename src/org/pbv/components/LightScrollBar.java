package org.pbv.components;

import javax.swing.*;
import java.awt.*;

public class LightScrollBar extends JScrollBar {

    public LightScrollBar(int orientation) {
        super(orientation);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        g.setColor(Color.GREEN);
       // g.fillRect(0, 0, w, h);

        int min = getMinimum();
        int max = getMaximum();
        int val = getValue();

        double pos = (val - min) / (max - min);

        if (orientation == JScrollBar.VERTICAL) {
            g.fillRect(w / 2, (int) ((double)h * pos), 2, 2);
        } else if (orientation == JScrollBar.HORIZONTAL) {
            g.fillRect((int) ((double)w * pos), h / 2, 2, 2);
        }

    }
}
