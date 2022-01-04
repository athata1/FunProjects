public class SystemOfEquation
{
    public static void main(String[] args)
    {
        double[][] equations = {{1,1,1,6},
                {0,2,5,-4},
                {2,5,-1,27}};

        double[][] firstMatrix = new double[equations.length][equations[0].length-1];
        double[][] secondMatrix = new double[equations.length][1];

        for (int r = 0; r < equations.length; r++)
        {
            for (int c = 0; c < equations[r].length;c++)
            {
                if (c != equations[r].length-1)
                    firstMatrix[r][c] = equations[r][c];
                else
                    secondMatrix[r][0] = equations[r][c];
            }
        }

        for(int r = 0; r < firstMatrix.length; r++)
        {
            for (int c = 0; c < firstMatrix[r].length;c++)
            {
                if (c != 0)
                {
                    if (firstMatrix[r][c] > 0)
                        System.out.print(" +");
                    System.out.print(" " + firstMatrix[r][c] + (char)(c + 'a'));
                }
                else
                    System.out.print("" + firstMatrix[r][c] + (char)(c + 'a'));
            }
            System.out.print(" = " + secondMatrix[r][0]);
            System.out.println();
        }
        System.out.println();

        double[][] vals = multiplyMatricies(inverseMatrix(firstMatrix),secondMatrix);
        for (int i = 0; i < vals.length; i++)
        {
            System.out.println((char)(i+'a') + " = " + vals[i][0]);
        }

    }
    public static double[][] multiplyMatricies(double[][] firstMatrix, double[][] secondMatrix)
    {
        double[][] output = new double[firstMatrix.length][secondMatrix[0].length];
        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[r].length;c++)
            {
                for (int k = 0; k < firstMatrix[0].length; k++)
                {
                    output[r][c] += firstMatrix[r][k] * secondMatrix[k][c];
                }
            }
        }
        return output;
    }
    public static double[][] inverseMatrix(double[][] matrix)
    {
        double determ = 1.0/findDeterminant(matrix);
        double[][] matrix1 = findMatrixOfMinors(matrix);
        double[][] matrix2 = findMatrixOfCofactors(matrix1);
        double[][] output = adjugate(matrix2);
        for (int r = 0; r < matrix.length; r++)
        {
            for (int c = 0; c < matrix[r].length; c++)
            {
                output[r][c]*=determ;
            }
        }
        return output;
    }
    public static double[][] adjugate(double[][] matrix)
    {
        double[][] output = new double[matrix.length][matrix[0].length];
        for (int r = 0; r < matrix.length; r++)
        {
            for (int c = r; c < matrix[r].length; c++)
            {
                output[r][c] = matrix[c][r];
                output[c][r] = matrix[r][c];
            }
        }
        return output;
    }
    public static double[][] findMatrixOfCofactors(double[][] matrix)
    {
        for (int r = 0; r < matrix.length; r++)
        {
            boolean isPos = true;
            if (r%2 ==1)
                isPos = false;
            for (int c = 0; c < matrix[r].length; c++)
            {
                if (!isPos)
                    matrix[r][c]*=-1;
                isPos = !isPos;
            }
        }
        return matrix;
    }
    public static double[][] findMatrixOfMinors(double[][] matrix)
    {
        double[][] output = new double[matrix.length][matrix[0].length];
        for (int r = 0; r < output.length; r++)
        {
            for (int c = 0; c < output[0].length; c++)
            {
                output[r][c] = helper(r,c,matrix);
            }
        }
        return output;
    }
    public static double helper(int rIndex, int cIndex, double[][] matrix)
    {
        double[][] output = new double[matrix.length-1][matrix[0].length-1];
        int countR = 0;
        int countC = 0;
        for (int r = 0; r < matrix.length; r++)
        {
            for (int c = 0; c < matrix.length; c++)
            {
                if (rIndex != r && cIndex != c)
                    output[countR][countC++] = matrix[r][c];
                if (countC == output[0].length)
                {
                    countR++;
                    countC = 0;
                }
            }
        }
        return findDeterminant(output);
    }
    public static double findDeterminant(double[][] matrix)
    {
        if (matrix.length == 2)
        {
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }

        double output = 0;
        for (int i = 0; i < matrix[0].length; i++)
        {
            double[][] newMatrix = new double[matrix.length-1][matrix[0].length-1];
            int countR = 0;
            int countC = 0;
            for (int r = 1; r < matrix.length; r++)
            {
                for (int c = 0; c < matrix.length; c++)
                {
                    if (i != c)
                    {
                        newMatrix[countR][countC++] = matrix[r][c];
                        if (countC == matrix.length-1)
                        {
                            countR++;
                            countC = 0;
                        }
                    }
                }
            }
            if (i %2 == 0)
                output += matrix[0][i]*findDeterminant(newMatrix);
            else
                output -= matrix[0][i]*findDeterminant(newMatrix);
        }
        return output;
    }
    public static void printMatrix(double[][] matrix)
    {
        for (int r = 0; r < matrix.length; r++)
        {
            for (int c = 0; c < matrix[r].length; c++)
            {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}