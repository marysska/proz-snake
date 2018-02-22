package konieczka.snake;

import java.util.ArrayList;



/**
 *
 *This is the snake object class, it contains the size and the location of the snake
 * @author Maria Konieczka
 *
 */
public class Snake {
	
	/**
	 * The x coordinates of the snake's body, the first coordinate represents the head, the last - the tail.
	 */
	private ArrayList<Integer> xSnake;
	/**
	 * The y coordinates of the snake's body, the first coordinate represents the head, the last - the tail.
	 */
	private ArrayList<Integer> ySnake;
	/**
	 * The length of the snake
	 */
	private int length;
	
	
	/**
	 * The constructor creates two empty ArrayList of int values 
	 */
	public Snake()
	{
		xSnake=new ArrayList<Integer>();
		ySnake=new ArrayList<Integer>();
	}
	
	
	/**
	 * 
	 * @return the x Snake's location List
	 */
	public ArrayList<Integer> getXSnake()
	{
		return xSnake;
	}
	
	/**
	 * 
	 * @return the y Snake's location List
	 */
	public ArrayList<Integer> getYSnake()
	{
		return ySnake;
	}
	
	/**
	 * 
	 * The function sets the snake to start values (the start position and length) 
	 */
	public void clearSnake()
	{
		xSnake.clear();
		ySnake.clear();
		length=3;
		for (int i=0; i<3; i++)
		{
			xSnake.add(6-i); ///na poczatku waz znajduje sie : 6,9  5,9  4,9
			ySnake.add(9);
		}

	}
	
	/**
	 * 
	 * @return the snake's length
	 */
	public int getLength()
	{
		return length;
	}
	
	/**
	 * The function moves the snake for one position 
	 * @param m the Game's model used to check the direction of the snake
	 */
	public void moveSnake(Model m)
	{
		
		int tmp;
		for (int i = length-1; i > 0; i--) {
			xSnake.set(i, xSnake.get(i-1));
			ySnake.set(i, ySnake.get(i-1));
		}
		
		if (m.getDirection()==Direction.LEFT) {
			tmp = xSnake.get(0);
			xSnake.set(0, tmp-1);
		}
		if (m.getDirection()==Direction.RIGHT) {
			tmp = xSnake.get(0);
			xSnake.set(0, tmp+1);
		}
		if (m.getDirection()==Direction.UP) {
			tmp = ySnake.get(0);
			ySnake.set(0, tmp-1);
		}
		if (m.getDirection()==Direction.DOWN) {
			tmp = ySnake.get(0);
			ySnake.set(0, tmp+1);
		}
	}
	
	/**
	 * The function increases the snake.
	 * It is called after eating an apple.
	 * @see Controller#checkApple()
	 */
	public void growSnake()
	{
		xSnake.add(xSnake.get(0));
		ySnake.add(ySnake.get(0));
		length++;
	}
	
	/**
	 * The function checks whether the snake contains point(x,y) it is used to locate an apple 
	 * @param x the x coordinate of the point to check
	 * @param y the y coordinate of the point to check 
	 * @return true when the snake contains (x,y)
	 * @see Model#setPointApple()
	 */
	public boolean snakeContain(int x, int y) ///true jest taki element false nie ma
	{
		for (int i=0; i<length; i++)
		{
			if(xSnake.get(i)==x && ySnake.get(i)==y )
			{
				return true;
			}
		}
		return false;
	}

}
