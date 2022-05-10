import java.util.*;
import java.text.*;
import java.time.*;
class PowerRule
{
    public static void main(String[] args)
    {
        ArrayList<Fraction> derivative = new ArrayList<Fraction>();
        derivative.add(new Fraction(1));
        derivative.add(new Fraction(15));
        derivative.add(new Fraction(85));
        derivative.add(new Fraction(225));
        derivative.add(new Fraction(274));
        derivative.add(new Fraction(120));

        //solveNDerivative(1,derivative);
        solveAntiDerivative(derivative);
    }
    public static ArrayList<Fraction> solveNDerivative(int n, ArrayList<Fraction> arr)
    {
        if (n == 0)
        {
            for (int i = 0; i < arr.size(); i++)
            {
                if (arr.get(i).getNumer() != 0)
                {
                    System.out.print(arr.get(i));
                    if (i != arr.size()-1)
                    {
                        System.out.print("x^" + (arr.size()-1-i));
                        System.out.print(" + ");
                    }
                }
            }
            return arr;
        }
        for (int i = 0; i < arr.size(); i++)
        {
            arr.get(i).multiply((arr.size()-1-i));
        }
        arr.remove(arr.size()-1);

        return solveNDerivative(n-1,arr);
    }
    public static ArrayList<Fraction> solveAntiDerivative(ArrayList<Fraction> arr)
    {
        for (int i = 0; i < arr.size(); i++)
        {
            arr.get(i).divide((arr.size()-i));
        }

        for (int i = 0; i < arr.size(); i++)
        {
            System.out.print(arr.get(i));
            System.out.print("x^" + (arr.size()-i));
            System.out.print(" + ");
        }
        System.out.print("C");
        return arr;
    }
}
   