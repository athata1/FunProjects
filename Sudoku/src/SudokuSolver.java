class SudokuSolver
{
    public static void main(String[] args)
    {
        //Problem 241
      /*int[][] board = {{7,0,2,0,0,6,0,0,3},
                       {0,0,0,2,0,0,8,0,0},
                       {0,5,0,0,0,3,0,0,4},
                       {5,0,3,0,0,0,0,4,0},
                       {0,0,0,0,0,0,0,0,0},
                       {0,1,0,0,0,0,6,0,7},
                       {2,0,0,3,0,0,0,9,0},
                       {0,0,7,0,0,4,0,0,0},
                       {4,0,0,8,0,0,1,0,5}};*/
                       
      /*int[][] board = {{0,0,0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0,0,0}};*/
        //Problem 298
      /*int[][] board = {{0,0,0,5,0,0,9,0,4},
                       {0,0,0,0,0,0,0,0,1},
                       {0,4,0,0,3,8,2,0,0},
                       {0,0,0,9,0,3,8,0,0},
                       {0,6,0,4,0,1,0,3,0},
                       {0,0,9,8,0,5,0,0,0},
                       {0,0,5,2,8,0,0,4,0},
                       {3,0,0,0,0,0,0,0,0},
                       {6,0,7,0,0,4,0,0,0}};*/
        //Bryan commiting sudoku
        int[][] board = {{2,9,5,0,0,0,8,6,0},
                {0,3,1,8,6,5,0,2,7},
                {8,0,6,0,0,0,0,0,0},
                {0,0,0,0,5,0,0,0,6},
                {0,0,0,3,8,7,0,0,0},
                {5,0,0,0,0,6,7,0,0},
                {0,0,0,5,0,0,1,0,9},
                {0,2,0,6,0,0,3,5,0},
                {0,5,4,0,0,8,6,0,2}};


        solveSudoku(board,4,5);
        int countX = 0;
        int countY = 0;

        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[r].length;c++)
            {
                System.out.print(board[r][c] + " ");
                if (c % 3 == 2)
                {
                    countX++;
                    if (countX % 3 != 0)
                        System.out.print("| ");
                }
            }
            System.out.println();
            if (r % 3 == 2 && countY != 2)
            {
                System.out.println("------+-------+------");
                countY++;
            }
        }
    }
    public static void sleepFor(int num)
    {
        long currentTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - currentTime < num){}
    }

    static boolean isPlaying = true;
    public static void solveSudoku(int[][] arr, int r, int c)
    {
        if (isPlaying)
        {
            if (isFilled(arr))
                return;
            while (true)
            {
                if (arr[r][c] != 0)
                {
                    c = (c + 1) % 9;
                    if (c == 0)
                    {
                        r = (r + 1)%9;
                    }
                }
                else
                    break;
            }
            for (int i = 1; i <= 9 && isPlaying; i++)
            {
                if (isValidPlacement(i, arr,r,c))
                {
                    arr[r][c] = i;
                    if (isFilled(arr))
                    {
                        isPlaying = false;
                    }
                    else
                        solveSudoku(arr,r,c);
                }
            }
            if (isPlaying)
                arr[r][c] = 0;
            return;
        }
    }
    public static boolean isFilled(int[][] arr)
    {
        for (int[] nums: arr)
        {
            for (int num: nums)
            {
                if (num == 0)
                    return false;
            }
        }
        return true;
    }
    public static boolean isValidPlacement(int num, int[][] arr, int r, int c)
    {
        for (int row = 0; row < arr.length; row++)
        {
            if (arr[row][c] == num)
                return false;
        }
        for (int col = 0; col < arr[0].length; col++)
        {
            if (arr[r][col] == num)
                return false;
        }
        int posR = r/3*3;
        int posC = c/3*3;
        for (int row = 0; row <= 2; row++)
        {
            for (int col = 0; col <= 2; col++)
            {
                if (arr[posR+row][posC+col] == num)
                    return false;
            }
        }
        return true;
    }
}