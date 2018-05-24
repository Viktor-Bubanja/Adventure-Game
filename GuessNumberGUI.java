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

	/**
	 * Attributes:
	 * The frame for the guess Game GUI to display
	 * The game Environment handling the game
	 * The villain you are currently fighting
	 * The hero you select to play with
	 * The current city you are fighting in.
	 * Your team
	 * The battleWindowGUI handling the fight
	 * The number you have to try to pick random number between 1 and 10
	 * your max amount of guesses, affected by the power up extra guess and the gambler
	 * the amount of damage the villain will do to you if you lose
	 */
	private JFrame guessGameFrame;
	private GameEnvironment gameEnvironment;
	private Villain villain;
	private Hero heroPlaying;
	private CityGUI cityGui;
	private Team team;
	private BattleWindowGUI battleWindowGui;
	private int villainsNumber;
	private int maxGuesses = 2;
	private int guessesLeft;
	private int villainsDamage;

	/**
	 * makes the guess game GUI visible
	 */
	public void makeVisible() {
		this.guessGameFrame.setVisible(true);
	}
	/**
	 * Adds guesses to guesses left if you have the power up or the gambler is playing
	 * @param heroPlayingInput Hero
	 * @param battleWindowInputGui BattleWindowGUI
	 * @param gameEnvironmentInput GameEnvironment
	 */
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
		villainsNumber = random.nextInt(10); //Villains number we gotta guess
		villainsNumber++; //Due to it being 0 - 9 so we want 1 - 10
		
		JSlider guessSlider = new JSlider();
		guessSlider.setOpaque(false);
		guessSlider.setBackground(Color.WHITE); //The way we guess
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
		
		JLabel guessesLeftLabel = new JLabel("Guesses left:");
		guessesLeftLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		guessesLeftLabel.setBounds(498, 276, 150, 30);
		guessGameFrame.getContentPane().add(guessesLeftLabel);
		
		JLabel numGuessesLeftLabel = new JLabel(Integer.toString(guessesLeft)); //Displays how many guess we have left
		numGuessesLeftLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		numGuessesLeftLabel.setBounds(669, 280, 44, 22);
		guessGameFrame.getContentPane().add(numGuessesLeftLabel);
		
		JLabel highOrLowLabel = new JLabel("");
		highOrLowLabel.setHorizontalAlignment(SwingConstants.CENTER);  //Displays whether our guess was too high or too low or we got the correct answer 
		highOrLowLabel.setBounds(375, 534, 450, 58);
		guessGameFrame.getContentPane().add(highOrLowLabel);
		
		villainsDamage = villain.getDamage();
		
		JButton goBackButton = new JButton("Go back!"); //Once game is over we can go back
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
		
		JButton pickButton = new JButton("Pick!");
		pickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String textSet;
				
				if (guessesLeft == maxGuesses) { //First guess
					if (guessSlider.getValue() == villainsNumber) {
						highOrLowLabel.setText("Wow you got it on the first try!");
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
						pickButton.setVisible(false);
					} else if (guessSlider.getValue() > villainsNumber) {
						highOrLowLabel.setText("you have another guess, your guess was too high");
					} else if (guessSlider.getValue() < villainsNumber) {
						highOrLowLabel.setText("you have another guess, your guess was too low");
					}
					
					
				} else if (guessesLeft > 1) { //future guesses but no the last one
					if (guessSlider.getValue() == villainsNumber) {
						highOrLowLabel.setText("Great guess! You got it with guesses to spare");
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
						pickButton.setVisible(false);
					}  else if (guessSlider.getValue() > villainsNumber) {
						highOrLowLabel.setText("you have another guess, your guess was too high");
					} else if (guessSlider.getValue() < villainsNumber) {
						highOrLowLabel.setText("you have another guess, your guess was too low");
						
						
					}
						
				} else if (guessesLeft == 1) { // last guess
					if (guessSlider.getValue() == villainsNumber) {
						highOrLowLabel.setText("Great guess! You got it on your last guess");
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
						pickButton.setVisible(false);
					} else {
						textSet = "Sorry you lose, the number was: " + villainsNumber;
						highOrLowLabel.setText(textSet);
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
						pickButton.setVisible(false);
					}
				}
				guessesLeft--;
				numGuessesLeftLabel.setText(Integer.toString(guessesLeft));
			}
		});
		pickButton.setBounds(510, 450, 180, 60);
		guessGameFrame.getContentPane().add(pickButton);
		
		JLabel guessNumberLabel = new JLabel("Guess the Number");
		guessNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		guessNumberLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		guessNumberLabel.setBounds(350, 40, 500, 100);
		guessGameFrame.getContentPane().add(guessNumberLabel);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg"))); // background picture of the scroll
		backgroundPic.setBounds(0, 0, 1200, 800);
		guessGameFrame.getContentPane().add(backgroundPic);
		

	}
}
