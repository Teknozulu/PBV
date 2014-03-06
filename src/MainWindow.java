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

    static {
        for (int y = 0; y < 12; y++) {
            // Build the \
            for (int x = y; x < y + 2; x++) {
                // This will make it convenient to draw a border around the X
                closeIconPoints.add(new Point(x, y));
                closeIconPoints.add(new Point(12 - x, y));
            }
        }
    }

    public MainWindow() {

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(800, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastMousePress.x = e.getX();
                lastMousePress.y = e.getY();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > 780 &&  e.getX() < 792 && e.getY() > 4 && e.getY() < 16) {
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
                lastMouseMove.x = e.getX();
                lastMouseMove.y = e.getY();
                repaint();
            }
        });
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
        Color hc = new Color(107, 65, 152, 255);
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


    }


}
