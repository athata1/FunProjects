import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DiceStats extends JPanel implements ActionListener
{
    Timer tm = new Timer(1,this);
    int[] rolls = new int[11];
    int total = 0;
    public DiceStats()
    {
        int dice1 = (int)(Math.random()*6);
        int dice2 = (int)(Math.random()*6);
        rolls[dice1+dice2]++;
        total++;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawChart(rolls,g);
        tm.start();
    }

    public void actionPerformed(ActionEvent e)
    {
        int dice1 = (int)(Math.random()*6);
        int dice2 = (int)(Math.random()*6);
        rolls[dice1+dice2]++;
        total++;
        repaint();
    }
    public void drawChart(int[] arr, Graphics g)
    {
        Font f = new Font("Serif", Font.BOLD, 12);
        g.setFont(f);
        //Graph
        int graphVShift = 250;
        for (int i = 0; i < arr.length; i++)
        {
            g.setColor(Color.YELLOW);
            g.fillRect(i*20,graphVShift-arr[i]*1000/total,20,arr[i]*1000/total);
            g.setColor(Color.BLACK);
            g.drawRect(i*20,graphVShift-arr[i]*1000/total,20,arr[i]*1000/total);
            g.drawString("" + (i+2),i*20+5,graphVShift+10);
        }

        g.drawLine(220,graphVShift,230,graphVShift);
        g.drawLine(230,graphVShift,230,50);
        g.drawLine(270,0,270,350);
        for (int i = 0; i < 9; i++)
        {
            g.drawLine(225,graphVShift - i*20-20,235,graphVShift - i*20-20);
            g.drawString((i*2+2) + "%", 240,graphVShift-17-i*20);
        }

        //Frequency Chart
        g.drawString("Relative Frequency", 280,50);
        for (int i = 0; i < arr.length; i++)
        {
            g.drawString((i+2) + ": " + (double)Math.round((double)arr[i]/total*1000)/10 + "%", 280,70+i*20);
        }
        g.drawString("Total rolls: " + total, 280, 290);
        g.drawString("Roll Number vs Relative Frequency", 0,20);

        //X and Y axis
        g.drawString("x = roll number",5,290);
        g.drawString("y = relative frequency",5,310);
    }
    public static void main(String[] args)
    {
        DiceStats t = new DiceStats();
        JFrame jf = new JFrame();
        jf.setTitle("Tester");
        jf.setSize(400,350);
        t.setBackground(Color.WHITE);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(t);
    }
}