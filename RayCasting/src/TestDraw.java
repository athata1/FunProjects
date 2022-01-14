import java.awt.*;
import java.util.Scanner;
import javax.swing.JFrame;
public class TestDraw
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        //import classes
        DrawingPanel panel = new DrawingPanel(1000,1000);
        Graphics g = panel.getGraphics();
        int posX = 500;
        int posY = 500;
        double angle = 0;
        while(true)
        {
            g.setColor(Color.BLACK);
            g.drawLine(700,300,700,700);
            int posX2 = panel.findX();
            int posY2 = panel.findY();
            int position = findQuadrant(posX,posY,posX2,posY2);
            angle = Math.atan(1.0*(posY2-posY)/(posX2-posX));
            if (position == 2 || position == 3)
            {
                angle += Math.PI;
            }
            determineIntersection(posX,posY,(int)((1000*Math.cos(angle))+500),(int)((1000*Math.sin(angle))+500),700,700,700,300,g);
            g.drawLine(posX,posY,(int)(50*Math.cos(angle))+500,(int)(50*Math.sin(angle))+500);
            panel.sleep(10);
            g.setColor(Color.WHITE);
            g.fillRect(0,0,1000,1000);
        }

    }
    public static void intersect(int x1,int y1, int x2, int y2, double t,Graphics g)
    {
        g.drawLine(x1,y1, (int)(x1 + t*(x2-x1)), (int)(y1 + t*(y2-y1)));
    }
    public static boolean determineIntersection(int x1,int y1, int x2, int y2, int x3,int y3,int x4, int y4,Graphics g)
    {
        int den = ((x1-x2)*(y3-y4) - (y1-y2)*(x3-x4));
        if (den == 0)
            return false;
        int numer01 = ((x1-x3)*(y3-y4) - (y1-y3)*(x3-x4));
        int numer02 = -((x1-x2)*(y1-y3) - (y1-y2)*(x1-x3));
        double t = 1.0*numer01/den;
        double u = 1.0*numer02/den;
        if (0 <= t && t <= 1 && u >= 0 && u <= 1)
        {
            intersect(x1,y1,x2,y2,t,g);
            return true;
        }
        else
            return false;
    }
    public static int findQuadrant(int cx, int cy, int posx,int posy)
    {
        if (posx > cx)
        {
            if (posy > cy)
                return 1;
            else
                return 4;
        }
        else
        {
            if (posy > cy)
                return 2;
            else
                return 3;
        }
    }
    public static void drawRectLCorner(int x,int y, int width, int height, Graphics g)
    {
        g.drawRect(x,y-height,width,height);
    }
    public static void drawCircle(int x,int y,int radius, Graphics g)
    {
        g.drawOval(x-radius,y-radius,radius*2,radius*2);
    }
    public static void fillCircle(int x,int y,int radius, Graphics g)
    {
        g.fillOval(x-radius,y-radius,radius*2,radius*2);
    }
    public static void fillSquare(int x, int y, int side, Graphics g)
    {
        g.drawRect(x-side/2, y-side/2, side, side);
    }
}