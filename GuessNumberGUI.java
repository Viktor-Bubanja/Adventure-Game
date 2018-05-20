import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class GuessNumberGUI {

	private JFrame frame;
	private JFrame guessGameFrame;
	private int villainsNumber;
	private int guessAvailable = 2;
	private int guessNumber = 1;
	private BattleWindow battleWindow;
	private Villain villain;
	private Hero heroPlaying;
	private int villainsDamage;
	
	
	/**
	 * Launch the application.
	 */
	public static void NewScreen(Villain villain, Hero heroPlaying, BattleWindow battleWindow) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuessNumberGUI guessNumberWindow = new GuessNumberGUI(villain, heroPlaying, battleWindow);
					if (heroPlaying.getHasGuessNumberPowerUp() || Team.teamHasGambler()) {
						guessNumberWindow.guessAvailable = 3;
						heroPlaying.setHasGuessNumberPowerUp(false);
					}
					guessNumberWindow.guessGameFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GuessNumberGUI(Villain villainInput, Hero heroPlayingInput, BattleWindow battleWindowInput) {
		battleWindow = battleWindowInput;
		heroPlaying = heroPlayingInput;
		villain = villainInput;
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Guess the Number Battle");
		guessGameFrame = new JFrame();
		guessGameFrame.setBounds(100, 100, 1000, 700);
		guessGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guessGameFrame.getContentPane().setLayout(null);
		
		
		
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
		guessGameFrame.getContentPane().add(guessSlider);
		
		JLabel pickLabel = new JLabel("Pick a number, any number!");
		pickLabel.setBounds(46, 12, 250, 30);
		guessGameFrame.getContentPane().add(pickLabel);
		
		JLabel highOrLow = new JLabel("");
		highOrLow.setBounds(12, 97, 424, 58);
		guessGameFrame.getContentPane().add(highOrLow);
		
		villainsDamage = villain.getDamage();
		
		JButton goBackButton = new JButton("Go back!");
		goBackButton.setVisible(false);
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleWindow.changeGame();
				guessGameFrame.dispose();
				
			}
		});
		goBackButton.setBounds(319, 237, 117, 25);
		guessGameFrame.getContentPane().add(goBackButton);
		
		JButton btnPick = new JButton("Pick!");
		btnPick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textSet;
				
				if (guessNumber == 1) { //First guess
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Wow you got it on the first try!");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(frame, "The villain is now dead!");
						}
						battleWindow.villainDies();
						goBackButton.setVisible(true);
						btnPick.setVisible(false);
					} else if (guessSlider.getValue() > villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too high");
						guessNumber++;
					} else if (guessSlider.getValue() < villainsNumber) {
						guessNumber++;
						highOrLow.setText("you have another guess, your guess was too low");
					}
					
					
				} else if (guessNumber < guessAvailable) { 
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Great guess! You got it");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(frame, "The villain is now dead!");
							BattleWindow.villainDies();
							guessGameFrame.dispose();
						}
						battleWindow.villainDies();
						goBackButton.setVisible(true);
						btnPick.setVisible(false);
					}  else if (guessSlider.getValue() > villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too high");
						guessNumber++;
					} else if (guessSlider.getValue() < villainsNumber) {
						guessNumber++;
						highOrLow.setText("you have another guess, your guess was too low");
						
						
					}
						
				} else if (guessNumber == guessAvailable) { // last guess
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Great guess! You got it");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(frame, "The villain is now dead!");
							BattleWindow.villainDies();
							guessGameFrame.dispose();
						}
						battleWindow.villainDies();
						goBackButton.setVisible(true);
						btnPick.setVisible(false);
					} else {
						textSet = "Sorry you lose, the number was: " + villainsNumber;
						highOrLow.setText(textSet);
						heroPlaying.doDamage(villainsDamage);
						if (heroPlaying.getHealth() <= 0) {
							JOptionPane.showMessageDialog(frame, "Your hero has died!");
						}
						goBackButton.setVisible(true);
						btnPick.setVisible(false);
					}
				}
				
					
				/*} else if (guessAvailable > guessNumber) { 
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Great guess! You got it with guesses to spare");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(frame, "The villain is now dead!");
							BattleWindow.villainDies();
							guessGameFrame.dispose();
						}
						goBackButton.setVisible(true);
						btnPick.setVisible(false);						
					} else if (guessSlider.getValue() > villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too high");
					} else if (guessSlider.getValue() < villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too low");
					}
				} */
				
				//System.out.println("Available after: " + guessAvailable);
			}

		});
		btnPick.setBounds(300, 54, 117, 25);
		guessGameFrame.getContentPane().add(btnPick);
	}
}
