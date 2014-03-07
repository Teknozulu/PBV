package org.pbv;

import org.pbv.gui.MainWindow;

import javax.swing.*;

public class PBV {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

}
