import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class RunnerCode extends JPanel implements MouseListener
{

    Turtle turtle;
    int count = 0;
    SierpinskiTriangle lSystem;
    final int LINELENGTH;
    public RunnerCode()
    {
        lSystem = new SierpinskiTriangle();
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
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK && count > 0)
        {
            count--;
            lSystem.subtract();
            repaint();
        }
        if (e.getModifiers() == MouseEvent.BUTTON1_MASK)
        {
            count++;
            lSystem.add();
            repaint();
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

    public static void main(String[] args)
    {
        RunnerCode t = new RunnerCode();
        JFrame jf = new JFrame();
        jf.setTitle("Tester");
        jf.setSize(1000,750);
        t.setBackground(Color.WHITE);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(t);
    }
}