import javax.swing.*;
import java.awt.*;

public class TurtleTest extends JPanel {

    public TurtleTest() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Turtle t = new Turtle(500,500,g);
        t.draw(100);
        t.turnLeft(Math.PI/2);
        t.draw(100);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TurtleTest t = new TurtleTest();
                JFrame jf = new JFrame();
                jf.setTitle("Tester");
                jf.setSize(1000,750);
                t.setBackground(Color.WHITE);
                jf.setVisible(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.add(t);
            }
        });
    }
}
