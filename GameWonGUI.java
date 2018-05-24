import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class GameWonGUI {
	/**
	 * Attributes:
	 * The frame for the DiceGame GUI to display
	 * The game Environment handling the game
	 */
	private JFrame gameWonFrame;
	private GameEnvironment gameEnvironment;

	/**
	 * @param gameEnvironmentInput GameEnvironment
	 */
	public GameWonGUI(GameEnvironment gameEnvironmentInput) {
		gameEnvironment = gameEnvironmentInput;
		initialize();
	}
	/**
	 * Makes the game won frame visible
	 */
	public void makeVisible() {
		this.gameWonFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gameWonFrame = new JFrame();
		gameWonFrame.setBounds(100, 100, 1200, 800);
		gameWonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWonFrame.getContentPane().setLayout(null);
		
		JLabel congratulationsLabel = new JLabel("Congradulations! Your Team, " + gameEnvironment.getTeamName() + " beat all the Villains");
		congratulationsLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		congratulationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		congratulationsLabel.setBounds(175, 422, 850, 22);
		gameWonFrame.getContentPane().add(congratulationsLabel);
		
		JLabel timeTakenLabel = new JLabel("Time taken:");
		timeTakenLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		timeTakenLabel.setBounds(432, 554, 150, 25);
		gameWonFrame.getContentPane().add(timeTakenLabel);
		
		gameEnvironment.getEndTime();
		
		JLabel timeLabel = new JLabel(gameEnvironment.getEndTime()); //stops the timer and displays it
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		timeLabel.setBounds(580, 554, 203, 25);
		gameWonFrame.getContentPane().add(timeLabel);
		
		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setFont(new Font("Dialog", Font.BOLD, 51));
		gameOverLabel.setBounds(400, 70, 400, 100);
		gameWonFrame.getContentPane().add(gameOverLabel);
		
		JButton closeAllButton = new JButton("Done!");
		closeAllButton.addActionListener(new ActionListener() { //Closes this frame
			public void actionPerformed(ActionEvent e) {
				gameWonFrame.dispose();
			}
		});
		closeAllButton.setBounds(980, 680, 180, 60);
		gameWonFrame.getContentPane().add(closeAllButton);
		
		JLabel youWinLabel = new JLabel("You Win!");
		youWinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		youWinLabel.setFont(new Font("Dialog", Font.BOLD, 80));
		youWinLabel.setBounds(350, 300, 500, 100);
		gameWonFrame.getContentPane().add(youWinLabel);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg"))); // background picture of the scroll
		backgroundPic.setBounds(0, 0, 1200, 800);
		gameWonFrame.getContentPane().add(backgroundPic);
	}
}
