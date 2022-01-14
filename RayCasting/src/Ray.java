import java.awt.*;
public class Ray
{
    private double angle;
    private int length;
    private Graphics g;
    private int newX;
    private int newY;

    //Create constructor
    public Ray (int length, double angle,Graphics g)
    {
        this.angle = angle;
        this.length = length;
        this.g = g;
    }

    //Determine if ray intersects wall
    public boolean intersects(Object other, int x, int y)
    {
        Wall temp = (Wall) other;

        int x2 = calculateX2(x);
        int y2 = calculateY2(y);
        int x3 = temp.getX1();
        int x4 = temp.getX2();
        int y3 = temp.getY1();
        int y4 = temp.getY2();

        int den = ((x-x2)*(y3-y4) - (y-y2)*(x3-x4));
        if (den == 0)
            return false;
        int numer01 = ((x-x3)*(y3-y4) - (y-y3)*(x3-x4));
        int numer02 = -((x-x2)*(y-y3) - (y-y2)*(x-x3));
        double t = 1.0*numer01/den;
        double u = 1.0*numer02/den;
        if (0 <= t && t <= 1 && u >= 0 && u <= 1)
        {
            return true;
        }
        else
            return false;
    }
    private int calculateX2(int x)
    {
        return (int)(length*Math.cos(angle)+x);
    }
    private int calculateY2(int y)
    {
        return (int)(length*Math.sin(angle)+y);
    }
    //Find point of intersection
    public void findNewXandY(int x, int y, Object other)
    {
        Wall temp = (Wall) other;
        int x2 = calculateX2(x);
        int y2 = calculateY2(y);

        newX = (int)(x + findT(temp,x,y)*(x2-x));
        newY = (int)(y + findT(temp,x,y)*(y2-y));
    }
    //Draw ray if there is no wall intersection
    public void drawOtherRay(int x, int y)
    {
        int x2 = calculateX2(x);
        int y2 = calculateY2(y);
        g.drawLine(x,y,x2,y2);
    }

    //Draw ray from cursor to wall
    public void drawRay(Object other, int x, int y)
    {
        Wall temp = (Wall) other;
        findNewXandY(x,y,temp);
        g.drawLine(x,y,newX,newY);
    }

    //Find t value used to find intersection
    private double findT(Object other01, int x,int y)
    {
        Wall temp = (Wall) other01;

        int x2 = calculateX2(x);
        int y2 = calculateY2(y);
        int x3 = temp.getX1();
        int x4 = temp.getX2();
        int y3 = temp.getY1();
        int y4 = temp.getY2();

        int den = ((x-x2)*(y3-y4) - (y-y2)*(x3-x4));
        int numer01 = ((x-x3)*(y3-y4) - (y-y3)*(x3-x4));
        return 1.0*numer01/den;
    }
    public double findDist(int x, int y)
    {
        return Math.sqrt(Math.pow((x-newX),2) + Math.pow((y-newY),2));
    }
}