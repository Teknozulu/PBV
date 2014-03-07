package org.pbv.gui;

import org.pbv.Session;
import org.pbv.gui.components.DarkScrollPane;
import org.pbv.gui.components.PurpleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class MainWindow extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(6, 1, 12, 240);
    private static final Color DARK_PURPLE = new Color(25, 11, 31, 255);

    private static Point lastMousePress = new Point();
    private static Point lastMouseMove = new Point();

    private static ArrayList<Point> closeIconPoints = new ArrayList();

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
    JPanel panel;
    JList fileList;

    public MainWindow() {

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(800, 600);
        setLocation(100, 100);
        setTitle("Pleasant Bytecode Viewer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(BACKGROUND_COLOR);
                g.fillRect(0, 0, getWidth(), getHeight());

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
                g.drawString(getTitle(), 5, y);

                // Draw line to separate top bar
                g.setColor(new Color(60, 47, 68, 255));
                g.drawLine(0, 22, getWidth(), 22);

                // Draw black border
                g.setColor(Color.BLACK);
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

                // Draw "X"

                boolean hovering = false;
                Color hc = new Color(255, 54, 61, 255);
                if (isPointInBox(780, 792, 4, 16, lastMouseMove.x, lastMouseMove.y)) {
                    g.setColor(hc);
                    hovering = true;
                }

                for (Point pt : closeIconPoints) {
                    int x = (int) pt.getX();
                    y = (int) pt.getY();

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

                hovering = isPointInBox(747, 770, 1, 21, lastMouseMove.x, lastMouseMove.y);

                g.setColor(hovering ? hc : Color.WHITE);
                g.fillRect(751, 10, 15, 2);

                g.setColor(hovering ? hc : Color.LIGHT_GRAY);
                g.drawRect(750, 9, 16, 3);

            }
        };

        setContentPane(panel);
        setLayout(null);

        fileField = new JTextField();
        fileField.setText("Choose file...");
        fileField.setBounds(15, 37, 400, 22);
        fileField.setForeground(Color.LIGHT_GRAY);
        fileField.setBackground(Color.BLACK);
        fileField.setBorder(BorderFactory.createLineBorder(new Color(65, 40, 92, 255)));
        add(fileField);

        browseButton = new PurpleButton("Browse");
        browseButton.setBounds(430, 37, 100, 22);
        browseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.showOpenDialog(null);
                if (jfc.getSelectedFile() != null) {
                    fileField.setText(jfc.getSelectedFile().getAbsolutePath());
                }
            }
        });
        add(browseButton);

        openFileButton = new PurpleButton("Open");
        openFileButton.setBounds(545, 37, 100, 22);
        openFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JarFile jf = new JarFile(fileField.getText());
                    Session.parseJar(jf);
                    DefaultListModel dlm = new DefaultListModel();
                    for (String name : Session.classes.keySet()) {
                        dlm.addElement(name);
                    }
                    fileList.setModel(dlm);
                } catch (IOException e1) {
                    fileField.setText("File does not exist...");
                }
            }
        });
        add(openFileButton);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastMousePress.x = e.getX();
                lastMousePress.y = e.getY();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isPointInBox(780, 792, 4, 16, e.getX(), e.getY())) {
                    dispose();
                }
                if (isPointInBox(747, 770, 1, 21, e.getX(), e.getY())) {
                    setState(Frame.ICONIFIED);
                }
            }
        });

        jTextArea = new JTextArea(100, 20);
        jTextArea.setBackground(Color.BLACK);
        jTextArea.setForeground(Color.LIGHT_GRAY);


        // List of files contained within the selected jar
        fileList = new JList();
        fileList.setBackground(DARK_PURPLE);
        fileList.setForeground(Color.LIGHT_GRAY);
        fileList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(Color.BLACK);
                    setBorder(BorderFactory.createEmptyBorder());
                }
                return c;
            }
        });
        jScrollPane = new DarkScrollPane(fileList);
        jScrollPane.setBounds(15, 74, 200, 200);

        add(jScrollPane);

        // Mouse listeners for dragging and highlighting the close/minimize buttons
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
}
