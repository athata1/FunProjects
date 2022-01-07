/* Term 2 Assignment 4 - Fraction Comparable */
/*
 * Akhil Thata
 * AP Comp Sci
 * A class which is used to represent fractions and implements
 * the comparable interface
 */
public class Fraction implements Comparable
{
    private long numerator;
    private long denominator;

    // Default constructor: creates fraction 1/1
    public Fraction()
    {
        numerator = 1;
        denominator = 1;
    }

    public Fraction(int n)
    {
        numerator = n;
        denominator = 1;
    }
    // Constructor for fraction n/d where n, d > 0
    public Fraction(int n, int d)
    {
        this();
        numerator = n;
        denominator = d;
        simplify();
    }

    public Fraction(long n, long d)
    {
        this();
        numerator = n;
        denominator = d;
        simplify();
    }

    public Fraction(Fraction f)
    {
        this(f.numerator,f.denominator);
    }

    // Return the fraction as a String e.g. "2/3"
    public String toString()
    {

        if (numerator % denominator == 0)
        {
            return "" + numerator/denominator;
        }
        return numerator + "/" + denominator;
    }

    public double toDouble()
    {
        return 1.0 * numerator / denominator;
    }
    public boolean isZero()
    {
        if (numerator == 0)
            return true;
        return false;
    }

    // Return the fraction as a mixed number String
    public String mixedNumber()
    {
        // If fraction is proper return fraction using toString method
        if(numerator < denominator)
        {
            return toString();
        }

        // If numerator is a multiple of denominator return just integer part
        if (numerator % denominator == 0)
        {
            return "" + numerator/denominator;
        }

        // Otherwise return mixed number string
        return numerator/denominator + " " + numerator%denominator + "/" + denominator;
    }
    public long value()
    {
        return numerator/denominator;
    }
    // Adds the fraction n/d to this fraction if n and d are both greater than 0
    public void add(int n, int d)
    {
        if(n != 0 && d != 0)
        {
            numerator = numerator * d + n * denominator;
            denominator *= d;
            simplify();
        }
    }
    public void add(Object other)
    {
        Fraction temp = (Fraction)other;
        long n = temp.numerator;
        long d = temp.denominator;
        if(n != 0 && d != 0)
        {
            numerator = numerator * d + n * denominator;
            denominator *= d;
            simplify();
        }
    }

    public void subtract(int n, int d)
    {
        if(n != 0 && d != 0)
        {
            numerator = numerator * d - n * denominator;
            denominator *= d;
            simplify();
        }
    }
    public void subtract(Object other)
    {
        Fraction temp = (Fraction)other;
        long n = temp.numerator;
        long d = temp.denominator;
        if(n != 0 && d != 0)
        {
            numerator = numerator * d - n * denominator;
            denominator *= d;
        }

        simplify();
    }

    public void multiply(int n)
    {
        numerator *= n;
        simplify();
    }
    public void multiply(int n, int d)
    {
        numerator *= n;
        denominator *= d;
        simplify();
    }

    public void multiply(Object other)
    {
        Fraction temp = (Fraction)other;

        numerator *= temp.numerator;
        denominator *= temp.denominator;
        simplify();
    }
    public void divide(int n)
    {
        if (n != 0)
            denominator *= n;
        simplify();
    }
    public void divide(int n, int d)
    {
        if (n != 0)
        {
            numerator *= d;
            denominator *= n;
            simplify();
        }
    }
    public void divide(long n, long d)
    {
        if (n != 0)
        {
            numerator *= d;
            denominator *= n;
            simplify();
        }
    }
    public void divide(Object other)
    {
        Fraction f = (Fraction)other;
        if (f.numerator != 0)
        {
            numerator *= f.denominator;
            denominator *= f.numerator;
            simplify();
        }
    }
    public int compareTo(Object other)
    {
        Fraction num = (Fraction) other;

        //Determine if num is greater than less than or equal to each other
        if ((double)numerator*num.denominator < (double)num.numerator*denominator)
            return -1;
        if ((double)numerator*num.denominator > (double)num.numerator*denominator)
            return 1;
        else
            return 0;
    }

    public static int gcd(long a, long b)
    {
        /*long factor = 1;

        //Find common factor
        long val = Math.abs(a);
        for (long i = 2; i <= Math.min(val,b); i++)
        {
            if (a % i == 0 && b % i == 0)
                factor = i;
        }*/

        while (b != 0) {
            long t = a;
            a = b;
            b = t % b;
        }

        return (int)a;
    }

    public void simplify()
    {
        int divider = gcd(numerator, denominator);

        //Use gcd to simpify
        numerator /= divider;
        denominator /= divider;

        if ((numerator < 0 && denominator < 0) || (numerator > 0 && denominator < 0))
        {
            numerator *= -1;
            denominator *= -1;
        }
    }

    public void inverse()
    {
        long temp = numerator;
        numerator = denominator;
        denominator = temp;
    }

    public long getNumer()
    {
        return numerator;
    }
    public long getDenom()
    {
        return denominator;
    }
    public void setNumer(long n)
    {
        numerator = n;
    }
    public void settDenom(long d)
    {
        denominator = denominator;
    }
}