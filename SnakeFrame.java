package konieczka.snake;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


/**
 * 
 * 
 * This is the main frame of the game.
 * It contains buttons and SnakePanel
 * @see JFrame
 * @author Maria Konieczka
 */
public class SnakeFrame extends JFrame
{

	/**
	 * The SnkePanel represents the board.
	 */
	public SnakePanel snakePanel;
	/**
	 * The button used to start or continue a game.
	 */
	public JButton start; //
	/**
	 * The button used to pause the started game.
	 */
	public JButton pause;//
	/**
	 * The string printed on the frame ("Result: ")
	 */
	private JLabel resultString;
	/**
	 * The label includes the current result of the game.
	 */
	public JLabel result;
	/**
	 * The group of Radio Buttons of the game's mode.
	 */
	private ButtonGroup group;
	/**
	 * The selected button informs of the mode of the game.
	 */
	private JRadioButton easy, medium, hard;
	/**
	 * The view module of the programme.
	 */
	private View view;
	/**
	 * The game's logo.
	 */
	private JLabel icon;
	
	
	
	/**
	 * The constructor sets all frame's parameters.
	 * It creates and adds buttons and pannel 
	 * @param v the view 
	 */
	public SnakeFrame(View v)
	{
		
		super("Snake");
		
		view=v;
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		setSize(525, 415);
		setLayout(null);
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		snakePanel= new SnakePanel();
		snakePanel.setBounds(0, 0, 375, 375);
		add(snakePanel);

		Insets insets = new Insets(1,1,1,1);
		
		start = new JButton("Start");
		start.setMargin(insets);
		start.setFocusable(false);
		start.setBounds(390, 25, 100, 25);
		start.addActionListener(new Start(view));
		add(start);
		
		pause = new JButton("Pause");
		pause.setMargin(insets);
		pause.setFocusable(false);
		pause.setBounds(390, 60, 100, 25);
		pause.addActionListener(new Pause(view));
		pause.setEnabled(false);
		add(pause); 
		
		resultString = new JLabel("Result:");
		resultString.setBounds(390, 95, 60, 25);
		resultString.setFont(new Font("Helvetica", Font.BOLD, 16));
		 
		result = new JLabel("0");
		result.setBounds(450,95, 60, 25);
		result.setFont(new Font("Helvetica", Font.BOLD, 16));
		add(result);
		add(resultString);
		
		group=new ButtonGroup();
		easy= new JRadioButton("Easy", false);
		easy.setBounds(390, 130, 100,25);
		medium= new JRadioButton ("Medium", true);
		medium.setBounds(390, 155, 100, 25);
		hard= new JRadioButton ("Hard", false);
		hard.setBounds(390,180, 100,25);
		add(easy);
		add(medium);
		add(hard);
		group.add(easy);
		group.add(medium);
		group.add(hard);
		medium.setVisible(true);
		easy.setVisible(true);
		hard.setVisible(true);
		setEnableButtons(true);
		
		try
		{
			icon= new JLabel(new ImageIcon("waz3.png"));
			icon.setBounds(370, 230,150, 150);
			add(icon);
		}
		catch (Exception e)
		{
			System.out.println("Error, cannot find/open the icon");
		}
		
	}
	
	/**
	 * the function changes the possibility to choose the mode of the game
	 * @param b the boolean parameter it chooses whether to enable or disable the possibility 
	 */
	public void setEnableButtons( boolean b)
	{
		easy.setEnabled(b);
		medium.setEnabled(b);
		hard.setEnabled(b);
	}
	
	/**
	 * The function changes the ability to click Start and Pause buttons when the game is to be started.
	 * @see Start
	 */
	public void changeButtonsStartGame()
	{
		start.setEnabled(false);
		pause.setEnabled(true);
	}
	
	/**
	 * The function changes the ability to click Start and Pause buttons when the game is to be paused.
	 * @see Pause
	 */
	public void changeButtonsPauseGame()
	{
		
		start.setEnabled(true);
		pause.setEnabled(false);
	}
	/**
	 * The function changes the ability to click Start and Pause buttons when the game is to be continued.
	 * @see Start
	 */
	public void changeButtonsContinueGame()
	{
		start.setEnabled(false);
		pause.setEnabled(true);
	}
	/**
	 * The function checks which mode button is selected.
	 * @return the selected mode of the game
	 */
	public Mode getMode()
	{
		if (easy.isSelected())
			return Mode.EASY;
		if( medium.isSelected())
			return Mode.MEDIUM;
		else
			return Mode.HARD;
	}
	
	
	
	
}
