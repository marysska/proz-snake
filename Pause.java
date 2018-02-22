package konieczka.snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 *
 *The Pause button's listener class 
 *@see SnakeFrame
 * @author Maria Konieczka
 *
 */
public class Pause implements ActionListener 
{

	/**
	 * The view module of the game.
	 */
	private View view;
	/**
	 * The constructor 
	 * @param v the View module of the proramme 
	 */
	public Pause(View v)
	{
		view=v;
	}
	/**
	 * 
	 * The method calls controller's method which stops the timer 
	 * if also calls the SnakeFrame's method to change buttons' ability
	 * @param e the click of the Pause button
	 * @see Controller#pauseGame()
	 * @see SnakeFrame#changeButtonsPauseGame()
	 * 
	 */
	public void actionPerformed(ActionEvent e) 
	{
		view.getController().pauseGame();
		view.getFrame().changeButtonsPauseGame();
	}

}
