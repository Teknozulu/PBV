package org.pbv.components;

import javax.swing.*;
import java.awt.*;

public class LightScrollPane extends JScrollPane {

    public LightScrollPane(Component c) {
        super(c);
    }

    @Override
    public JScrollBar createVerticalScrollBar() {
        return new LightScrollBar(JScrollBar.VERTICAL);
    }

    @Override
    public JScrollBar createHorizontalScrollBar() {
        return new LightScrollBar(JScrollBar.HORIZONTAL);
    }
}
