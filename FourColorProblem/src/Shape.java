import java.util.*;
public class Shape
{
    private String name;
    private ArrayList<Shape> arr;
    private String color;
    private int val;
    public Shape(String name)
    {
        arr = new ArrayList<Shape>();
        this.name = name;
        color = "N/A";
    }
    public void connectsTo(Object other)
    {
        Shape temp = (Shape) other;
        arr.add(temp);
        val++;
    }
    public void reorder()
    {
        for (int i = 0; i < arr.size(); i++)
        {
            for(int j = 0; j < i; j++)
            {
                if (arr.get(i).val <= arr.get(j).val)
                {
                    arr.add(j,arr.remove(i));
                    break;
                }
            }
        }
    }
    public void setColor(String s)
    {
        color = s;
        if (color.equals("N/A"))
        {
            for (Shape shape: arr)
            {
                shape.val++;
            }
        }
        else
        {
            for (Shape shape: arr)
            {
                shape.val--;
            }
        }
        for (Shape shape: arr)
        {
            shape.reorder();
        }
    }
    public String getColor()
    {
        return color;
    }
    public ArrayList<Shape> getConnections()
    {
        return arr;
    }
    public String toString()
    {
        return name + ": " + color;
    }
    public String getName()
    {
        return name;
    }
    public int getVal()
    {
        return val;
    }
}