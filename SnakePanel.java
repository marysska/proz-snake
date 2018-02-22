package konieczka.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * This is the game panel class. 
 * It represents the board on the frame.
 * The class contains the size and scale 
 * and necessary date to paint snake and apple 
 * @author Maria Konieczka
 *
 */
public class SnakePanel extends JPanel
{
	/**
	 * The scale of the board (how many pixels for one cell of the board).
	 */
	final int SCALE =15; 
	/**
	 * The size of the board. The board is square. 
	 */
	private final int BOARD_SIZE=25;
	/**
	 * The coordinates of an apple.
	 */
	private Point point;
	/**
	 * The snake's coordinates.
	 */
	private ArrayList<Integer> x, y;
	/**
	 * The icon of an apple.
	 */
	Image appleImage;
	/**
	 * The boolean variable. It informs whether during the download of an apple image, an error occurs.  
	 */
	boolean error;
	
	/**
	 * The constructor creates the empty points of snake and apple 
	 * It downloads the image of an apple
	 */
	public SnakePanel()
	{
		super();
		point=new Point();
		x=new ArrayList<Integer>();
		y=new ArrayList<Integer>();
		error=false;
		try
		{
		appleImage= ImageIO.read(new File("apple2.png"));
	
		}
		catch (Exception e)
		{
			System.out.println("Cannot open or read the file");
			error=true;
		}
	}
	/**
	 * The functions sets the date. 
	 * It is used before painting and repainting the SnakePanel.
	 * @param xTemp the x coordinate array of snake 
	 * @param yTemp the y coordinate array of snake
	 * @param pTemp the apple point 
	 */
	public void setTheDate( ArrayList<Integer> xTemp, ArrayList<Integer> yTemp, Point pTemp)
	{
		point= pTemp;
		x=xTemp;
		y=yTemp;
	}
	
	/**
	 * The function paints all the elements of the board: the apple and the snake. 
	 * It is called by repaint() function.
	 * @param g the graphics element 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		paintBackground(g);
		paintApple(g);
		paintSnake(g);	
		
	}

	/**
	 * The function paint the Background 
	 * @param g the graphics element
	 */
	private void paintBackground(Graphics g)
	{
		g.setColor(new Color(187,187,187));
		g.fillRect(0, 0, BOARD_SIZE* SCALE,  BOARD_SIZE* SCALE);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, BOARD_SIZE* SCALE-1,  BOARD_SIZE* SCALE-1);
	}
	
	/**
	 * The function paints the apple on appropriate place on the board
	 * If the icon wasn't downloaded, it paints an oval instead of an apple
	 * @param g the graphics element used to print an apple
	 */
	private void paintApple(Graphics g)
	{
		if(error==false)
		{
			g.drawImage(appleImage, SCALE*point.x, SCALE*point.y,null);
		}
		
		else
		{
			g.setColor(new Color( 178,34,34));
			g.fillOval(point.x * SCALE , point.y * SCALE, SCALE, SCALE);
		}
	}
	/**
	 *The function paints the body of the snake. The body is composed with rectangles. 
	 * @param g the graphics element used to paint the snake
	 */
	private void paintSnake(Graphics g)
	{
		int i= x.size();
		for (int k=0; k<i; k++)
		{
			if(k==0)
			{
				g.setColor(new Color(91,93,116)); ///head of the snake
				g.fillRect(x.get(k) * SCALE , y.get(k) * SCALE, SCALE, SCALE);
			}
			else
			{
				g.setColor(new Color(102,153,102));
				g.fillRect(x.get(k) * SCALE , y.get(k) * SCALE, SCALE, SCALE);
			}
			
		}
	}
}
