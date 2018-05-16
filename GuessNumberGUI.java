import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class GuessNumberGUI extends JFrame {

	private JPanel contentPane;
	private int villainsNumber;
	private int guessAvailable = 2;
	private int guessNumber = 1;
	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuessNumberGUI frame = new GuessNumberGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuessNumberGUI() {
		setTitle("Guess the Number Battle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Random random = new Random();
		villainsNumber = random.nextInt(10);
		villainsNumber++; //Due to it being 0 - 9 so we want 1 - 10
		
		JSlider guessSlider = new JSlider();
		guessSlider.setPaintTicks(true);
		guessSlider.setPaintLabels(true);
		guessSlider.setValue(5);
		guessSlider.setMajorTickSpacing(1);
		guessSlider.setMinimum(1);
		guessSlider.setMaximum(10);
		guessSlider.setBounds(32, 54, 245, 41);
		contentPane.add(guessSlider);
		
		JLabel pickLabel = new JLabel("Pick a number, any number!");
		pickLabel.setBounds(46, 12, 250, 30);
		contentPane.add(pickLabel);
		
		JLabel highOrLow = new JLabel("");
		highOrLow.setBounds(12, 97, 424, 58);
		contentPane.add(highOrLow);
		
		JButton btnPick = new JButton("Pick!");
		btnPick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textSet;
				System.out.println("Number before: " + guessNumber);
				//System.out.println("Available before: " + guessAvailable);
				if (guessNumber == 1) { //First guess
				
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Wow you got it on the first try!");
						btnPick.setVisible(false);
					} else if (guessSlider.getValue() > villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too high");
					} else if (guessSlider.getValue() < villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too low");
					}
					
					
				} else if (guessNumber == guessAvailable) { //If this is your last guess
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Great guess! You got it");
						btnPick.setVisible(false);
					} else {
						textSet = "Sorry you lose, the number was: " + villainsNumber;
						highOrLow.setText(textSet);
						btnPick.setVisible(false);
					}
					
				} else if (guessAvailable > guessNumber) { 
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Great guess! You got it with guesses to spare");
						btnPick.setVisible(false);
					} else if (guessSlider.getValue() > villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too high");
					} else if (guessSlider.getValue() < villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too low");
					}
				}
				guessNumber = guessNumber + 1;
				System.out.println("Number after: " + guessNumber);
				//System.out.println("Available after: " + guessAvailable);
			}
			
		});
		btnPick.setBounds(300, 54, 117, 25);
		contentPane.add(btnPick);
	}
}
