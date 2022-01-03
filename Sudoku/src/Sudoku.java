import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sudoku extends JPanel
{
    int[][] board = {{0,0,0,4,0,0,6,5,0},
            {9,0,5,8,0,1,0,0,0},
            {0,2,0,0,0,0,0,0,0},
            {0,0,8,0,0,7,0,6,0},
            {1,0,0,0,3,0,0,0,2},
            {0,6,0,2,0,0,7,0,0},
            {0,0,0,0,0,0,0,4,0},
            {0,0,0,5,0,8,2,0,9},
            {0,9,4,0,0,3,0,0,0}};
    boolean[][] statics = new boolean[9][9];
    int count = 0;
    boolean isRunning = true;
    public Sudoku()
    {
        for (int r = 0; r < statics.length; r++)
        {
            for (int c = 0; c < statics[r].length; c++)
            {
                if (board[r][c] != 0)
                {
                    statics[r][c] = true;
                }
                else
                {
                    statics[r][c] = false;
                }
            }
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        requestFocus(true);
        if (count == 0)
        {
            solveSudoku(board,0,0);
        }
        count++;
        drawBoard(g);
    }
    public void sleepFor(int num)
    {
        long currentTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - currentTime < num){}
    }
    public void drawBoard(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setFont(new Font("Times New Roman",Font.BOLD, 12));
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[r].length; c++)
            {
                if (!statics[r][c] && board[r][c] != 0)
                {
                    g.setColor(new Color(119,221,119));
                    g.fillRect(2+c*30,2+r*30,30,30);
                }
                g2d.setColor(Color.BLACK);
                if (board[r][c] != 0)
                    g.drawString("" + board[r][c],2+c*30+11,2+r*30+20);
            }
        }


        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.GRAY);
        for (int i = 0; i <= 9; i++)
        {
            g2d.drawLine(2,2 + i*30,270,2+i*30);
            g2d.drawLine(2 + i*30,2,2+i*30,270);
        }
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        for (int i = 0; i <= 9; i+=3)
        {
            g2d.drawLine(2,2 + i*30,270,2+i*30);
            g2d.drawLine(2 + i*30,2,2+i*30,270);
        }
    }
    public void solveSudoku(int[][] arr, int r, int c)
    {
        if (isRunning)
        {
            while (true)
            {
                if (arr[r][c] != 0)
                {
                    c = (c + 1) % 9;
                    if (c==0)
                    {
                        r = (r+1)%9;
                    }
                }
                else
                {
                    break;
                }
            }
            for (int i = 1; i <= 9 && isRunning; i++)
            {
                if (isValidPlacement(i, arr,r,c))
                {
                    arr[r][c] = i;
                    repaintIm();
                    sleepFor(10);
                    if (!isFilled())
                    {
                        solveSudoku(arr,r,c);
                    }
                    else
                    {
                        if (isRunning)
                        {
                            for (int[] n: arr)
                            {
                                for (int a: n)
                                {
                                    System.out.print(a + " ");
                                }
                                System.out.println();
                            }
                            isRunning = false;
                            System.out.println();
                        }
                    }
                    if (isRunning)
                    {
                        arr[r][c] = 0;
                        repaintIm();
                        sleepFor(10);
                    }
                }
            }
        }
    }
    public void repaintIm()
    {
        paintImmediately(0,0,500,500);
    }
    public boolean isFilled()
    {
        for (int[] nums: board)
        {
            for (int num: nums)
            {
                if (num == 0)
                    return false;
            }
        }
        return true;
    }
    public boolean isValidPlacement(int num, int[][] arr, int r, int c)
    {
        for (int row = 0; row < arr.length; row++)
        {
            if (arr[row][c] == num)
            {
                return false;
            }
        }
        for (int col = 0; col < arr.length; col++)
        {
            if (arr[r][col] == num)
                return false;
        }
        int posR = -1;
        int posC = -1;
        if (r <= 2)
            posR = 0;
        else if (r > 2 && r <= 5)
            posR = 3;
        else
            posR = 6;

        if (c <= 2)
            posC = 0;
        else if (c > 2 && c <= 5)
            posC = 3;
        else
            posC = 6;
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

    public static void main(String[] args)
    {
        Sudoku t = new Sudoku();
        JFrame jf = new JFrame();
        jf.setTitle("Sudoku");
        jf.setSize(285,310);
        jf.setResizable(false);
        t.setBackground(new Color(249,228,183));
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(t);
    }
}