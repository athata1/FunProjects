import java.awt.*;
public class SierpinskiTriangle implements LSystems
{
    private final double PI = Math.PI;
    private final int POSX = 900;
    private final int POSY = 700;
    private final int LENGTH = 5;
    private String str;
    private String[][] rules = {{"F","F-G+F+G-F"},
            {"G","GG"}};
    public SierpinskiTriangle()
    {
        str = "F-G-G";
    }
    public void drawLSystem(Turtle turtle, int length, Graphics g)
    {
        System.out.println(str);
        turtle.turnLeft(Math.PI/6);
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'F' || str.charAt(i) == 'G')
                turtle.draw(length);
            else if (str.charAt(i) == '+')
                turtle.turnRight(2*PI/3);
            else if (str.charAt(i) == '-')
                turtle.turnLeft(2*PI/3);
            else if (str.charAt(i) == '[')
                turtle.push();
            else if (str.charAt(i) == ']')
                turtle.pop();
        }
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
    public void add()
    {
        str = LSystems.calculateAddition(str,rules);
    }
    public void subtract()
    {
        str = LSystems.calculateSubtraction(str,rules);
    }
}