import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		gameOverFrame.setBounds(100, 100, 1200, 800);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverFrame.getContentPane().setLayout(null);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setFont(new Font("Dialog", Font.BOLD, 51));
		lblGameOver.setBounds(400, 70, 400, 100);
		gameOverFrame.getContentPane().add(lblGameOver);
		
		JLabel lblYouLost = new JLabel("You Lost!");
		lblYouLost.setForeground(Color.RED);
		lblYouLost.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouLost.setFont(new Font("Dialog", Font.BOLD, 80));
		lblYouLost.setBounds(350, 300, 500, 100);
		gameOverFrame.getContentPane().add(lblYouLost);
		
		JLabel lblAllYourHeroes = new JLabel("All your heroes died");
		lblAllYourHeroes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllYourHeroes.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAllYourHeroes.setBounds(450, 569, 300, 15);
		gameOverFrame.getContentPane().add(lblAllYourHeroes);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameOverFrame.dispose();
			}
		});
		closeButton.setBounds(980, 680, 180, 60);
		gameOverFrame.getContentPane().add(closeButton);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		gameOverFrame.getContentPane().add(backgroundPic);
	}
}
