import java.awt.*;

public class Rings implements LSystems{
    final double PI = Math.PI;
    private final int posX = 150;
    private final int posY = 180;
    private final int LENGTH = 1;
    private String str;
    private String[][] rules = {{"F","FF+F+F+F+F+F-F"}};
    public Rings() {
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
            {
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
        return posX;
    }
    public int getPosY()
    {
        return posY;
    }
    public int getLength()
    {
        return LENGTH;
    }
}
