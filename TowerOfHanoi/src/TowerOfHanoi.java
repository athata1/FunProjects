import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;

public class TowerOfHanoi extends JPanel implements ActionListener
{
    Time t;
    Timer tm;
    ArrayList<ArrayList<TowerPiece>> towers;
    int swap;
    int size;
    long start;
    Image image;
    Graphics2D graphics2D;
    StepsThread threadClass;
    public TowerOfHanoi(int size) {
        threadClass = new StepsThread(size,3);
        Thread th = new Thread(threadClass);
        th.start();

        setBackground(Color.WHITE);
        t = new Time();
        start = System.currentTimeMillis();
        this.size = size;
        tm = new Timer(1, this);
        towers = new ArrayList<ArrayList<TowerPiece>>();
        towers.add(new ArrayList<TowerPiece>());
        towers.add(new ArrayList<TowerPiece>());
        towers.add(new ArrayList<TowerPiece>());
        swap = 0;

        for (int i = size; i >= 1; i--)
        {
            towers.get(0).add(new TowerPiece(i));
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //Crate image
        image = createImage(getSize().width, getSize().height);
        // this lets us draw on the image (ie. the canvas)
        graphics2D = (Graphics2D) image.getGraphics();
        // gives us better rendering quality for the drawing lines
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // set canvas to white with default paint color
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);

        drawTowers(graphics2D);
        g.drawImage(image,0,0,null);
        if (swap == (int)Math.pow(2, size)-1)
            tm.stop();
        else
            tm.start();
    }

    public void drawTowers(Graphics g)
    {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.setColor(Color.BLACK);
        fillRect(200,405,10,300,g);
        fillRect(500,405,10,300,g);
        fillRect(800,405,10,300,g);
        fillRect(500,555,800,10,g);
        String time = t.toString();
        g.drawString(time,0,50);
        g.drawString(swap + " swaps",0,100);

        long timeToCompletionMillis = (long)(1.0*(System.currentTimeMillis() - start)/swap * Math.pow(2,size));;
        Time timeToComplete = new Time();
        timeToComplete.setTime(timeToCompletionMillis);
        g.drawString("Est. Time: " + timeToComplete,0,150);

        g.setColor(Color.RED);
        ArrayList<TowerPiece> t1 = towers.get(0);
        for (int i = 0; i < t1.size(); i++)
        {
            fillRect(200,545-i*15,t1.get(i).getSize()*10+20,10,g);
        }

        ArrayList<TowerPiece> t2 = towers.get(1);
        for (int i = 0; i < t2.size(); i++)
        {
            fillRect(500,545-i*15,t2.get(i).getSize()*10+20,10,g);
        }

        ArrayList<TowerPiece> t3 = towers.get(2);
        for (int i = 0; i < t3.size(); i++)
        {
            fillRect(800,545-i*15,t3.get(i).getSize()*10+20,10,g);
        }
    }

    public static void fillRect(int x, int y, int sideX, int sideY, Graphics g)
    {
        g.fillRoundRect(x-sideX/2, y-sideY/2, sideX, sideY,10,20);
    }

    public static void movePiece(ArrayList<TowerPiece> t1, ArrayList<TowerPiece> t2)
    {
        t2.add(t1.remove(t1.size()-1));
    }
    public void actionPerformed(ActionEvent e)
    {
        Move m = threadClass.getNextMove();
        if (m != null)
        {
            movePiece(towers.get(m.getStart()-1),(towers.get(m.getEnd()-1)));
            t.setTime((int)(System.currentTimeMillis()-start));
            swap++;
        }
        repaint();
    }
    /*public ArrayList<Move> steps(int begin, ArrayList<Move> arr)
    {
        ArrayList<Move> temp01 = new ArrayList<Move>();
        int start = 1;
        int num = (begin)%2;
        int end = 2 + num;

        for (int i = 0; i < arr.size()+1; i++)
        {
            temp01.add(new Move(start,end));
            start = (start+num)%3+1;
            end = (end+num)%3+1;
        }


        ArrayList<Move> temp02 = new ArrayList<Move>();
        for (int i = 0; i < arr.size(); i++)
        {
            temp02.add(temp01.get(i));
            temp02.add(arr.get(i));
        }
        temp02.add(temp01.get(temp01.size()-1));
        if (size == begin)
            return temp02;
        else
        {
            return steps(++begin,temp02);
        }
    }*/
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter num of pieces: ");
        int size = scan.nextInt();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jf = new JFrame();
                jf.setTitle("Tester");
                jf.setSize(1000,750);
                jf.setVisible(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.add(new TowerOfHanoi(size));
            }
        });
    }
}