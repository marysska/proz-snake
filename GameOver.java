package konieczka.snake;


import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * 
 * 
 *This JDialog is used after losing the game.
 *(to start a new game the user has to accept the previous one's result)
 *
 *@see Controller#actionPerformed(ActionEvent)
 *@see JDialog
 *@author Maria Konieczka
 */

public class GameOver extends JDialog
{
	/**
	 * The label includes the result of the lost game.
	 */
	private JLabel result;
	/**
	 * The button used to accept the result.
	 */
	private JButton ok;
	
	/**
	 * The constructor creates the JDialog, the JLabel and Button
	 * @param res the result of the lost game
	 */
	public GameOver(int res)
	{
		
		setTitle("Game over");
		
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setSize(270, 160);
		setModal(true);
		///setLocation(new Point(310, 230));
		setResizable(false);
		
		result = new JLabel("Game over, your result: " + res);
		result.setBounds(55, 35, 200, 20);
		
		ok = new JButton("OK");
		ok.setBounds(105, 85, 50, 30);
		ok.setFocusable(false);
		ok.setMargin(new Insets(1,1,1,1));
		ok.addActionListener(new End());
		
		add(result);
		add(ok);
		setVisible(true);
		
	}
	
	/**
	 * 
	 * 
	 * the ok button listener class
	 * @author MARIA
	 *
	 */
	private class End implements ActionListener
	{
		/**
		 * 
		 * the method disposes the GameOver JDialog after the click of the button
		 * @param e the click of the button
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}
}
