import java.awt.*;
public class DragonCurve implements LSystems
{
    private final double PI = Math.PI;
    private final int POSX = 250;
    private final int POSY = 350;
    private final int LENGTH = 1;
    private String str;
    String[][] rules = {{"X","X+YF+"},
            {"Y","-FX-Y"}};
    public DragonCurve()
    {
        str = "FX";
    }
    public void drawLSystem(Turtle turtle, int length, Graphics g)
    {
        System.out.println(str);
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'F')
                turtle.draw(length);
            else if (str.charAt(i) == '+')
                turtle.turnRight(PI/2);
            else if (str.charAt(i) == '-')
                turtle.turnLeft(PI/2);
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