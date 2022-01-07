public class RowReducedEchelonFormFraction
{
    public static void main(String[] args)
    {
        int[][] matrix = {
                {1,0,-1,2},
                {0,-1,0,-1},
                {1,-2,0,-1},


                /*{1,3,6},
                {2,6,11},*/

                /*{ 8, 8,20, 0, 0,0,0,0,0,0,0,-3},
                { 0, 8, 8, 0, 0,0,0,0,0,0,0,12},
                { 0, 0, 8, 0, 0,0,0,0,0,0,0,-1},
                { 1, 0, 0, 1, 0,1,0,0,0,0,0,0},
                { 6, 7, 1, 0, 6,4,5,1,1,0,0,0},
                { 1, 1, 0, 0, 1,1,1,0,0,0,0,0},
                {18, 6, 7,12, 0,8,4,3,1,1,0,0},
                { 7, 1, 1, 6, 0,5,1,1,0,0,0,0},
                {20,12,18, 8, 0,4,4,2,2,1,1,0},
                {12,18, 6, 0,12,4,8,2,3,1,1,1},
                { 8,20,12, 0, 8,0,4,0,2,0,1,0},*/

                /*{ 0, 0, 8, 0, 0,0,0,0,0,0,0,-1},
                { 0, 8, 8, 0, 0,0,0,0,0,0,0,12},
                { 8, 8,20, 0, 0,0,0,0,0,0,0,-3},
                { 8,20,12, 0, 8,0,4,0,2,0,1,0},
                {20,12,18, 8, 0,4,4,2,2,1,1,0},
                {12,18, 6, 0,12,4,8,2,3,1,1,1},
                {18, 6, 7,12, 0,8,4,3,1,1,0,0},
                { 6, 7, 1, 0, 6,4,5,1,1,0,0,0},
                { 7, 1, 1, 6, 0,5,1,1,0,0,0,0},
                { 1, 1, 0, 0, 1,1,1,0,0,0,0,0},
                { 1, 0, 0, 1, 0,1,0,0,0,0,0,0},*/

                /*{16,23,12,34,-2,-37,109,-141,139,149,29,12,131,74608},
                {13,31,-5,17,29,-67,-16,-101,-7,-201,32,17,171,-4194},
                {81,-12,-5,17,-9,17,39,49,-67,-7,-41,13,121,-15793},
                {91,-43,17,19,17,23,97,101,-101,173,-12,137,217,-16661},
                {71,47,37,37,41,-37,-67,-17,-2,-6,41,-39,-12,-6862},
                {61,13,101,7,117,111,-4,-3,-19,-2,13,17,-91,7802},
                {51,31,37,4,101,-13,-7,-13,-41,-3,31,73,-71,8846},
                {21,12,67,-67,-9,-67,9,98,101,171,12,191,-54,-4544},
                {41,-2,5,17,18,19,20,21,22,23,-11,91,-2,-4050},
                {31,100,2,4,5,10,-10,20,-50,-4,10,67,-4,-4459},
                {17,67,2,-2,-8,-78,-9,-101,107,145,21,-9,-10,37104},
                {11,3,4,71,-45,87,8,34,45,93,104,-8,-20,4737},
                {19,-2,-9,-12,123,61,71,83,91,10,11,-12,82,623},*/
        };

        /*for (int i = 0; i < 1000; i++) {
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[r].length; c++) {
                    matrix[r][c] = (int) (Math.random() * (21) - 10);
                }
            }

            Fraction[][] fractionMatrix = new Fraction[matrix.length][matrix[0].length];
            for (int r = 0; r < fractionMatrix.length; r++) {
                for (int c = 0; c < fractionMatrix[r].length; c++) {
                    fractionMatrix[r][c] = new Fraction(matrix[r][c]);
                }
            }

            Fraction[][] output = convertToRowReducedEchelonForm(fractionMatrix);
            output = convertToRowReducedEchelonForm(output);
            printMatrix(output);

            boolean isCorrect = true;
            for (int r = 0; r < output.length; r++) {
                Fraction result = new Fraction(0);
                for (int c = 0; c < matrix.length; c++) {
                    Fraction f = new Fraction(1);
                    f.multiply(new Fraction(matrix[r][c]));
                    //System.out.print(matrix[0][c] + " * (" + output[c][output[0].length - 1] + ") + ");
                    f.multiply(output[c][output[r].length - 1]);
                    result.add(f);
                }
                if (result.compareTo(new Fraction(matrix[r][output[r].length - 1])) != 0) {
                    isCorrect = false;
                }
            }
            System.out.println((isCorrect) ? "Correct!" : "Incorrect!");
            if (!isCorrect)
            {
                System.out.println("Beginning of original matrix");
                printMatrix(fractionMatrix);
                System.out.println("Ending of original matrix");
            }
        }*/


        Fraction[][] fractionMatrix = new Fraction[matrix.length][matrix[0].length];
        for (int r = 0; r < fractionMatrix.length; r++) {
            for (int c = 0; c < fractionMatrix[r].length; c++) {
                fractionMatrix[r][c] = new Fraction(matrix[r][c]);
            }
        }

        Fraction[][] output = convertToRowReducedEchelonForm(fractionMatrix);
        output = convertToRowReducedEchelonForm(output);
        printMatrix(output);

        boolean isCorrect = true;
        for (int r = 0; r < output.length; r++) {
            Fraction result = new Fraction(0);
            for (int c = 0; c < matrix.length; c++) {
                Fraction f = new Fraction(1);
                f.multiply(new Fraction(matrix[r][c]));
                //System.out.print(matrix[0][c] + " * (" + output[c][output[0].length - 1] + ") + ");
                f.multiply(output[c][output[r].length - 1]);
                result.add(f);
            }
            if (result.compareTo(new Fraction(matrix[r][output[r].length - 1])) != 0) {
                isCorrect = false;
            }
        }
        System.out.println((isCorrect) ? "Correct!" : "Incorrect!");
    }
    public static Fraction[][] convertToRowReducedEchelonForm(Fraction[][] matrix)
    {
        if (matrix.length > matrix[0].length)
            throw new MatrixException("Error: Invalid matrix dimensions");

        //System.out.println("Converting to Reduced Row Echelon Form");

        Fraction[][] output = new Fraction[matrix.length][matrix[0].length];
        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[r].length; c++)
            {
                output[r][c] = new Fraction(matrix[r][c]);
            }
        }

        //System.out.println("Converting to Reduced Echelon Form");
        output = convertToReducedEchelonForm(output);

        for (int c = output.length - 1; c >= 0; c--)
        {
            Fraction pivot = output[c][c];
            for (int r = c - 1; r >= 0; r--)
            {
                if (!pivot.isZero())
                {
                    Fraction coefficient = new Fraction(-1);
                    coefficient.multiply(output[r][c]);
                    coefficient.divide(pivot);
                    Fraction[] alteredEquation = multiplyByCoeff(coefficient, output[c]);
                    output[r] = addEquations(alteredEquation, output[r]);
                }
            }
        }
        //System.out.println("Completed Reduced Row Echelon Form");
        return output;
    }


    public static Fraction[][] convertToReducedEchelonForm(Fraction[][] matrix)
    {
        if (matrix.length > matrix[0].length)
            throw new MatrixException("Error: Invalid matrix dimensions");

        Fraction[][] output = new Fraction[matrix.length][matrix[0].length];
        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[r].length; c++)
            {
                output[r][c] = new Fraction(matrix[r][c]);
            }
        }

        //System.out.println("Converting to Echelon Form");
        output = convertToEchelonForm(output);

        for (int c = 0; c < output.length; c++)
        {
            if (!output[c][c].isZero())
            {
                Fraction coefficient = new Fraction(1);
                coefficient.divide(output[c][c]);
                output[c] = multiplyByCoeff(coefficient, output[c]);
            }
        }
        //System.out.println("Completed Reduced Echelon Form");
        return output;
    }

    public static Fraction[][] convertToEchelonForm(Fraction[][] matrix)
    {

        if (matrix.length > matrix[0].length)
            throw new MatrixException("Error: Invalid matrix dimensions");

        Fraction[][] output = new Fraction[matrix.length][matrix[0].length];
        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[r].length; c++)
            {
                output[r][c] = new Fraction(matrix[r][c]);
            }
        }

        for (int r = 0; r < output.length; r++)
        {
            Fraction pivot = output[r][r];
            for (int i = r + 1; i < output.length; i++)
            {
                if (!pivot.isZero())
                {
                    Fraction coefficient = new Fraction(1);
                    coefficient.multiply(-1);
                    coefficient.multiply(output[i][r]);
                    coefficient.divide(pivot);
                    Fraction[] alteredEquation = multiplyByCoeff(coefficient, output[r]);
                    output[i] = addEquations(alteredEquation, output[i]);
                }
            }
        }
        //System.out.println("Completed Echelon Form");
        return output;
    }

    private static <T> void printMatrix(T[][] output)
    {
        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[r].length; c++)
            {
                System.out.printf("%5s ",output[r][c]);
            }
            System.out.println();
        }
    }
    public static <T>void printArray(T[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static Fraction[] addEquations(Fraction[] equation1, Fraction[] equation2)
    {
        Fraction[] output = new Fraction[equation1.length];
        for (int i = 0; i < output.length; i++)
        {
            Fraction temp = new Fraction(0);
            temp.add(equation1[i]);
            temp.add(equation2[i]);
            output[i] = temp;
        }
        return output;
    }

    public static Fraction[] multiplyByCoeff(Fraction coefficient, Fraction[] equation)
    {
        Fraction[] output = new Fraction[equation.length];
        for (int i = 0; i < equation.length; i++)
        {
            Fraction temp = new Fraction();
            temp.multiply(coefficient);
            temp.multiply(equation[i]);
            output[i] = temp;
        }
        return output;
    }
}
