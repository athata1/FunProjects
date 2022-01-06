public class RowReducedEchelonFormFraction {
    public static void main(String[] args) {
        /*int[][] matrix = {
                {0,-1,0,-1},
                {1,1,-1,2},
                {1,-2,2,-1},
        };*/
        /*int[][] matrix = {
                { 8, 8,20, 0, 0,0,0,0,0,0,0,-3},
                { 0, 8, 8, 0, 0,0,0,0,0,0,0,12},
                { 0, 0, 8, 0, 0,0,0,0,0,0,0,-1},
                { 1, 0, 0, 1, 0,1,0,0,0,0,0,0},
                { 6, 7, 1, 0, 6,4,5,1,1,0,0,0},
                { 1, 1, 0, 0, 1,1,1,0,0,0,0,0},
                {18, 6, 7,12, 0,8,4,3,1,1,0,0},
                { 7, 1, 1, 6, 0,5,1,1,0,0,0,0},
                {20,12,18, 8, 0,4,4,2,2,1,1,0},
                {12,18, 6, 0,12,4,8,2,3,1,1,1},
                { 8,20,12, 0, 8,0,4,0,2,0,1,0},
        };*/

        int[][] matrix = {
                { 0, 0, 8, 0, 0,0,0,0,0,0,0,-1},
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

        Fraction[][] fractionMatrix = new Fraction[matrix.length][matrix[0].length];
        for (int r = 0; r < fractionMatrix.length;r++)
        {
            for (int c = 0; c < fractionMatrix[r].length; c++)
            {
                fractionMatrix[r][c] = new Fraction(matrix[r][c]);
            }
        }

        Fraction[][] output = convertToRowReducedEchelonForm(fractionMatrix);
        output = convertToRowReducedEchelonForm(output);
        printMatrix(output);
    }
    public static Fraction[][] convertToRowReducedEchelonForm(Fraction[][] matrix)
    {
        if (matrix.length > matrix[0].length)
            throw new MatrixException("Error: Invalid matrix dimensions");

        System.out.println("Converting to Reduced Row Echelon Form");

        Fraction[][] output = new Fraction[matrix.length][matrix[0].length];
        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[r].length; c++)
            {
                output[r][c] = new Fraction(matrix[r][c]);
            }
        }

        System.out.println("Converting to Reduced Echelon Form");
        output = convertToReducedEchelonForm(output);

        for (int c = output.length - 1; c >= 0; c--)
        {
            Fraction currentValue = output[c][c];
            for (int r = c - 1; r >= 0; r--)
            {
                if (currentValue.compareTo(new Fraction(0)) != 0)
                {
                    Fraction coefficient = new Fraction(-1);
                    coefficient.multiply(output[r][c]);
                    coefficient.divide(currentValue);
                    Fraction[] alteredEquation = multiplyByCoeff(coefficient, output[c]);
                    output[r] = addEquations(alteredEquation, output[r]);
                }
            }
        }
        System.out.println("Completed Reduced Row Echelon Form");
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

        System.out.println("Converting to Echelon Form");
        output = convertToEchelonForm(output);

        for (int c = 0; c < output.length; c++)
        {
            if (output[c][c].compareTo(new Fraction(0)) != 0)
            {
                Fraction coefficient = new Fraction(1);
                coefficient.divide(output[c][c]);
                output[c] = multiplyByCoeff(coefficient, output[c]);
            }
        }
        System.out.println("Completed Reduced Echelon Form");
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
            Fraction currentValue = output[r][r];
            for (int i = r + 1; i < output.length; i++)
            {
                Fraction coefficient = new Fraction(1);
                coefficient.multiply(-1);
                coefficient.multiply(output[i][r]);
                coefficient.divide(currentValue);
                Fraction[] alteredEquation = multiplyByCoeff(coefficient, output[r]);
                output[i] = addEquations(alteredEquation,output[i]);
            }
        }
        System.out.println("Completed Echelon Form");
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
