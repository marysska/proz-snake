package konieczka.snake;

import java.awt.Point;
import java.util.ArrayList;
import java.lang.Exception;

/**
 * 
 * 
 *This is the model of the snake game
 *The class contains all the necessary date: 
 *direction, size of the board, the snake , the location of the apple and the result.
 *@author Maria Konieczka
 *
 */
public class Model 
{
	/**
	 * The size of the board: the board is square.
	 */
	public final int BOARD_SIZE=25;
	
	/**
	 * The snake's direction.
	 */
	private Direction d;
	/**
	 * The coordinates of an apple.
	 */
	private Point pointApple;
	/**
	 * All the date connected with the snake. Including the size, the coordinates of each part of the body.
	 */
	private Snake snake;
	/**
	 * The current game's result.
	 */
	private int result;
	
	
	/**
	 * The contructor creates the snake and the apple point 
	 */
	public Model()
	{
		snake=new Snake ();
		pointApple=new Point();
		
	}
	
	
	/**
	 * 
	 * @return the x snake coordinates List
	 */
	public ArrayList<Integer> getXSnake()
	{
		return snake.getXSnake();
	}
	
	/**
	 * 
	 * @return the y snake coordinates List
	 */
	public ArrayList<Integer> getYSnake()
	{
		return snake.getYSnake();
	}
	/**
	 * 
	 * @return the snake's direction
	 */
	public Direction getDirection()
	{
		return d;
	}
	
	/**
	 * the function moves the snake for a one position depending on the snake's direction
	 * @see Snake#moveSnake(Model)
	 */
	public void moveTheSnake()
	{
		snake.moveSnake(this);
	}
	/**
	 * 
	 * @return the apples coordinates
	 */
	public Point getPointApple()
	{
		return pointApple;
	}
	
	/**
	 * This function changes the direction of the snake if it is possible
	 * @param temp the current direction of the snake
	 * @return true when the operation succeeds 
	 */
	public boolean setDirection (Direction temp) //true udalo sie , false nie
	{
		boolean flag=false;
		if (temp==Direction.UP || temp==Direction.DOWN)
		{
			if (this.d==Direction.RIGHT || this.d==Direction.LEFT )
			{
				this.d=temp;
				flag=true;
			}
		}
		if (temp==Direction.RIGHT || temp==Direction.LEFT)
		{
			if (this.d==Direction.UP || this.d==Direction.DOWN )
			{
				this.d=temp;
				flag=true;
			}
		}
		return flag;
	};
	
	/**
	 * the function increases the snake 
	 * it is called after eating an apple
	 * @see Controller#checkApple()
	 * @see Snake#growSnake()
	 */
	public void add ()
	{
		snake.growSnake();
	}

	
	/**
	 * 
	 * the function is used to locate the apple 
	 * it uses Math.random() function to create the point
	 * if the generated point is not available (the snake contains this point), 
	 * the function checks the point in the generated point's neighbourhood  
	 * @throws Exception no possible place for an apple (the snake's size equals to the board's size)
	 */
	public void setPointApple () throws Exception
	{ 		
	 	boolean bl=true;
	 	boolean dir=false; /// poruszamy sie najpierw w prawo (0), po dojsciu do konca w lewo (1)
	 	int xTemp, yTemp, xTemp1, yTemp1;
	 	int count=0;
		xTemp=(int)(Math.random() * BOARD_SIZE );
		yTemp=(int)(Math.random() * BOARD_SIZE );
		xTemp1=xTemp;
		yTemp1=yTemp;
		while (bl)
		{
			if (snake.snakeContain(xTemp, yTemp))
			{
				if (dir==false) ///w prawo idziemy
				{
					if (xTemp!=24)
					{
						xTemp++;
						count++;
						continue;
					}
					else
					{
						if (yTemp!=24)
						{
							xTemp=0;
							yTemp++;
							count++;
							continue;
						}
						else 
						{
							xTemp=xTemp1;
							yTemp=yTemp1;
							dir=true;
						}
					}
				}
				else  /// w lewo
				{
				if (xTemp!=0)
					{
						xTemp--;
						count++;
						continue;
					}
				else
				{
					if (yTemp!=0)
					{
						yTemp--;
						xTemp=24;
						count++;
						continue;
					}
					else 
					{
						if (dir==true && xTemp==0 && yTemp==0 && (snake.snakeContain(xTemp, yTemp)))
						{
							System.out.println("No possible place for an apple ");
							bl=false; 
							throw  new Exception("You win");
						}
							
					}
				}
				}		
			}
			else
			{
				bl = false;
				//System.out.println(count);
				pointApple.setLocation(xTemp, yTemp);				
			}
		}
	}
	
	/**
	 * The function sets the result. 
	 * Result depends on the mode (easy :10, medium :12 and hard 15 for one eaten apple).
	 * @param m mode of the game
	 */
	public void setResult(Mode m)
	{
		
		if (m==Mode.EASY)
		{
			result=(snake.getLength()-3)*10;
		}
		if (m==Mode.MEDIUM)
		{
			result=(snake.getLength()-3)*12;
		}
		if (m==Mode.HARD)
		{
			result=(snake.getLength()-3)*15;
		}
		
	}
	
	/**
	 * 
	 * @return the reult of the game
	 */
	public int getResult()
	{
		return result;
	}
	
	/**
	 * The function set the start values of the game. It is used before starting a new game. 
	 * @see Snake#clearSnake()
	 */
	public void clear()
	{
		snake.clearSnake();
		d=Direction.RIGHT;
		try
		{
			setPointApple();
		}
		catch(Exception e)
		{
			//impossible situation, the board is cleaned
		}
		result=0;
	}
	/**
	 * 
	 * @return the length of the snake
	 * @see Snake#getLength()
	 */
	public int getLength()
	{
		return snake.getLength();
	}
}
