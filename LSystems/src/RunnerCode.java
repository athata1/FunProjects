import java.awt.*;
import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class RunnerCode extends JPanel implements MouseListener
{

    Turtle turtle;
    int count = 0;
    LSystems lSystem;
    final int LINELENGTH;
    public RunnerCode()
    {
        lSystem = new DragonCurve();
        LINELENGTH = lSystem.getLength();
        addMouseListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        turtle = new Turtle(lSystem.getPosX(),lSystem.getPosY(),g);
        lSystem.drawLSystem(turtle,LINELENGTH,g);
        g.drawString("Left Click = Increase Generation +1",5,20);
        g.drawString("Right Click = Decrease Generation -1",5,40);
    }

    public void mouseClicked(MouseEvent e)
    {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(e.getModifiersEx());
                if (e.getModifiersEx() == InputEvent.META_DOWN_MASK && count > 0)
                {
                    count--;
                    lSystem.subtract();
                    repaint();
                }
                if (e.getModifiersEx() == 0)
                {
                    count++;
                    lSystem.add();
                    repaint();
                }
            }
        });
        th.start();
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RunnerCode t = new RunnerCode();
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