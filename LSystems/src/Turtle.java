import java.awt.*;
import java.util.*;
public class Turtle
{
    private double posX;
    private double posY;
    private double angle;
    private ArrayList<Double> saveX;
    private ArrayList<Double> saveY;
    private ArrayList<Double> saveAngle;
    public Graphics g;

    public Turtle(int posX,int posY, Graphics g)
    {
        this.posX = posX;
        this.posY = posY;
        angle = Math.PI;
        this.g = g;
        saveX = new ArrayList<Double>();
        saveY = new ArrayList<Double>();
        saveAngle = new ArrayList<Double>();
    }

    public void draw(int length)
    {
        g.drawLine((int)Math.round(posX),(int)Math.round(posY),(int)Math.round(posX + length*Math.sin(angle)), (int)Math.round(posY + length*Math.cos(angle)));
        posX += length*Math.sin(angle);
        posY += length*Math.cos(angle);
    }

    public void turnLeft(double angle01)
    {
        angle += angle01;
    }

    public void turnRight(double angle01)
    {
        angle -= angle01;
    }

    public void push()
    {
        saveX.add(posX);
        saveY.add(posY);
        saveAngle.add(angle);
    }

    public void pop()
    {
        posX = saveX.remove(saveY.size()-1);
        posY = saveY.remove(saveY.size()-1);
        angle = saveAngle.remove(saveAngle.size()-1);
    }
    public double getPosX()
    {
        return posX;
    }
    public double getPosY()
    {
        return posY;
    }
    public double getAngle()
    {
        return angle;
    }
    public void setAngle(double angle)
    {
        this.angle = angle;
    }
}