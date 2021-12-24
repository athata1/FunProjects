import java.awt.*;
public class Tree implements LSystems
{
    private final double PI = Math.PI;
    private final int POSX = 250;
    private final int POSY = 700;
    private final int LENGTH = 5;
    private String str;
    private String[][] rules = {{"F","FF+[+F-F-F]-[-F+F+F]"}};
    public Tree()
    {
        str = "[F]";
    }
    public void drawLSystem(Turtle turtle, int length, Graphics g)
    {
        System.out.println(str);

        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'F')
                turtle.draw(length);
            else if (str.charAt(i) == '+')
                turtle.turnRight(5*PI/36);
            else if (str.charAt(i) == '-')
                turtle.turnLeft(5*PI/36);
            else if (str.charAt(i) == '[')
                turtle.push();
            else if (str.charAt(i) == ']')
            {
                g.setColor(Color.GREEN);
                fillCircle((int)turtle.getPosX(),(int)turtle.getPosY(),3,g);
                g.setColor(Color.BLACK);
                turtle.pop();
            }
        }
    }
    public void add()
    {
        str = LSystems.calculateAddition(str,rules);
    }
    public void subtract()
    {
        str = LSystems.calculateSubtraction(str,rules);
    }
    public void fillCircle(int x,int y,int radius, Graphics g)
    {
        g.fillOval(x-radius,y-radius,radius*2,radius*2);
    }
    public int getPosX()
    {
        return POSX;
    }
    public int getPosY()
    {
        return POSY;
    }
    public int getLength()
    {
        return LENGTH;
    }
}