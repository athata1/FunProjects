public class SystemOfEquationFractionForm
{
    public static void main(String[] args)
    {

      int[][] start = {{ 0, 0, 8, 0, 0,0,0,0,0,0,0,-1},
                       { 0, 8, 8, 0, 0,0,0,0,0,0,0,12},
                       { 8, 8,20, 0, 0,0,0,0,0,0,0,-3},
                       { 8,20,12, 0, 8,0,4,0,2,0,1,0},
                       {20,12,18, 8, 0,4,4,2,2,1,1,0},
                       {12,18, 6, 0,12,4,8,2,3,1,1,1},
                       {18, 6, 7,12, 0,8,4,3,1,1,0,0},
                       { 6, 7, 1, 0, 6,4,5,1,1,0,0,0},
                       { 7, 1, 1, 6, 0,5,1,1,0,0,0,0},
                       { 1, 1, 0, 0, 1,1,1,0,0,0,0,0},
                       { 1, 0, 0, 1, 0,1,0,0,0,0,0,0}};

        /*int[][] start = {{1,1,1,6,3},
                {0,2,5,8,8},
                {2,5,-1,3,-9},
                {5,9,4,6,-12}};*/

     /*int[][] start = {{2,5,-1,27},
                           {0,2,5,-4},
                           {1,1,1,6},};*/

        for (int r = 0; r < start.length; r++)
        {
            int count = 0;
            for (int c = 0; c < start[r].length; c++)
            {
                if (c != 0)
                {
                    if (c != start[r].length -1)
                    {
                        if (start[r][c] >= 0)
                            System.out.print("+");
                        System.out.print(start[r][c] + "" + (char)('a'+c));
                    }
                    else
                        System.out.print("=" + start[r][c]);
                }
                else
                    System.out.print(start[r][c] + "" +(char)('a'+c));
                count++;
            }
            System.out.println();
        }
        System.out.println();

        long time = System.currentTimeMillis();
        Fraction[][] firstArr = new Fraction[start.length][start[0].length-1];
        Fraction[][] secondArr = new Fraction[start.length][1];
        for (int r = 0; r < start.length; r++)
        {
            for (int c = 0; c < start[r].length; c++)
            {
                if (c != start[r].length -1)
                    firstArr[r][c] = new Fraction(start[r][c]);
                else
                    secondArr[r][0] = new Fraction(start[r][c]);
            }
        }

        Fraction[][] inverse = inverseMatrix(firstArr);
        Fraction[][] finalArray = multiplyArrays(inverse,secondArr);


        System.out.println("\nDisplating Results...");
        int count = 0;
        for (int r = 0; r < finalArray.length; r++)
        {
            System.out.println((char)('a' + count) + " = " + finalArray[r][0]);
            count++;
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - time)/1000 + " seconds");
    }
    public static Fraction findDeterminant(Fraction[][] arr)
    {
        if (arr.length == 2)
        {
            Fraction output = new Fraction(0);
            output.add(arr[0][0]);
            output.multiply(arr[1][1]);
            Fraction f2 = new Fraction(0);
            f2.add(arr[0][1]);
            f2.multiply(arr[1][0]);
            output.subtract(f2);
            return output;
        }
        int col = 0;
        Fraction total = new Fraction(0);
        Fraction[][] temp = new Fraction[arr.length-1][arr.length-1];
        int countR = 0;
        int countC = 0;
        Fraction[] nums = new Fraction[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            for (int r = 1; r < arr.length; r++)
            {
                for (int c = 0; c < arr[r].length; c++)
                {
                    if (col != c)
                    {
                        Fraction f = new Fraction(0);
                        f.add(arr[r][c]);
                        temp[countR][countC++] = f;
                        if (countC == temp[0].length)
                        {
                            countC = 0;
                            countR++;
                        }
                    }
                }
            }

            Fraction f = new Fraction(0);
            f.add(arr[0][col++]);
            f.multiply(findDeterminant(temp));
            nums[i] = f;
            if (i % 2 == 0)
                total.add(nums[i]);
            else
                total.subtract(nums[i]);
            countR = 0;
            countC = 0;
        }
        return total;
    }
    public static Fraction[][] findMatrixOfMinors(Fraction[][] arr)
    {
        Fraction[][] temp = new Fraction[arr.length][arr[0].length];
        System.out.println("Finding Matrix of Minors");
        for (int r = 0; r < arr.length; r++)
        {
            for (int c = 0; c < arr[r].length; c++)
            {
                if (arr.length == 13)
                {
                    long start = System.currentTimeMillis();
                    System.out.println("Finding: " + r + " " + c);
                    temp[r][c] = helper(r,c,arr);
                    System.out.println("Found: " + r + " " + c + " in " + ((System.currentTimeMillis()-start)/1000) + " seconds");
                }
                else
                    temp[r][c] = helper(r,c,arr);
            }
        }
        System.out.println("Found Matrix of Minors");
        return temp;
    }
    public static Fraction helper(int row, int col, Fraction[][] arr)
    {
        Fraction[][] temp = new Fraction[arr.length-1][arr.length-1];
        int countR = 0;
        int countC = 0;
        for (int r = 0; r < arr.length; r++)
        {
            for (int c = 0; c < arr[r].length; c++)
            {
                if (row != r && col != c)
                {
                    temp[countR][countC++] = arr[r][c];
                    if (countC == temp.length)
                    {
                        countC = 0;
                        countR++;
                    }
                }
            }
        }
        return findDeterminant(temp);
    }
    public static Fraction[][] findMatrixofCofactors(Fraction[][] arr)
    {
        boolean isEvenPos = true;
        System.out.println("Finding Matrix of Cofactors");
        for (int r = 0; r < arr.length; r++)
        {
            for (int c = 0; c < arr[r].length; c++)
            {
                if (c % 2 == 0 && !isEvenPos)
                    arr[r][c].multiply(-1);
                if (c % 2 == 1 && isEvenPos)
                    arr[r][c].multiply(-1);
            }
            if (isEvenPos)
                isEvenPos = false;
            else
                isEvenPos = true;
        }
        System.out.println("Found Matrix of Cofactors");
        return arr;
    }
    public static Fraction[][] adjugate(Fraction[][] arr)
    {
        System.out.println("Adugating Matrix");
        for (int r = 0; r < arr.length; r++)
        {
            for (int c = r; c < arr[r].length; c++)
            {
                Fraction temp = arr[r][c];
                arr[r][c] = arr[c][r];
                arr[c][r] = temp;
            }
        }
        System.out.println("Adugated Matrix");
        return arr;
    }
    public static Fraction[][] inverseMatrix(Fraction[][] arr)
    {
        Fraction[][] arr01 = findMatrixOfMinors(arr);
        Fraction[][] arr02 = findMatrixofCofactors(arr01);
        Fraction[][] temp = adjugate(arr02);

        Fraction determ = findDeterminant(arr);
        determ.inverse();
        System.out.println(determ);
        Fraction[][] output = new Fraction[temp.length][temp[0].length];

        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[r].length; c++)
            {
                Fraction f = new Fraction(0);
                f.add(determ);
                f.multiply(temp[r][c]);
                output[r][c] = f;
            }
        }
        System.out.println("Completed Inversion");
        return output;
    }
    public static Fraction[][] multiplyArrays(Fraction[][] first, Fraction[][] second)
    {
        Fraction[][] product = new Fraction[first.length][second[0].length];
        for(int i = 0; i < product.length; i++)
        {
            for (int j = 0; j < product[0].length; j++)
            {
                product[i][j] = new Fraction(0);
                for (int k = 0; k < first[0].length; k++)
                {
                    Fraction f = new Fraction(0);
                    f.add(first[i][k]);
                    f.multiply(second[k][j]);
                    product[i][j].add(f);
                }
            }
        }
        System.out.println("Finished Multiplying Matrix");
        return product;
    }
}