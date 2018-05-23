import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		gameWonFrame.setBounds(100, 100, 1200, 800);
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
		
		JButton closeAllButton = new JButton("I'm amazing, close all");
		closeAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//gameEnvironment.closeAll();
			}
		});
		closeAllButton.setBounds(452, 239, 216, 85);
		gameWonFrame.getContentPane().add(closeAllButton);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		gameWonFrame.getContentPane().add(backgroundPic);
	}

	public void makeVisible() {
		this.gameWonFrame.setVisible(true);
	}
}
