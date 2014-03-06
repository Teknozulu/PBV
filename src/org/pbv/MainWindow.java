package org.pbv;

import org.pbv.components.PurpleButton;
import org.pbv.components.LightScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(6, 1, 12, 245);

    private static Point lastMousePress = new Point();
    private static Point lastMouseMove = new Point();

    private static ArrayList<Point> closeIconPoints = new ArrayList<Point>();

    // Build the X
    static {
        for (int y = 0; y < 12; y++) {
            for (int x = y; x < y + 2; x++) {
                closeIconPoints.add(new Point(x, y));
                closeIconPoints.add(new Point(12 - x, y));
            }
        }
    }

    JButton browseButton;
    JButton openFileButton;
    JTextArea jTextArea;
    JScrollPane jScrollPane;
    JTextField fileField;

    public MainWindow() {

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        setSize(800, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jTextArea = new JTextArea(100, 100);
        jScrollPane = new LightScrollPane(jTextArea);
        jScrollPane.setBounds(50, 160, 100, 100);
        //add(jScrollPane);

        fileField = new JTextField();
        fileField.setText("Choose file...");
        fileField.setBounds(5, 31, 400, 22);
        fileField.setForeground(Color.LIGHT_GRAY);
        fileField.setBackground(Color.BLACK);
        fileField.setBorder(BorderFactory.createLineBorder(new Color(65, 40, 92, 255)));
        add(fileField);

        browseButton = new PurpleButton("Browse");
        browseButton.setBounds(420, 31, 100, 22);
        browseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Hello world");
                JFileChooser jfc = new JFileChooser();
                jfc.showOpenDialog(null);
                if (jfc.getSelectedFile() != null) {
                    fileField.setText(jfc.getSelectedFile().getAbsolutePath());
                }
            }
        });
        add(browseButton);

        openFileButton = new PurpleButton("Open");
        openFileButton.setBounds(530, 31, 100, 22);
        add(openFileButton);


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastMousePress.x = e.getX();
                lastMousePress.y = e.getY();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > 780 && e.getX() < 792 && e.getY() > 4 && e.getY() < 16) {
                    dispose();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (e.getY() < 22) {
                    Point p = getLocation();
                    setLocation(p.x + e.getX() - lastMousePress.x, p.y + e.getY() - lastMousePress.y);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // Using xor to check if we either exited or entered an area that needs to be highlighted
                boolean repaint = false; // This is because we have to update lastMouseMove before repainting
                if (isPointInBox(780, 792, 4, 16, e.getX(), e.getY()) ^ isPointInBox(780, 792, 4, 16, lastMouseMove.x, lastMouseMove.y))
                    repaint = true;
                if (isPointInBox(747, 770, 1, 21, e.getX(), e.getY()) ^ isPointInBox(747, 770, 1, 21, lastMouseMove.x, lastMouseMove.y))
                    repaint = true;
                lastMouseMove.x = e.getX();
                lastMouseMove.y = e.getY();
                if (repaint) {
                    repaint();
                }
            }
        });
    }

    private static boolean isPointInBox(int x1, int x2, int y1, int y2, int x, int y) {
        return x > x1 && x < x2 && y > y1 && y < y2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw title bar
        g.setColor(new Color(60, 47, 68, 255));
        g.fillRect(0, 0, getWidth(), 11);
        g.setColor(new Color(25, 11, 31, 255));
        g.fillRect(0, 11, getWidth(), 11);

        // Draw title text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fm = g.getFontMetrics();
        int y = ((22 - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString("Pleasant Bytecode Viewer", 5, y);

        // Fill with translucent purple
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 22, getWidth(), getHeight());

        // Draw line to separate top bar
        g.setColor(new Color(60, 47, 68, 255));
        g.drawLine(0, 22, getWidth(), 22);

        // Draw black border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Draw "X"

        boolean hovering = false;
        Color hc = new Color(255, 54, 61, 255);
        if (lastMouseMove.x > 780 && lastMouseMove.x < 792 && lastMouseMove.y > 4 && lastMouseMove.y < 16) {
            g.setColor(hc);
            hovering = true;
        }

        for (Point p : closeIconPoints) {
            int x = (int)p.getX();
            y = (int)p.getY();

            // Doesn't do exactly what I planned to do (complete border around the X) but it looks fine.
            if (!hovering) {
                if (y == 0 || y == 11 || x == y || (12 - x == y)) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.WHITE);
                }
            }
            g.drawRect(780 + x, 4 + y, 1, 1);
        }

        hovering = false;

        if (lastMouseMove.x > 747 && lastMouseMove.x < 770 && lastMouseMove.y > 1 && lastMouseMove.y < 21) {
            hovering = true;
        }

        g.setColor(hovering ? hc : Color.WHITE);
        g.fillRect(751, 10, 15, 2);

        g.setColor(hovering ? hc : Color.LIGHT_GRAY);
        g.drawRect(750, 9, 16, 3);

        fileField.repaint();
        browseButton.repaint();
        openFileButton.repaint();

    }


}
