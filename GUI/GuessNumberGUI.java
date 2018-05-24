import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class GuessNumberGUI {

	private JFrame guessGameFrame;
	private int villainsNumber;
	private int maxGuesses = 2;
	private int guessesLeft;
	private int guessNumber = 1;
	private BattleWindowGUI battleWindowGui;
	private Villain villain;
	private Hero heroPlaying;
	private int villainsDamage;
	private Team team;
	private CityGUI cityGui;
	private GameEnvironment gameEnvironment;
	private JLabel numGuessesLeftLabel;

	public void makeVisible() {
		this.guessGameFrame.setVisible(true);
	}
	
	public GuessNumberGUI(Hero heroPlayingInput, BattleWindowGUI battleWindowInputGui, GameEnvironment gameEnvironmentInput) {
		gameEnvironment = gameEnvironmentInput;
		battleWindowGui = battleWindowInputGui;
		heroPlaying = heroPlayingInput;
		int currentCityIndex = gameEnvironment.getCurrentCityIndex();
		cityGui = gameEnvironment.getCurrentCity();
		villain = gameEnvironment.getVillain(currentCityIndex);
		this.team = gameEnvironmentInput.getTeam();
		if (heroPlayingInput.getHasGuessNumberPowerUp()) {
			maxGuesses++;
			heroPlayingInput.setHasGuessNumberPowerUp(false);
		}
		if (heroPlayingInput.getType() == "Gambler") {
			maxGuesses++;	
		}	
		guessesLeft = maxGuesses;
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	private void initialize() {
		guessGameFrame = new JFrame();
		guessGameFrame.setTitle("Guess the Number Battle");
		guessGameFrame.setBounds(100, 100, 1200, 800);
		guessGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guessGameFrame.getContentPane().setLayout(null);

		Random random = new Random();
		villainsNumber = random.nextInt(10);
		villainsNumber++; //Due to it being 0 - 9 so we want 1 - 10
		
		JSlider guessSlider = new JSlider();
		guessSlider.setBackground(Color.WHITE);
		guessSlider.setPaintTicks(true);
		guessSlider.setPaintLabels(true);
		guessSlider.setValue(5);
		guessSlider.setMajorTickSpacing(1);
		guessSlider.setMinimum(1);
		guessSlider.setMaximum(10);
		guessSlider.setBounds(475, 350, 250, 40);
		guessGameFrame.getContentPane().add(guessSlider);
		
		JLabel pickLabel = new JLabel("Pick a number, any number!");
		pickLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		pickLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pickLabel.setBounds(450, 234, 300, 30);
		guessGameFrame.getContentPane().add(pickLabel);
		
		JLabel lblGuessesLeft = new JLabel("Guesses left:");
		lblGuessesLeft.setFont(new Font("Dialog", Font.BOLD, 17));
		lblGuessesLeft.setBounds(498, 276, 150, 30);
		guessGameFrame.getContentPane().add(lblGuessesLeft);
		
		JLabel numGuessesLeftLabel = new JLabel(Integer.toString(guessesLeft));
		numGuessesLeftLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		numGuessesLeftLabel.setBounds(669, 280, 44, 22);
		guessGameFrame.getContentPane().add(numGuessesLeftLabel);
		
		JLabel highOrLow = new JLabel("");
		highOrLow.setHorizontalAlignment(SwingConstants.CENTER);
		highOrLow.setBounds(375, 534, 450, 58);
		guessGameFrame.getContentPane().add(highOrLow);
		
		villainsDamage = villain.getDamage();
		
		JButton goBackButton = new JButton("Go back!");
		goBackButton.setVisible(false);
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleWindowGui.changeGame();
				guessGameFrame.dispose();
				gameEnvironment.openBattleWindow(team, cityGui);
			}
		});
		goBackButton.setBounds(980, 680, 180, 60);
		guessGameFrame.getContentPane().add(goBackButton);
		
		JButton btnPick = new JButton("Pick!");
		btnPick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textSet;
				
				if (guessesLeft == maxGuesses) { //First guess
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Wow you got it on the first try!");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(guessGameFrame, "The villain is now dead!");
							if (gameEnvironment.finalCity()) {
								gameEnvironment.gameWon();
								guessGameFrame.dispose();
							} else {
								battleWindowGui.villainDies();
								guessGameFrame.dispose();
							}
						}
						goBackButton.setVisible(true);
						btnPick.setVisible(false);
					} else if (guessSlider.getValue() > villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too high");
					} else if (guessSlider.getValue() < villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too low");
					}
					
					
				} else if (guessesLeft > 1) { 
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Great guess! You got it with guesses to spare");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(guessGameFrame, "The villain is now dead!");
							if (gameEnvironment.finalCity()) {
								gameEnvironment.gameWon();
								guessGameFrame.dispose();
							} else {
								battleWindowGui.villainDies();
								guessGameFrame.dispose();
							}
						}
						goBackButton.setVisible(true);
						btnPick.setVisible(false);
					}  else if (guessSlider.getValue() > villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too high");
					} else if (guessSlider.getValue() < villainsNumber) {
						highOrLow.setText("you have another guess, your guess was too low");
						
						
					}
						
				} else if (guessesLeft == 1) { // last guess
					if (guessSlider.getValue() == villainsNumber) {
						highOrLow.setText("Great guess! You got it on your last guess");
						villain.loseLife();
						if (villain.getLives() == 0) {
							JOptionPane.showMessageDialog(guessGameFrame, "The villain is now dead!");
							if (gameEnvironment.finalCity()) {
								gameEnvironment.gameWon();
								guessGameFrame.dispose();
							} else {
								battleWindowGui.villainDies();
								guessGameFrame.dispose();
							}
						}
						goBackButton.setVisible(true);
						btnPick.setVisible(false);
					} else {
						textSet = "Sorry you lose, the number was: " + villainsNumber;
						highOrLow.setText(textSet);
						goBackButton.setVisible(true);
						heroPlaying.doDamage(villainsDamage, team, battleWindowGui);
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
				guessesLeft--;
				numGuessesLeftLabel.setText(Integer.toString(guessesLeft));
			}

		});
		btnPick.setBounds(510, 450, 180, 60);
		guessGameFrame.getContentPane().add(btnPick);
		
		JLabel lblNewLabel = new JLabel("Guess the Number");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		lblNewLabel.setBounds(350, 40, 500, 100);
		guessGameFrame.getContentPane().add(lblNewLabel);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		guessGameFrame.getContentPane().add(backgroundPic);
		

	}
}
