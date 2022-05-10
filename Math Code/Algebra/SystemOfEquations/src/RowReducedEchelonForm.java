public class RowReducedEchelonForm {
    public static void main(String[] args) {
        /*double[][] matrix = {
                {2,-1,0,-1},
                {1,1,-1,2},
                {1,-2,2,-1},
        };*/

        double[][] matrix = {
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
        };


        double[][] output = convertToRowReducedEchelonForm(matrix);
        output = convertToRowReducedEchelonForm(output);
        printMatrix(output);
    }
    public static double[][] convertToRowReducedEchelonForm(double[][] matrix)
    {
        if (matrix.length > matrix[0].length)
            throw new MatrixException("Error: Invalid matrix dimensions");

        double[][] output = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < output.length; i++)
        {
            System.arraycopy(matrix[i], 0, output[i], 0, output[i].length);
        }

        output = convertToReducedEchelonForm(output);

        for (int c = output.length - 1; c >= 0; c--)
        {
            double currentValue = output[c][c];
            for (int r = c - 1; r >= 0; r--)
            {
                double coefficient = -1.0 * output[r][c] / currentValue;
                double[] alteredEquation = multiplyByCoeff(coefficient, output[c]);
                output[r] = addEquations(alteredEquation, output[r]);
            }
        }

        return output;
    }
    public static double[][] convertToReducedEchelonForm(double[][] matrix)
    {
        if (matrix.length > matrix[0].length)
            throw new MatrixException("Error: Invalid matrix dimensions");

        double[][] output = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < output.length; i++)
        {
            System.arraycopy(matrix[i], 0, output[i], 0, output[i].length);
        }
        output = convertToEchelonForm(output);
        for (int c = 0; c < output.length; c++)
        {
            if (output[c][c] != 0)
            {
                output[c] = multiplyByCoeff(1.0 / output[c][c], output[c]);
            }
        }
        return output;
    }

    public static double[][] convertToEchelonForm(double[][] matrix)
    {
        if (matrix.length > matrix[0].length)
            throw new MatrixException("Error: Invalid matrix dimensions");

        double[][] output = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < output.length; i++)
        {
            System.arraycopy(matrix[i], 0, output[i], 0, output[i].length);
        }

        for (int r = 0; r < output.length; r++)
        {
            double currentValue = output[r][r];
            for (int i = r + 1; i < output.length; i++)
            {
                if (currentValue != 0)
                {
                    double coefficient = -1.0 * output[i][r] / currentValue;
                    double[] alteredEquation = multiplyByCoeff(coefficient, output[r]);
                    output[i] = addEquations(alteredEquation, output[i]);
                }
            }
        }
        return output;
    }

    private static void printMatrix(double[][] output)
    {
        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[r].length; c++)
            {
                System.out.printf("%.2f ",output[r][c]);
            }
            System.out.println();
        }
    }
    public static void printArray(double[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static double[] addEquations(double[] equation1, double[] equation2)
    {
        double[] output = new double[equation1.length];
        for (int i = 0; i < output.length; i++)
        {
            output[i] = equation1[i] + equation2[i];
        }
        return output;
    }

    public static double[] multiplyByCoeff(double coefficient, double[] equation)
    {
        double[] output = new double[equation.length];
        for (int i = 0; i < equation.length; i++)
        {
            output[i] = coefficient * equation[i];
        }
        return output;
    }
}
