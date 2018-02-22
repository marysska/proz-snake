package konieczka.snake;
/**
 * 
 *
 * The snake game.
 *  @author Maria Konieczka
 */
public class ProzSnake {

	public static void main(String[] args) 
	{
		try {
			
			
			javax.swing.SwingUtilities.invokeLater(
					new Runnable()
					{
						public void run() 
						{
							Model model = new Model();
							View view = new View();
							Controller controller = new Controller(model, view); 
							view.addController(controller);
						}
					} );
			
			
			
			
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}

	}

}
