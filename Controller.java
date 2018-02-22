package konieczka.snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

/** 
 * 
 * 
 * The class serves the game. 
 * It contains all functions needed to follow the game's algorithm 
 * @author Maria Konieczka
 */

public class Controller implements ActionListener
{
	/**
	 *The view module of the programme 
	 */
	private View view;
	/**
	 *The boolean variable. It informs whether the game is paused (true) or not (false).
	 */
	private boolean pause;
	/**
	 * The boolean variable. It informs whether the direction of the snake has already been changed in each loop of the game.
	 * It is reseted at the very beginning of the loop.
	 */
	private boolean directionChange;
	/**
	 * The delay of the Timer. It is set before starting a new game depending on the selected mode.
	 */
	private int delay;
	/**
	 * The model module of the programme
	 */
	private Model model;
	/**
	 * The boolean variable. It informs whether the game has already been started and has not been lost (true).
	 */
	private boolean game;
	/**
	 * The Timer is calling a function controller's actionPerformed(ActionEvent) function after set delay.
	 * @see Controller#actionPerformed(ActionEvent)
	 */
	private Timer timer; 
	
	/**
	 * The constructor sets all the flags to start values.
	 * @param m Model : all the date
	 * @param v View : everything connected with the graphic part of the game
	 */
	public Controller(Model m, View v)
	{
		
		view=v;
		model=m;
		directionChange=true;
		pause=false;
		game=false;
		
		model.clear();
		
		view.setPanelDate(model.getXSnake(), model.getYSnake(), model.getPointApple());
		view.getFrame().addKeyListener(new KAdapter());
		view.repaint();
		
		
	}
	
	
	/**
	 * The function sets the delay of the timer.
	 * @see View#getDelay()
	 */
	public void setDelay()
	{
		delay=view.getDelay();
	}
	/**
	 * The function continues the game it resumes the timer.
	 */
	public void continueGame()
	{
		pause=false;
		timer.start();
		
	}
	/**
	 * The function stops the timer.
	 */
	public void pauseGame()
	{
		pause=true;
		timer.stop();
		
	}
	/**
	 * 
	 * @return the game status (true when the game is on)
	 */
	public boolean getGame()
	{
		return game;
	}
	
	
	
	/**
	 * the function starts a game.
	 * It is connected with Start button
	 * @see Start
	 */
	public void newGame()
	{
		setDelay();
		///System.out.println(delay);
		timer= new Timer (delay,this);
		game=true;
		///view.addKeyListener(new KAdapter());
		directionChange=true;
		view.setEnableButtons(false);
		view.requestFocus();
		///model.clear();
		timer.start();
		
	}
	
	
	/**
	 * The function is used to move the snake. It is called in every timer cycle.
	 * @see Model#moveTheSnake()
	 * @see Controller#actionPerformed(ActionEvent)
	 */
	public void move()
	{
		model.moveTheSnake();
	}
	
	/**
	 * the function checks if the head of the snake has the same coordinates as an apple.
	 * If it has the same it calls reseting the apple's point and it increases the snake.
	 * @throws Exception the board is full of snake's body parts: no place for an apple
	 */
	public void checkApple() throws Exception
	{
		if (model.getPointApple().getX()==model.getXSnake().get(0) && 
				model.getPointApple().getY()==model.getYSnake().get(0) )
		{
			model.add();
			model.setResult(view.getMode());
			view.setTextResult(String.valueOf(model.getResult()));
			try
			{
				model.setPointApple();
			}
			catch (Exception e)
			{
				throw e;
			}
			///System.out.println(model.getPointApple());
		}
	}
	
	/**
	 * The function checks whether the coordinates of snake's head are different 
	 * than the the coordinates of the rest of snake's body and whether they are on the board 
	 * (what means form 0 to BOARD_SIZE-1).
	 * @return the result of checking
	 */
	public boolean checkNoCollision()
	{
		for(int i=1; i<model.getLength(); i++){
			if((model.getXSnake().get(i).equals(model.getXSnake().get(0))) &&
					(model.getYSnake().get(i).equals(model.getYSnake().get(0)))) 
			{
				return false;
			}
		}
		
		if (model.getXSnake().get(0)<0 || model.getYSnake().get(0)<0 ||
				model.getXSnake().get(0)>(model.BOARD_SIZE-1) || model.getYSnake().get(0)>(model.BOARD_SIZE-1)	)
			return false;
		else return true;
	}
	
	
	/**
	 * This is the game's main loop. The function is called after every timer's tic.
	 * It checks collision, checks apple and then move. If it is the first tic after losing the game the function
	 * stops the game and create GameOver JDialog.
	 * @see Controller#endGame()
	 * @see GameOver
	 * @param arg0 the Timer's tic
	 */
	public void actionPerformed(ActionEvent arg0)  /// petla gry
	{
			if (game)
			{
				directionChange=true;
				game=checkNoCollision();
				try
				{
					checkApple();
				}
				catch (Exception e)
				{
					model.setResult(view.getMode());
					game=false;
					timer.stop();
					view.setEnabledPause(false);
					new GameWin(model.getResult());
					endGame();
					
				}
				if (game) 
				{
					move();
					model.setResult(view.getMode());
					view.setPanelDate(model.getXSnake(), model.getYSnake(), model.getPointApple());
					view.repaint();
				}
				
				///System.out.println("repaint");
		
			}
			else
			{
				timer.stop();
				view.setEnabledPause(false);
				new GameOver(model.getResult());
				endGame();
			}
	}
	
	/**
	 * This is a function called after losing or wining a game it reset the frames properties and clears all the date.
	 */
	public void endGame()
	{
		System.out.println("End of the game");
		view.setButtonsEnableEndGame();
		model.clear();
		model.setResult(view.getMode());
		view.setTextResult(String.valueOf(model.getResult()));
		view.setPanelDate(model.getXSnake(), model.getYSnake(), model.getPointApple());
		view.repaint();
	}
	
	
	
	/**
	 * 
	 * 
	 *This class is keyboard listener.
	 *It checks whether the arrow keys are pressed.
	 *The user may change the direction only once a timer tic's (the rest are ignored).
	 *The user can not change the direction while the game is not on .
	 *@author MARIA
	 */
	public class KAdapter extends KeyAdapter
	{
        public void keyPressed(KeyEvent e)
        {
            if (directionChange&&( !(pause))&&(game))
            {
            	int key = e.getKeyCode();
            	if (key == KeyEvent.VK_LEFT) 
            	{
            		model.setDirection(Direction.LEFT);
            		directionChange=false;
            		// System.out.println("left");
            		 //System.out.println(model.getDirection());
            	}
            	if (key == KeyEvent.VK_RIGHT) 
            	{
            		model.setDirection(Direction.RIGHT);
            		directionChange=false;
            		//System.out.println("right");
            		//System.out.println(model.getDirection());
            	}

            	if (key == KeyEvent.VK_UP) 
            	{
            		model.setDirection(Direction.UP);
            		directionChange=false;
            		//System.out.println("up");
            		//System.out.println(model.getDirection());
            	}

            	if (key == KeyEvent.VK_DOWN)
            	{
            		model.setDirection(Direction.DOWN);
            		directionChange=false;
            		//System.out.println("down");
            		//System.out.println(model.getDirection());
            	}
            }
        }
    }
	
}
