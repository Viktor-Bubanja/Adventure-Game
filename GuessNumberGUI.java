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

	private JFrame guessGameFrame;
	private int villainsNumber;
	private int guessAvailable = 2;
	private int guessNumber = 1;
	private BattleWindow battleWindow;
	private Villain villain;
	private Hero heroPlaying;
	private int villainsDamage;
	private Team team;
	private CityGUI cityGui;
	private GameEnvironment gameEnvironment;

	public void makeVisible() {
		this.guessGameFrame.setVisible(true);
	}
	
	public GuessNumberGUI(Hero heroPlayingInput, BattleWindow battleWindowInput, GameEnvironment gameEnvironmentInput) {
		gameEnvironment = gameEnvironmentInput;
		battleWindow = battleWindowInput;
		heroPlaying = heroPlayingInput;
		int currentCityIndex = gameEnvironment.getCurrentCityIndex();
		cityGui = gameEnvironment.getCurrentCity();
		villain = gameEnvironment.getVillain(currentCityIndex);
		this.team = gameEnvironmentInput.getTeam();
		if (heroPlayingInput.getHasGuessNumberPowerUp() || this.team.teamHasGambler()) {
			this.guessAvailable = 3;
			heroPlayingInput.setHasGuessNumberPowerUp(false);
		}	
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	private void initialize() {
		guessGameFrame = new JFrame();
		guessGameFrame.setTitle("Guess the Number Battle");
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
				gameEnvironment.openBattleWindow(team, cityGui);
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
							JOptionPane.showMessageDialog(guessGameFrame, "The villain is now dead!");
							if (gameEnvironment.finalCity()) {
								gameEnvironment.gameWon();
								guessGameFrame.dispose();
							} else {
								battleWindow.villainDies();
								guessGameFrame.dispose();
							}
						}
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
						highOrLow.setText("Great guess! You got it with guesses to spare");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(guessGameFrame, "The villain is now dead!");
							if (gameEnvironment.finalCity()) {
								gameEnvironment.gameWon();
								guessGameFrame.dispose();
							} else {
								battleWindow.villainDies();
								guessGameFrame.dispose();
							}
						}
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
						highOrLow.setText("Great guess! You got it on your last guess");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(guessGameFrame, "The villain is now dead!");
							if (gameEnvironment.finalCity()) {
								gameEnvironment.gameWon();
								guessGameFrame.dispose();
							} else {
								battleWindow.villainDies();
								guessGameFrame.dispose();
							}
						}
						goBackButton.setVisible(true);
						btnPick.setVisible(false);
					} else {
						textSet = "Sorry you lose, the number was: " + villainsNumber;
						highOrLow.setText(textSet);
						goBackButton.setVisible(true);
						heroPlaying.doDamage(villainsDamage, team, battleWindow);
						if (heroPlaying.getHealth() <= 0)
							if (gameEnvironment.herosLeft()) {
								JOptionPane.showMessageDialog(guessGameFrame, "Your hero has died!");
								guessGameFrame.dispose();
								gameEnvironment.openBattleWindow(team, cityGui);
							} else {
								guessGameFrame.dispose();
								gameEnvironment.gameLost();
							}
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
