import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class GameLostGUI {

	private JFrame gameOverFrame;

	/**
	 * Create the application.
	 */
	public GameLostGUI() {
		initialize();
	}
	
	public void makeVisible() {
		this.gameOverFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gameOverFrame = new JFrame();
		gameOverFrame.setBounds(100, 100, 662, 372);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverFrame.getContentPane().setLayout(null);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setFont(new Font("Dialog", Font.BOLD, 51));
		lblGameOver.setBounds(158, 30, 354, 108);
		gameOverFrame.getContentPane().add(lblGameOver);
		
		JLabel lblYouLost = new JLabel("You Lost!");
		lblYouLost.setBounds(282, 150, 70, 15);
		gameOverFrame.getContentPane().add(lblYouLost);
		
		JLabel lblAllYourHeroes = new JLabel("All your heroes died");
		lblAllYourHeroes.setBounds(255, 190, 181, 15);
		gameOverFrame.getContentPane().add(lblAllYourHeroes);
	}
}
