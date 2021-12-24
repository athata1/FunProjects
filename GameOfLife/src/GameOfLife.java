import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class GameOfLife extends JPanel implements MouseMotionListener,KeyListener,MouseListener, ActionListener
{
    private int[][] board;
    private String status;
    private int width;
    private int squareX;
    private int squareY;
    private int multi;
    private int countZoom;
    private int maxZoom;
    private int boardLength;
    private ArrayList<Button> buttons;
    private Timer tm = new Timer(100,this);
    private int translateX;
    private int translateY;
    private int[][] originalBoard;
    private int halfWidth;
    private int extraWidth;
    private final int MAPPIXELWIDTH = 4;
    public GameOfLife()
    {
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        board = new int[51][51];
        originalBoard = new int[board.length][board[0].length];
        status = "pause";
        countZoom = 2;
        maxZoom = 7;
        boardLength = 510;
        setValues();
        translateX = 50;
        translateY = 100;
        squareX = board[0].length/2;
        squareY = board.length/2;
        buttons = new ArrayList<Button>();
        buttons.add(new Button(boardLength + 20,board.length*MAPPIXELWIDTH +  10,board.length*MAPPIXELWIDTH,50, new Color(128,206,225), "Start"));
        buttons.add(new Button(boardLength + 20,board.length*MAPPIXELWIDTH +  70,board.length*MAPPIXELWIDTH,50, new Color(128,206,225), "Stop"));
        buttons.add(new Button(boardLength + 20,board.length*MAPPIXELWIDTH + 130,board.length*MAPPIXELWIDTH,50, new Color(128,206,225), "Random"));
        buttons.add(new Button(boardLength + 20,board.length*MAPPIXELWIDTH + 190,board.length*MAPPIXELWIDTH,50, new Color(128,206,225), "Controls"));
    }
    public void setValues()
    {
        width = board.length*countZoom/maxZoom;
        multi = boardLength/width;
        halfWidth = width/2;
        extraWidth = (width+1)%2;
        System.out.println(extraWidth);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        requestFocus(true);
        g.translate(translateX,translateY);
        if (status.equals("controls"))
        {
            Font myFont = new Font("Courier New", Font.BOLD, 24);
            g.setFont(myFont);
            g.setColor(new Color(255,203,164));
            g.fillRect(0,0,boardLength,boardLength);

            g.setColor(Color.BLACK);
            g.drawString("W: Up",50,50);
            g.drawString("A: Left",50,100);
            g.drawString("S: Down",50,150);
            g.drawString("D: Right",50,200);
            g.drawString("-: Zoom out",250,50);
            g.drawString("=: Zoom in",250,100);
            g.drawString("R: Reload",250,150);
            g.drawString("ESC: Delete All",250,200);
        }
        else
        {
            int row = 0;
            int col = 0;
            g.setColor(Color.BLACK);
            for (int r = squareY - halfWidth; r <= squareY + halfWidth - extraWidth;r++)
            {
                col = 0;
                for (int c = squareX - halfWidth; c <= squareX + halfWidth - extraWidth;c++)
                {
                    g.drawRect(col*multi,row*multi,multi,multi);
                    if (board[r][c] == 1)
                        g.fillRect(col*multi,row*multi,multi,multi);
                    col++;
                }
                row++;
            }
        }
        Font myFont = new Font("Lucida Console", Font.BOLD, 24);
        g.setFont(myFont);
        for (Button b: buttons)
        {
            g.setColor(b.color);
            g.fillRect(b.x,b.y,b.width,b.height);
            g.setColor(Color.BLACK);
            g.drawRect(b.x,b.y,b.width,b.height);
            g.drawString(b.text,b.x + b.width/2 - 12*b.text.length()/2,b.y + b.height/2 + 12/2);
        }
        g.setFont(new Font("Serif", Font.BOLD, 40));
        g.drawString("Conway's Game of Life", 150,-25);

        for (int r = 0; r < board.length;r++)
        {
            for (int c = 0; c < board[r].length;c++)
            {
                if (board[r][c] == 1)
                    g.setColor(Color.BLACK);
                if (board[r][c] == 0)
                    g.setColor(Color.GRAY);
                if (r == squareY + halfWidth - extraWidth && c <= squareX + halfWidth - extraWidth && c >=squareX - halfWidth)
                    g.setColor(Color.YELLOW);
                if (r == squareY - halfWidth && c <= squareX + halfWidth - extraWidth && c >=squareX - halfWidth)
                    g.setColor(Color.YELLOW);
                if (c == squareX + halfWidth - extraWidth && r <= squareY + halfWidth - extraWidth && r >=squareY - halfWidth)
                    g.setColor(Color.YELLOW);
                if (c == squareX - halfWidth && r <= squareY + halfWidth - extraWidth && r >=squareY - halfWidth)
                    g.setColor(Color.YELLOW);
                g.fillRect(boardLength + 20 + c*MAPPIXELWIDTH,r*MAPPIXELWIDTH,MAPPIXELWIDTH,MAPPIXELWIDTH);
            }
        }

        if (status.equals("play"))
            tm.start();
    }
    public void mouseDragged(MouseEvent e)
    {
        if (status.equals("pause"))
        {
            int posX = (e.getX() - translateX);
            int posY = (e.getY() - translateY);
            int colorY = -1;
            int colorX = -1;
            if (posX >= 0 && posY >= 0 && posX < width*multi && posY < width*multi)
            {
                colorY = posY/multi;
                colorX = posX/multi;
                if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
                    board[colorY + squareY - width/2][colorX + squareX - width/2] = 1;
                if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK)
                    board[colorY + squareY - width/2][colorX + squareX - width/2] = 0;

            }
        }
        repaint();
    }
    public void keyPressed(KeyEvent e)
    {
        if (countZoom != maxZoom && e.getKeyCode() == KeyEvent.VK_MINUS)
        {
            countZoom++;
            setValues();
            if(squareX + halfWidth >= board[0].length)
            {
                squareX = board[0].length-1 - halfWidth + extraWidth;
            }
            if(squareX - halfWidth < 0)
            {
                squareX = halfWidth;
            }
            if(squareY - halfWidth < 0)
            {
                squareY = halfWidth;
            }
            if(squareY + halfWidth >= board.length)
            {
                squareY = board.length-1 - halfWidth + extraWidth;
            }
        }
        else if (countZoom != 1 && e.getKeyCode() == KeyEvent.VK_EQUALS)
        {
            countZoom--;
            setValues();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            for (int r = 0; r < board.length; r++)
            {
                for (int c = 0; c < board[r].length; c++)
                {
                    board[r][c] = 0;
                }
            }
            for (Button but: buttons)
            {
                but.color = new Color(128,206,225);
            }
            status = "pause";
        }
        else if (e.getKeyCode() == KeyEvent.VK_D)
        {
            if (squareX + 1 + halfWidth - extraWidth < board[0].length)
            {
                squareX++;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_A)
        {
            if (squareX - 1 - halfWidth >= 0)
                squareX--;
        }
        else if (e.getKeyCode() == KeyEvent.VK_W)
        {
            if (squareY - 1 - halfWidth >= 0)
                squareY--;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            if (squareY + 1 + halfWidth - extraWidth < board.length)
                squareY++;
        }
        else if (e.getKeyCode() == KeyEvent.VK_R)
        {
            status = "pause";
            tm.stop();
            for (int i =0; i < originalBoard.length;i++)
            {
                for (int j = 0; j < originalBoard[i].length; j++)
                {
                    board[i][j] = originalBoard[i][j];
                }
            }
            for (Button but: buttons)
            {
                but.color = new Color(128,206,225);
            }
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
        Button b = buttons.get(0);
        if (x >= b.x + translateX && x <= b.x + b.width + translateX
                && y >= b.y + translateY && y <= b.y + b.height + translateY)
        {
            for (Button but: buttons)
            {
                but.color = new Color(128,206,225);
            }
            b.color = new Color(28,106,125);
            status = "play";
            for (int i = 0; i < board.length; i++)
            {
                for (int j = 0; j < board[i].length; j++)
                {
                    originalBoard[i][j] = board[i][j];
                }
            }
        }
        b = buttons.get(1);
        if (x >= b.x + translateX && x <= b.x + b.width + translateX
                && y >= b.y + translateY && y <= b.y + b.height + translateY)
        {
            for (Button but: buttons)
            {
                but.color = new Color(128,206,225);
            }
            b.color = new Color(28,106,125);
            status = "pause";
            tm.stop();
        }

        b = buttons.get(2);
        if (x >= b.x + translateX && x <= b.x + b.width + translateX
                && y >= b.y + translateY && y <= b.y + b.height + translateY)
        {
            for (Button but: buttons)
            {
                but.color = new Color(128,206,225);
            }
            b.color = new Color(28,106,125);
            status = "pause";
            tm.stop();
            for (int r = 0; r < board.length;r++)
            {
                for (int c = 0; c < board[r].length;c++)
                {
                    if (Math.random() <= 0.25)
                        board[r][c] = 1;
                    else
                        board[r][c] = 0;
                }
            }
        }
        b = buttons.get(3);
        if (x >= b.x + translateX && x <= b.x + b.width + translateX
                && y >= b.y + translateY && y <= b.y + b.height + translateY)
        {
            for (Button but: buttons)
            {
                but.color = new Color(128,206,225);
            }
            if (status.equals("controls"))
                status = "pause";
            else
            {
                status = "controls";
                b.color = new Color(28,106,125);
            }
            tm.stop();
        }

        repaint();
    }
    public void actionPerformed(ActionEvent e)
    {
        if (status.equals("play"))
            updateBoard();
        repaint();
    }
    public void updateBoard()
    {
        int[][] surroundings = {{0,1},{0,-1},{1,0},{-1,0},{-1,1},{1,-1},{1,1},{-1,-1}};
        int[][] tempBoard = new int[board.length][board[0].length];
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[r].length;c++)
            {
                int count = 0;
                for (int i = 0; i < surroundings.length; i++)
                {
                  /*if (r + surroundings[i][0] < board.length && r + surroundings[i][0] >= 0 &&
                      c + surroundings[i][1] < board[0].length && c + surroundings[i][1] >= 0)
                  {
                     if (board[r+surroundings[i][0]][c+surroundings[i][1]] == 1)
                        count++;
                  }*/
                    if (board[(board.length + r+surroundings[i][0])%board.length][(board[0].length + c+surroundings[i][1])%board[0].length] == 1)
                    {
                        count++;
                    }
                }
                if (board[r][c] == 1)
                {
                    if (count < 2 || count > 3)
                        tempBoard[r][c] = 0;
                    else
                        tempBoard[r][c] = 1;
                }
                if (board[r][c] == 0 && count == 3)
                    tempBoard[r][c] = 1;
            }
        }
        board = tempBoard;
    }
    public static void main(String[] args)
    {
        GameOfLife t = new GameOfLife();
        JFrame jf = new JFrame();
        jf.setTitle("GameOfLife");
        jf.setSize(800,700);
        t.setBackground(Color.WHITE);
        //jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(t);
    }
}
