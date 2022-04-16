import java.awt.*;
public class FractalPlant implements LSystems
{
    private final double PI = Math.PI;
    private final int POSX = 100;
    private final int POSY = 700;
    private final int LENGTH = 2;
    private String str;
    private String[][] rules = {{"X","F+[[X]-X]-F[-FX]+X"},
                                {"F","FF"}};
    public FractalPlant()
    {
        str = "X";
    }
    public void drawLSystem(Turtle turtle, int length, Graphics g)
    {
        System.out.println(str);
        turtle.turnRight(Math.toRadians(45));
        g.setColor(new Color(1,68,33));
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'F')
                turtle.draw(length);
            else if (str.charAt(i) == '-')
                turtle.turnRight(Math.toRadians(25));
            else if (str.charAt(i) == '+')
                turtle.turnLeft(Math.toRadians(25));
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