import java.awt.*;
public interface LSystems
{
    public void drawLSystem(Turtle turtle, int length, Graphics g);
    public void subtract();
    public void add();
    public int getPosX();
    public int getPosY();
    public int getLength();

    public static String calculateSubtraction(String str, String[][] rules)
    {
        String temp = "";
        int index = -1;
        for (int i = 0; i < str.length(); i++)
        {
            for (int j = 0; j < rules.length; j++)
            {
                int len = rules[j][1].length();
                if (i <= str.length() - len && str.substring(i,i+len).equals(rules[j][1]))
                {
                    temp += rules[j][0];
                    i += len-1;
                    index = 0;
                    break;
                }
            }
            if (index == -1)
                temp += str.charAt(i);
            index = -1;
        }
        return temp;
    }

    public static String calculateAddition(String str, String[][] rules)
    {
        String temp = "";
        int index = -1;
        for (int i = 0; i < str.length(); i++)
        {
            for (int j = 0; j < rules.length; j++)
            {
                if (str.substring(i,i+1).equals(rules[j][0]))
                {
                    temp += rules[j][1];
                    index = 0;
                }
            }
            if (index == -1)
                temp += str.charAt(i);
            index = -1;
        }
        return temp;
    }
}