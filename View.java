package konieczka.snake;

import java.awt.Point;
import java.util.ArrayList;

import konieczka.snake.Controller.KAdapter;

/**
 * 
 * 
 *the class coordinates the games graphic part
 *it contains the mode (and delays) 
 *@author Maria Konieczka
 */

public class View 
{
	/**
	 * The controller part of the programme.
	 */
	private Controller controller;
	/**
	 * The game's frame.
	 */
	private SnakeFrame snakeFrame;
	/**
	 * The time of an easy game delay. 
	 */
	final int DELAY_EASY=125;
	/**
	 * The time of a medium game delay. 
	 */
	final int DELAY_MEDIUM=100;
	/**
	 * The time of a hard game delay. 
	 */
	final int DELAY_HARD=75;
	/** 
	 * The mode of current game.
	 */
	private Mode m;
	
	
	/**
	 * The constructor creates new SnakeFrame 
	 * @see SnakeFrame#SnakeFrame(View)
	 */
	public View()
	{ 
		snakeFrame=new SnakeFrame(this);
	}
	
	/**
	 * 
	 * @param c the controller module 
	 */
	public void addController(Controller c)
	{
		controller=c;
	}
	
	
	/**
	 * 
	 * @return the game's main frame
	 */
	public SnakeFrame getFrame()
	{
		return snakeFrame;
	}
	/**
	 * 
	 * @return the controller 
	 */
	public Controller getController()
	{
		return controller;
	}
	/**
	 * the function checks the mode and return proper time of delay
	 * @return the time of delay
	 * @see SnakeFrame#getMode()
	 */
	public int getDelay()
	{
		setMode();
		switch (m) 
		{
			case EASY:
				return DELAY_EASY;
			case MEDIUM:
				return DELAY_MEDIUM;
			case HARD: 
				return DELAY_HARD;
			default:
				return 0;
		}
	}
	/**
	 * The function set the mode depending on the selected button
	 * @see SnakeFrame#getMode()
	 */
	public void setMode()
	{
		m=snakeFrame.getMode();
	}
	/**
	 * 
	 * @return selected mode of the game
	 */
	
	public Mode getMode()
	{
		setMode();
		return m;
	}
	
	public void setPanelDate( ArrayList<Integer> xTemp, ArrayList<Integer> yTemp, Point pTemp)
	{
		snakeFrame.snakePanel.setTheDate( xTemp,  yTemp,  pTemp);
	}
	
	public void repaint()
	{
		snakeFrame.snakePanel.repaint();
	}
	
	public void setEnableButtons(boolean b)
	{
		snakeFrame.setEnableButtons(b);
	}
	
	public void requestFocus()
	{
		snakeFrame.requestFocus();
	}
	
	public void setTextResult(String s)
	{
		snakeFrame.result.setText(s);
	}

	public void setEnabledPause(boolean b)
	{
		snakeFrame.pause.setEnabled(b);
	}
	
	public void setButtonsEnableEndGame()
	{
		snakeFrame.setEnableButtons(true);
		snakeFrame.start.setEnabled(true);
		snakeFrame.pause.setEnabled(false);
	}
}
