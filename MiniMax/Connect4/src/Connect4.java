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
import java.io.File;
import java.io.IOException;

public class Connect4 extends JPanel implements MouseListener,MouseMotionListener,KeyListener
{
    char[][] board = {{' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '},
            {'r','y','y','r','r',' ',' '},
            {'r','y','r','y','r','r','y'},
            {'r','y','r','y','r','y','r'}};
    boolean isPlayer1Turn;
    String player1;
    String player2;
    boolean isDone;
    String message;
    final int LINEDIST = 100;
    String status;
    int hoverIndex;
    int connect;
    int miniMaxCount;
    HashMap<String, Integer> dupp;
    public Connect4()
    {
        setUpBoard();
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        player1 = "player";
        player2 = "ai";
        connect = 4;
    }

    //Set up inital board and when board resets
    public void setUpBoard()
    {
        isDone = false;
        isPlayer1Turn = true;
        hoverIndex = 0;
      /*board = new char[6][7];
      
      for (int r = 0; r < board.length; r++)
      {
         for (int c = 0; c < board[r].length;c++)
         {
            board[r][c] = ' ';
         }
      }*/
        message = "";
        status = "playing";
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        requestFocus(true);
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.GRAY);
        g.fillRect(0,0,board[0].length*LINEDIST, board.length*LINEDIST);
        g.setColor(Color.BLACK);
        for (int i = 0; i <= board.length; i++)
        {
            g.drawLine(0, i*LINEDIST, board[0].length*LINEDIST, i*LINEDIST);
        }
        for (int i = 0; i <= board[0].length; i++)
        {
            g.drawLine(i*LINEDIST, 0, i*LINEDIST, board.length*LINEDIST);
        }

        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                if (board[r][c] == ' ')
                    g.setColor(Color.WHITE);
                else if (board[r][c] == 'y')
                    g.setColor(Color.YELLOW);
                else
                    g.setColor(Color.RED);
                fillCircle(c*LINEDIST + LINEDIST/2,r*LINEDIST + LINEDIST/2,40,g);
            }

        }
        g.setFont(new Font("Lucida Console", Font.BOLD, 40));
        g.setColor(Color.BLACK);
        g.drawString(message,0, board.length*100 + 40);

        int hoverRow = -2;
        g.setColor(Color.BLUE);
        for (int r = 0; r < board.length; r++)
        {
            if (board[r][hoverIndex] != ' ')
            {
                hoverRow = r-1;
                break;
            }
        }
        if (hoverRow == -2)
        {
            hoverRow = board.length-1;
        }
        fillCircle(hoverIndex*LINEDIST + LINEDIST/2,(hoverRow)*LINEDIST + LINEDIST/2,45,g);
        g.setColor(Color.WHITE);
        fillCircle(hoverIndex*LINEDIST + LINEDIST/2,(hoverRow)*LINEDIST + LINEDIST/2,40,g);

        if (!isDone && ((isPlayer1Turn && player1.equals("random")) || (!isPlayer1Turn && player2.equals("random"))))
            findRandomMove();
        if (!isDone && ((isPlayer1Turn && player1.equals("ai")) || (!isPlayer1Turn && player2.equals("ai"))))
            bestMove();
    }
    public void bestMove()
    {
        int index = -2;
        miniMaxCount = 0;
        dupp = new HashMap<String, Integer>();
        int bestScore = Integer.MIN_VALUE;
        char ch = (isPlayer1Turn) ? 'y':'r';
        for (int i = 0; i < board[0].length; i++)
        {
            if (board[0][i] == ' ')
            {
                addPiece(i,ch);
                int score = minimax(board,!isPlayer1Turn,false);
                removePiece(i);
                if (score > bestScore)
                {
                    bestScore = score;
                    index = i;
                    if (bestScore == 1)
                        break;
                }
            }
            if (bestScore == 1)
                break;
        }
        addPiece(index,ch);
        System.out.println(miniMaxCount);
        isPlayer1Turn = !isPlayer1Turn;
        //Check if winner is found
        String winner = checkWinner(board);
        if (winner != null)
        {
            isDone = true;
            if (winner.equals("y"))
                message = "Yellow wins!";
            if (winner.equals("r"))
                message = "Red wins!";
            if (winner.equals("tie"))
                message = "Tie!";
        }
        repaint();

    }
    public int minimax(char[][] board, boolean currentPlayer1Turn, boolean isMaximizing)
    {
        String winner = checkWinner(board);
        if (winner != null)
        {
            miniMaxCount++;
            if (winner.equals("y") && isPlayer1Turn)
                return 1;
            if (winner.equals("r") && isPlayer1Turn)
                return -1;
            if (winner.equals("y") && !isPlayer1Turn)
                return -1;
            if (winner.equals("r") && !isPlayer1Turn)
                return 1;
            if (winner.equals("tie"))
                return 0;
        }
        char ch = (currentPlayer1Turn) ? 'y':'r';

        //Determine if win/lose in one turn
        int lossIndex = -1;
        int winIndex = -1;
        for (int i = 0; i < board[0].length; i++)
        {
            if (board[0][i] == ' ')
            {
                addPiece(i,ch);
                String test = checkWinner(board);
                if (test != null)
                {
                    winIndex = i;
                }
                removePiece(i);
            }
        }
        char notCh = (currentPlayer1Turn) ? 'r':'y';
        for (int i = 0; i < board[0].length; i++)
        {
            if (board[0][i] == ' ')
            {
                addPiece(i,notCh);
                String test = checkWinner(board);
                if (test != null)
                    lossIndex = i;
                removePiece(i);
            }
        }
        if (lossIndex != -1 || winIndex != -1)
        {
            int index = -1;
            if (lossIndex != -1)
                index = lossIndex;
            if(winIndex != -1)
                index = winIndex;
            addPiece(index,ch);
            String temp = convertBoardToString(board);
            int score = -10;
            if (!dupp.containsKey(temp))
            {
                score = minimax(board,!currentPlayer1Turn,!isMaximizing);
                dupp.put(temp,score);
            }
            else
            {
                score = dupp.get(temp);
            }
            removePiece(index);
            return score;
        }
        //Do minimax on all positions
        else
        {
            if (isMaximizing)
            {
                int bestScore = Integer.MIN_VALUE;
                for (int i = 0; i < board[0].length; i++)
                {
                    if (board[0][i] == ' ')
                    {
                        addPiece(i,ch);
                        String temp = convertBoardToString(board);
                        int score = -10;
                        if (!dupp.containsKey(temp))
                        {
                            score = minimax(board,!currentPlayer1Turn,!isMaximizing);
                            dupp.put(temp,score);
                        }
                        else
                        {
                            score = dupp.get(temp);
                        }
                        bestScore = Math.max(score,bestScore);
                        removePiece(i);
                        if (bestScore == 1)
                            break;
                    }
                }
                return bestScore;
            }
            else
            {
                int bestScore = Integer.MAX_VALUE;
                for (int i = 0; i < board[0].length; i++)
                {
                    if (board[0][i] == ' ')
                    {
                        addPiece(i,ch);
                        String temp = convertBoardToString(board);
                        int score = 10;
                        if (!dupp.containsKey(temp))
                        {
                            score = minimax(board,!currentPlayer1Turn,!isMaximizing);
                            dupp.put(temp,score);
                        }
                        else
                        {
                            score = dupp.get(temp);
                        }
                        bestScore = Math.min(score,bestScore);
                        removePiece(i);
                        if (bestScore == -1)
                            return bestScore;
                    }
                }
                return bestScore;
            }
        }
    }
    public String convertBoardToString(char[][] board)
    {
        String output = "";
        int count = 0;
        for (int r = 0; r < board.length;r++)
        {
            for (int c = 0; c < board[r].length;c++)
            {
                if (board[r][c] == ' ')
                    count++;
                else
                {
                    if (count != 0)
                    {
                        output += count;
                        count=0;
                    }
                    output += board[r][c];
                }
            }
        }
        if (count != 0)
            output += count;
        return output;
    }
    public void findRandomMove()
    {
        char ch = (isPlayer1Turn) ? 'y':'r';

        while (true)
        {
            int rand = (int)(Math.random()*7);
            if (board[0][rand] == ' ')
            {
                addPiece(rand,ch);
                break;
            }
        }
        isPlayer1Turn = !isPlayer1Turn;

        //Check if winner is found
        String winner = checkWinner(board);
        if (winner != null)
        {
            isDone = true;
            if (winner.equals("y"))
                message = "Yellow wins!";
            if (winner.equals("r"))
                message = "Red wins!";
            if (winner.equals("tie"))
                message = "Tie!";
        }
        repaint();
    }
    public String checkWinner(char[][] board)
    {
        int count = 0;
        String output = "";
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[r].length; c++)
            {
                if (board[r][c] != ' ')
                    count++;
            }
        }
        if (count == board.length*board[0].length)
            output = "tie";


        for (int r = 0; r < board.length; r++)
        {
            for (int c= 0; c < board[r].length; c++)
            {
                if (c < board[r].length - (connect-1) && board[r][c] != ' ' && hor(r,c,0))
                    output = "" + board[r][c];
                else if (r < board.length - (connect-1) && board[r][c] != ' ' && ver(r,c,0))
                    output = "" + board[r][c];
                else if (r < board.length - (connect-1) && c < board[r].length - (connect-1) && board[r][c] != ' ' && diag1(r,c,0))
                    output = "" + board[r][c];
                else if (r < board.length - (connect-1) && c < board[r].length - (connect-1) && board[r+(connect-1)][c] != ' ' && diag2(r+(connect-1),c,0))
                    output = "" + board[r+(connect-1)][c];
            }
        }
        if (output.equals(""))
            return null;
        return output;
    }
    public boolean diag2(int r, int c, int num)
    {
        if (num == (connect-1))
            return true;
        return board[r][c] == board[r-1][c+1] && diag2(r-1,c+1,num+1);
    }
    public boolean diag1(int r, int c, int num)
    {
        if (num == (connect-1))
            return true;
        return board[r][c] == board[r+1][c+1] && diag1(r+1,c+1,num+1);
    }
    public boolean hor(int r, int c, int num)
    {
        if (num == (connect-1))
            return true;
        return board[r][c] == board[r][c+1] && hor(r,c+1,num+1);
    }
    public boolean ver(int r, int c, int num)
    {
        if (num == (connect-1))
            return true;
        return board[r][c] == board[r+1][c] && ver(r+1,c,num+1);
    }
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        int x = e.getX();
        hoverIndex = x/LINEDIST;
        if (hoverIndex < 0)
            hoverIndex = 0;
        if (hoverIndex >= board[0].length)
            hoverIndex = board[0].length-1;
        repaint();
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

    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e)
    {
        if (status.equals("playing") && !isDone && ((isPlayer1Turn && player1.equals("player")) || (!isPlayer1Turn && player2.equals("player"))))
        {
            int x = e.getX();

            //Player places piece down
            if (x > 0 && x < LINEDIST*board[0].length)
            {
                x = x/LINEDIST;
                if (board[0][x] == ' ' && isPlayer1Turn)
                {
                    addPiece(x,'y');
                    isPlayer1Turn = !isPlayer1Turn;
                }
                else if (board[0][x] == ' ' && !isPlayer1Turn)
                {
                    addPiece(x,'r');
                    isPlayer1Turn = !isPlayer1Turn;
                }
                //Check if winner is found
                String winner = checkWinner(board);
                if (winner != null)
                {
                    isDone = true;
                    if (winner.equals("y"))
                        message = "Yellow wins!";
                    if (winner.equals("r"))
                        message = "Red wins!";
                    if (winner.equals("tie"))
                        message = "Tie!";
                }
            }
        }
        repaint();
    }
    public void addPiece(int index,char colorPiece)
    {
        int col = -2;
        for (int r = 0; r < board.length; r++)
        {
            if (board[r][index] != ' ')
            {
                col = r-1;
                break;
            }
        }
        if (col == -2)
        {
            board[board.length-1][index] = colorPiece;
            return;
        }
        board[col][index] = colorPiece;
    }
    public void removePiece(int index)
    {
        int col = -2;
        for (int r = 0; r < board.length; r++)
        {
            if (board[r][index] != ' ')
            {
                col = r;
                break;
            }
        }
        if (col == -2)
            return;
        board[col][index] = ' ';
    }
    public void fillCircle(int x,int y,int radius, Graphics g)
    {
        g.fillOval(x-radius,y-radius,radius*2,radius*2);
    }
    public void drawCircle(int x,int y,int radius, Graphics g)
    {
        g.drawOval(x-radius,y-radius,radius*2,radius*2);
    }
    public static void main(String[] args)
    {
        Connect4 t = new Connect4();
        JFrame jf = new JFrame();
        jf.setTitle("TicTacToe");
        jf.setSize(800,800);
        t.setBackground(Color.WHITE);
        //jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(t);
    }
}