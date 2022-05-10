import java.awt.*;
public class WeirdBoxes implements LSystems
{
    private final double PI = Math.PI;
    private final int POSX = 500;
    private final int POSY = 500;
    private final int LENGTH = 5;
    private String str;
    private String rules[][] = {{"F","FF+F-F+F+FF"}};
    public WeirdBoxes()
    {
        str = "F+F+F+F";
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
    public void add()
    {
        str = LSystems.calculateAddition(str,rules);
    }
    public void subtract()
    {
        str = LSystems.calculateSubtraction(str,rules);
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