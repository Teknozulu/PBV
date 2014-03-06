import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MainWindow extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(6, 1, 12, 245);

    private static Point point = new Point();

    public MainWindow() {

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(800, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (e.getY() < 22) {
                    Point p = getLocation();
                    setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
                }
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
    }


}
