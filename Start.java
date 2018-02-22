package konieczka.snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * 
 * This is a start button's listener class
 * @see SnakeFrame
 * @author Maria Konieczka
 *
 */
public class Start implements ActionListener 
{
	/**
	 * The view module of the programme.
	 */
	View v;
	/**
	 * 
	 * The constructor 
	 * @param view the View module of the programme 
	 */
	public Start(View view)
	{
		v=view;
	}
	
	/**
	 * 
	 * The method calls controller's function to create a new game or continue the started one
	 * The method also calls SnakeFrame's functions to change the Start and Pause buttons ability 
	 * @param e  the click of the button
	 * @see Controller#continueGame()
	 * @see Controller#newGame()
	 * @see SnakeFrame#changeButtonsStartGame()
	 * @see SnakeFrame#changeButtonsContinueGame()
	 * 
	 */
		public void actionPerformed(ActionEvent e) 
		{
			if (v.getController().getGame()==false)
			{
				v.getFrame().changeButtonsStartGame();
				v.getController().newGame();
			}
			else
			{
				v.getFrame().changeButtonsContinueGame();
				v.getController().continueGame();
			}
		}



}
