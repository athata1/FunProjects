import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Mandelbrot extends JPanel implements ActionListener, KeyListener {

    int size;
    double currXMin;
    double currXMax;
    double currYMin;
    double currYMax;
    int limit;
    HashMap<Integer, Color> colors;
    Timer tm = new Timer(10, this);
    public Mandelbrot() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        this.size = 400;
        this.currXMin = -2.5 + .05;
        this.currXMax = 2.5 + .05;
        this.currYMin = -2.5 - 1.25;
        this.currYMax = 2.5 - 1.25;
        this.limit = 100;
        colors = new HashMap<>();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        requestFocus(true);
        Image im = getImage();
        g.drawImage(im, 0,0,400,400, null);
        tm.start();
    }

    double velHolder = 0.1;
    double currVel = velHolder;
    public void actionPerformed(ActionEvent e) {
        tm.stop();
        while (currXMin + currVel >= currXMax - currVel || currYMin + currVel >= currYMax - currVel) {
            currVel /= 100;
            velHolder = currVel;
        }
        currXMin += currVel;
        currXMax -= currVel;
        currYMax -= currVel;
        currYMin += currVel;
        repaint();
    }

    public Image getImage() {
        BufferedImage im = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                double a = map(r, 0, size - 1, currYMin, currYMax);
                double b = map(c, 0, size - 1, currXMin, currXMax);
                int bound = findLimit(a,b);
                if (bound == limit) {
                    im.setRGB(r,c, new Color(0,0,0).getRGB());
                }
                else {
                    /*double bright = map(bound, 0, limit, 0, 1);
                    int color = (int) map(Math.sqrt(bright), 0, 1, 0, 255);
                    im.setRGB(r,c, new Color(0, color, color).getRGB());*/

                    float bright = (float)Math.sqrt((float)bound / limit);
                    im.setRGB(r,c, Color.getHSBColor(bright, 1,1).getRGB());
                }
            }
        }
        return im;
    }

    public double map(double num, double firstMin, double firstMax, double secondMin, double secondMax) {
        double percent = (num - firstMin) / (firstMax - firstMin);

        return percent * (secondMax - secondMin) + secondMin;
    }

    public int findLimit(double a, double b) {
        double ca = a;
        double cb = b;

        int count = 0;
        while (count < limit) {
            double aa = a*a - b*b;
            double bb = 2 * a * b;

            a = aa + ca;
            b = bb + cb;

            if (Math.abs(a*a + b*b) > 16) {
                break;
            }
            count++;
        }

        return count;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            currXMin -= velHolder;
            currXMax -= velHolder;

        }
        if (e.getKeyChar() == 's') {
            currXMin += velHolder;
            currXMax += velHolder;
        }
        if (e.getKeyChar() == 'd') {
            currYMin += velHolder;
            currYMax += velHolder;
        }
        if (e.getKeyChar() == 'a') {
            currYMin -= velHolder;
            currYMax -= velHolder;
        }
        if (e.getKeyChar() == '-' ) {
            if (currVel == 0) {
                currVel = velHolder;
            }
            if (currVel > 0)
                currVel *= -1;
        }
        else if (e.getKeyChar() == '=') {
            if (currVel == 0) {
                currVel = velHolder;
            }
            if (currVel < 0)
                currVel *= -1;
        }
        else if (e.getKeyChar() == '0') {
            velHolder = currVel;
            currVel = 0;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        Mandelbrot t = new Mandelbrot();
        JFrame jf = new JFrame();
        jf.setTitle("Mandelbrot");
        jf.setSize(700,700);
        t.setBackground(Color.WHITE);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(t);
    }
}
