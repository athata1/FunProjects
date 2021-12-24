import java.awt.*;
public class PenroseTiling implements LSystems
{
    private final double PI = Math.PI;
    private final int POSX = 500;
    private final int POSY = 375;
    private final int LENGTH = 25;
    private String str;
    private int count = 0;
    public PenroseTiling()
    {
        str = "[7]++[7]++[7]++[7]++[7]";
    }
    public void drawLSystem(Turtle turtle, int length, Graphics g)
    {
        System.out.println(str);

        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == '1')
                turtle.draw(length);
            else if (str.charAt(i) == '+')
                turtle.turnRight(PI/5);
            else if (str.charAt(i) == '-')
                turtle.turnLeft(PI/5);
            else if (str.charAt(i) == '[')
                turtle.push();
            else if (str.charAt(i) == ']')
                turtle.pop();
        }
    }
    public String helper(String str,int count)
    {
        if (count <= 0)
            return str;
        else
        {
            str = compute(str);
            count--;
            return helper(str,count);
        }
    }
    public String compute(String str)
    {
        String temp = "";
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == '7')
                temp += "+81--91[---61--71]+";
            else if (str.charAt(i) == '6')
                temp += "81++91----71[-81----61]++";
            else if (str.charAt(i) == '8')
                temp += "-61++71[+++81++91]-";
            else if (str.charAt(i) == '9')
                temp += "--81++++61[+91++++71]--71";
            else if (str.charAt(i) == '1')
                temp += "";
            else
                temp += str.charAt(i);
        }
        return temp;
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
        String temp = "";
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == '7')
                temp += "+81--91[---61--71]+";
            else if (str.charAt(i) == '6')
                temp += "81++91----71[-81----61]++";
            else if (str.charAt(i) == '8')
                temp += "-61++71[+++81++91]-";
            else if (str.charAt(i) == '9')
                temp += "--81++++61[+91++++71]--71";
            else if (str.charAt(i) == '1')
                temp += (char)(count + 'a');
            else
                temp += str.charAt(i);
        }
        str = temp;
        count++;
    }
    public void subtract()
    {
        count--;
        String temp = "";
        for (int i = 0; i < str.length(); i++)
        {
            if (i < str.length() - "+81--91[---61--71]+".length()+1 && str.substring(i, i+"+81--91[---61--71]+".length()).equals("+81--91[---61--71]+"))
            {
                temp+= "7";
                i+= "+81--91[---61--71]+".length()-1;
            }
            else if (i < str.length() - "81++91----71[-81----61]++".length()+1 && str.substring(i, i+"81++91----71[-81----61]++".length()).equals("81++91----71[-81----61]++"))
            {
                temp += "6";
                i+= "81++91----71[-81----61]++".length()-1;
            }
            else if(i < str.length() - "-61++71[+++81++91]-".length()+1 && str.substring(i, i+"-61++71[+++81++91]-".length()).equals("-61++71[+++81++91]-"))
            {
                temp+= "8";
                i+= "-61++71[+++81++91]-".length()-1;
            }
            else if (i < str.length() - "--81++++61[+91++++71]--71".length()+1 && str.substring(i, i+"--81++++61[+91++++71]--71".length()).equals("--81++++61[+91++++71]--71"))
            {
                temp += "9";
                i+= "--81++++61[+91++++71]--71".length()-1;
            }
            else if (str.substring(i, i+("" + (char)(count + 'a')).length()).equals(("" + (char)(count + 'a'))))
            {
                temp+= "1";
                i+= ("" + (char)(count + 'a')).length()-1;
            }
            else
                temp += str.charAt(i);
        }
        str = temp;
    }
}