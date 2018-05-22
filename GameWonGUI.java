import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameWonGUI {

	private JFrame gameWonFrame;
	private GameEnvironment gameEnvironment;

	/**
	 * Create the application.
	 */
	public GameWonGUI(GameEnvironment gameEnvironmentInput) {
		gameEnvironment = gameEnvironmentInput;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gameWonFrame = new JFrame();
		gameWonFrame.setBounds(100, 100, 704, 391);
		gameWonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWonFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Congradulations! Your Team beat all the Villains");
		lblNewLabel.setBounds(135, 142, 471, 22);
		gameWonFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblTimeTaken = new JLabel("Time taken:");
		lblTimeTaken.setBounds(245, 239, 150, 22);
		gameWonFrame.getContentPane().add(lblTimeTaken);
		
		gameEnvironment.getEndTime();
		
		JLabel timeTabel = new JLabel(gameEnvironment.getEndTime());
		timeTabel.setBounds(266, 273, 158, 15);
		gameWonFrame.getContentPane().add(timeTabel);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setBounds(266, 30, 113, 22);
		gameWonFrame.getContentPane().add(lblGameOver);
	}

	public void makeVisible() {
		this.gameWonFrame.setVisible(true);
	}

}
