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
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(300, 422, 600, 22);
		gameWonFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblTimeTaken = new JLabel("Time taken:");
		lblTimeTaken.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTimeTaken.setBounds(432, 554, 150, 25);
		gameWonFrame.getContentPane().add(lblTimeTaken);
		
		gameEnvironment.getEndTime();
		
		JLabel timeTabel = new JLabel(gameEnvironment.getEndTime());
		timeTabel.setFont(new Font("Dialog", Font.BOLD, 20));
		timeTabel.setBounds(630, 554, 158, 25);
		gameWonFrame.getContentPane().add(timeTabel);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setFont(new Font("Dialog", Font.BOLD, 51));
		lblGameOver.setBounds(400, 70, 400, 100);
		gameWonFrame.getContentPane().add(lblGameOver);
		
		JButton closeAllButton = new JButton("Close");
		closeAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWonFrame.dispose();
			}
		});
		closeAllButton.setBounds(980, 680, 180, 60);
		gameWonFrame.getContentPane().add(closeAllButton);
		
		JLabel lblNewLabel_1 = new JLabel("You Win!");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 80));
		lblNewLabel_1.setBounds(350, 300, 500, 100);
		gameWonFrame.getContentPane().add(lblNewLabel_1);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		gameWonFrame.getContentPane().add(backgroundPic);
	}

	public void makeVisible() {
		this.gameWonFrame.setVisible(true);
	}
}
