import java.awt.*;
import java.awt.font.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.util.Scanner;
import java.awt.geom.Rectangle2D;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class TicTacToe extends JPanel implements MouseListener, KeyListener
{
    char[][] board;
    boolean isPlayer1Turn;
    String player1;
    String player2;
    boolean isDone;
    String message;
    int len;
    final int LINEDIST = 100;
    String status;
    ArrayList<Button> buttons = new ArrayList<Button>();
    HashMap<String,Integer> dupp;
    int countMiniMax;
    public TicTacToe()
    {
        setUpBoard();
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        //Set Up Buttons
        buttons.add(new Button(10,80,100,40,new Color (207,255,209),"player"));
        buttons.add(new Button(10,130,100,40,new Color (207,255,209),"random"));
        buttons.add(new Button(10,180,100,40,new Color (207,255,209),"ai"));
        buttons.add(new Button(len*100 - 110,80,100,40,new Color (207,255,209),"player"));
        buttons.add(new Button(len*100 - 110,130,100,40,new Color (207,255,209),"random"));
        buttons.add(new Button(len*100 - 110,180,100,40,new Color (207,255,209),"ai"));
        buttons.add(new Button(len*100/2 - 50,250,100,40,new Color (207,255,209),"Start"));

        player1 = "";
        player2 = "";
        dupp = new HashMap<String,Integer>();
    }

    //Set up inital board and when board resets
    public void setUpBoard()
    {
        isDone = false;
        isPlayer1Turn = true;
        board = new char[3][3];
        len = board.length;
        for (int r = 0; r < len; r++)
        {
            for (int c = 0; c < len;c++)
            {
                board[r][c] = ' ';
            }
        }

        message = "";
        status = "settings";
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        requestFocus(true);
        //Draw Settings Board
        if (status.equals("settings"))
        {
            g.setColor(new Color(152,255,152));
            g.fillRect(0,0,len*LINEDIST,len*LINEDIST);
            g.setColor(Color.BLACK);
            Font font = new Font("Lucida Console", Font.BOLD, 20);
            g.setFont(font);
            g.drawString("Settings",len*100/2-8*12/2,20);

            g.drawString("Player 1", 10,60);
            g.drawString("Player 2", len*100-30 - 8*10,60);

            for (Button b: buttons)
            {
                int x = b.x;
                int y = b.y;
                int height = b.height;
                int width = b.width;
                String s = b.text;
                g.setColor(b.color);
                g.fillRect(b.x,b.y,b.width,b.height);
                g.setColor(Color.BLACK);
                g.drawRect(b.x,b.y,b.width,b.height);

                FontRenderContext frc = new FontRenderContext(null, true, true);
                Rectangle2D r2D = font.getStringBounds(s, frc);
                int rWidth = (int) Math.round(r2D.getWidth());
                int rHeight = (int) Math.round(r2D.getHeight());
                int rX = (int) Math.round(r2D.getX());
                int rY = (int) Math.round(r2D.getY());

                int a = (width / 2) - (rWidth / 2) - rX;
                int c = (height / 2) - (rHeight / 2) - rY;

                g.drawString(s, x + a, y + c);
            }
        }
        //Draw Tic-Tac-Toe board
        else if (status.equals("playing"))
        {
            Graphics2D g2d = (Graphics2D)g;
            g.setColor(Color.BLACK);
            for (int i = 1; i <= len-1; i++)
            {
                g2d.fillRoundRect(i*LINEDIST, 0, 10, len*LINEDIST, 15, 15);
                g2d.fillRoundRect(0, i*LINEDIST, len*LINEDIST, 10, 15, 15);
            }
            g.setFont(new Font("Lucida Console", Font.BOLD, LINEDIST));
            for (int r = 0; r < len; r++)
            {
                for (int c = 0; c < len;c++)
                {
                    if (board[r][c] == 'x')
                        g.setColor(Color.BLUE);
                    else
                        g.setColor(Color.RED);
                    g.drawString("" + board[r][c],c*LINEDIST + LINEDIST/4,r*LINEDIST + LINEDIST*3/4);
                }
            }
            g.setFont(new Font("Lucida Console", Font.BOLD, 40));
            g.setColor(Color.BLACK);
            g.drawString(message,0, len*100 + 40);
            g.setFont(new Font("Lucida Console", Font.BOLD, 30));
            if (!message.equals(""))
                g.drawString("Press R to Reset",0, len*100 + 70);

                //run Random as Next Move
                if (!isDone && ((isPlayer1Turn && player1.equals("random")) || (!isPlayer1Turn && player2.equals("random"))))
                    findRandomMove();

                //run Ai as NextMove
                if (!isDone && ((isPlayer1Turn && player1.equals("ai")) || (!isPlayer1Turn && player2.equals("ai"))))
                    bestMove(board,isPlayer1Turn);
        }
    }


    public char[][] copyOfBoard(char[][] board)
    {
        char[][] output = new char[board.length][board[0].length];
        for (int r = 0; r < output.length; r++)
        {
            for(int c = 0; c < output[r].length; c++)
            {
                output[r][c] = board[r][c];
            }
        }
        return output;
    }
    //Use minimax to find best Move
    public void bestMove(char[][] board, boolean currentPlayer1)
    {
        countMiniMax = 0;
        int[] output = new int[2];
        int max = Integer.MIN_VALUE;
        boolean foundBestMove = false;
        char ch = (isPlayer1Turn) ? 'x': 'o';
        for (int r = 0; r < len; r++)
        {
            for (int c = 0; c < len; c++)
            {
                if (board[r][c] == ' ')
                {
                    board[r][c] = ch;
                    int currVal = minimax(board,!currentPlayer1,false);
                    board[r][c] = ' ';
                    if (currVal > max)
                    {
                        max = currVal;
                        output[0] = r;
                        output[1] = c;
                    }
                    if (max == 1)
                    {
                        foundBestMove = true;
                        break;
                    }
                }
            }
            if (foundBestMove)
                break;
            //System.out.println();
        }
        board[output[0]][output[1]] = ch;
        String winner = checkWinner(board);
        if (winner != null)
        {
            isDone = true;
            if (winner.equals("x"))
                message = "X wins!";
            if (winner.equals("o"))
                message = "O wins!";
            if (winner.equals("tie"))
                message = "Tie!";
        }
        isPlayer1Turn = !isPlayer1Turn;
        System.out.println(countMiniMax);
        repaint();
    }

    //Find and send end state to bestMove method
    public int minimax(char[][] board, boolean currPlayer1Turn, boolean isMaximizing)
    {
        String val = checkWinner(board);
        countMiniMax++;
        //Check if board is complete
        if (val != null)
        {
            if (val.equals("x") && isPlayer1Turn)
                return 1;
            else if (val.equals("o") && isPlayer1Turn)
                return -1;
            else if (val.equals("x") && !isPlayer1Turn)
                return -1;
            else if (val.equals("o") && !isPlayer1Turn)
                return 1;
            else if (val.equals("tie"))
                return 0;
        }

        //Determine if current piece is x or o
        char ch = (currPlayer1Turn) ? 'x': 'o';

        int bestScore = (isMaximizing) ? Integer.MIN_VALUE: Integer.MAX_VALUE;
        boolean isRunning = true;
        boolean foundBestWorst = false;
        for (int r = 0; r < len && isRunning; r++)
        {
            for (int c = 0; c < len && isRunning; c++)
            {
                if (board[r][c] == ' ')
                {
                    board[r][c] = ch;
                    String temp = convertBoardToString(board);
                    int score = -10;
                    if (!dupp.containsKey(temp))
                    {
                        score = minimax(board,!currPlayer1Turn,!isMaximizing);
                        dupp.put(temp,score);
                    }
                    else
                        score = dupp.get(temp);
                    board[r][c] = ' ';
                    bestScore = (isMaximizing) ? Math.max(score,bestScore): Math.min(score,bestScore);
                    if ((isMaximizing && bestScore == 1) || (!isMaximizing && bestScore == -1))
                    {
                        return bestScore;
                    }
                }
            }
        }
        return bestScore;
      
      /*
      if (isMaximizing)
      {
         int bestScore = Integer.MIN_VALUE;
         for (int r = 0; r < len; r++)
         {
            for (int c = 0; c < len; c++)
            {
               if (board[r][c] == ' ')
               {
                  board[r][c] = ch;
                  String temp = convertBoardToString(board);
                  int score = -10;
                  if (!dupp.containsKey(temp))
                  {
                     score = minimax(board,!currPlayer1Turn,false);
                     dupp.put(temp,score);
                  }
                  else
                     score = dupp.get(temp);
                  bestScore = Math.max(score,bestScore);
                  //int score = minimax(board,!currPlayer1Turn,false);
                  //bestScore = Math.max(score,bestScore);
                  board[r][c] = ' ';
               }
            }
         }
         return bestScore;
      }
      else
      {
         int bestScore = Integer.MAX_VALUE;
         for (int r = 0; r < len; r++)
         {
            for (int c = 0; c < len; c++)
            {
               if (board[r][c] == ' ')
               {
                  board[r][c] = ch;
                  String temp = convertBoardToString(board);
                  int score = -10;
                  if (!dupp.containsKey(temp)){
                     score = minimax(board,!currPlayer1Turn,true);
                     dupp.put(temp,score);
                  }
                  else
                     score = dupp.get(temp);
                  bestScore = Math.min(score,bestScore);
                  //int score = minimax(board,!currPlayer1Turn,true);
                  //bestScore = Math.min(score,bestScore);
                  board[r][c] = ' ';
               }
            }
         }
         return bestScore;
      }*/
    }
    public String convertBoardToString(char[][] board)
    {
        String s = "";
        int count = 0;
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board.length; c++)
            {
                if (board[r][c] == ' ')
                    count++;
                else
                {
                    if (count != 0)
                    {
                        s += count;
                        count = 0;
                    }
                    s += board[r][c];
                }
            }
        }
        if (count != 0)
            s += count;
         
      /*for (int r = 0; r < board.length; r++)
      {
         for (int c = 0; c < board.length; c++)
         {
            if (board[r][c] == ' ')
               s += "n";
            else
               s +=  board[r][c];
         }
      }*/
        return s;
    }
    //Determine if there is winner/Loser/Draw/Not Finished
    public String checkWinner(char[][] board)
    {
        int count = 0;
        String output = "";
        for (int r = 0; r < len; r++)
        {
            for (int c = 0; c < len; c++)
            {
                if (board[r][c] != ' ')
                    count++;
            }
        }
        if (count == len*len)
            output = "tie";

        for (int i = 0; i < len; i++)
        {
            if (board[i][0] != ' ' && hor(i,0))
                output = "" + board[i][0];
            if (board[0][i] != ' ' && ver(i,0))
                output = "" + board[0][i];
        }
        if (board[0][0] != ' ' && diagonal1(0))
        {
            output = "" + board[0][0];
        }
        if (board[0][len-1] != ' ' && diagonal2(0))
        {
            output = "" + board[0][len-1];
        }
        if (output.equals(""))
            return null;
        return output;
    }

    public void keyPressed(KeyEvent e)
    {
        //Reset Board if r is pressed
        if (e.getKeyCode() == KeyEvent.VK_R)
        {
            setUpBoard();
        }
        repaint();
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

    public void mouseMoved(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if (status.equals("settings"))
        {
            //Click buttons for player 1 settings
            for (int i = 0; i <= 2; i++)
            {
                Button b = buttons.get(i);
                if (x >= b.x && x <= b.x + b.width && y >= b.y && y <= b.y + b.height)
                {
                    //Set all colors back to original
                    for (int j = 0;j <= 2; j++)
                    {
                        buttons.get(j).color = new Color (207,255,209);
                    }
                    b.color = Color.YELLOW;
                    player1 = b.text;
                }
            }
            //Click buttons for player 2 settings
            for (int i = 3; i <= 5; i++)
            {
                Button b = buttons.get(i);
                if (x >= b.x && x <= b.x + b.width && y >= b.y && y <= b.y + b.height)
                {
                    //Set all colors back to original
                    for (int j = 3;j <= 5; j++)
                    {
                        buttons.get(j).color = new Color (207,255,209);
                    }
                    b.color = Color.YELLOW;
                    player2 = b.text;
                }
            }
            //Start button
            Button b = buttons.get(6);
            if (!player1.equals("") && !player2.equals("") && x >= b.x && x <= b.x + b.width && y >= b.y && y <= b.y + b.height)
            {
                status = "playing";
            }
            repaint();
        }
        else if (status.equals("playing") && ((isPlayer1Turn && player1.equals("player")) || (!isPlayer1Turn && player2.equals("player"))) && !isDone)
        {
            //Player places piece down
            if (x > 0 && x < LINEDIST*len && y > 0 && y < LINEDIST*len)
            {
                x = x/LINEDIST;
                y = y/LINEDIST;
                if (board[y][x] == ' ' && isPlayer1Turn)
                {
                    board[y][x] = 'x';
                    isPlayer1Turn = !isPlayer1Turn;
                }
                else if (board[y][x] == ' ' && !isPlayer1Turn)
                {
                    board[y][x] = 'o';
                    isPlayer1Turn = !isPlayer1Turn;
                }
                //Check if winner is found
                String winner = checkWinner(board);
                if (winner != null)
                {
                    isDone = true;
                    if (winner.equals("x"))
                        message = "X wins!";
                    if (winner.equals("o"))
                        message = "O wins!";
                    if (winner.equals("tie"))
                        message = "Tie!";
                }
            }
            repaint();
        }
    }
    //Create random move for random player
    public void findRandomMove()
    {
        char ch = (isPlayer1Turn) ? 'x': 'o';
        while (true)
        {
            int x = (int)(Math.random()*len);
            int y = (int)(Math.random()*len);
            if (board[y][x] == ' ')
            {
                board[y][x] = ch;
                break;
            }
        }
        //Check winner/loser
        String winner = checkWinner(board);
        if (winner != null)
        {
            isDone = true;
            if (winner.equals("x"))
                message = "X wins!";
            if (winner.equals("o"))
                message = "O wins!";
            if (winner.equals("tie"))
                message = "Tie!";
        }
        isPlayer1Turn = !isPlayer1Turn;
        repaint();
    }
    public boolean hor(int i, int n)
    {
        if (n == len-1)
            return true;
        return hor(i,n+1) && board[i][n] == board[i][n+1];
    }
    public boolean ver(int i, int n)
    {
        if (n == len-1)
            return true;
        return ver(i,n+1) && board[n][i] == board[n+1][i];
    }
    public boolean diagonal1(int n)
    {
        if (n == len - 1)
        {
            return true;
        }
        return diagonal1(n+1) && board[n][n] == board[n+1][n+1];
    }
    public boolean diagonal2(int n)
    {
        if (n == len - 1)
        {
            return true;
        }
        return diagonal2(n+1) && board[n][len-1-n] == board[n+1][len-2-n];
    }
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();
        JFrame jf = new JFrame();
        jf.setTitle("TicTacToe");
        jf.setSize(315,420);
        t.setBackground(Color.WHITE);
        //jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(t);
    }
}