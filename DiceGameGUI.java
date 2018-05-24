import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DiceGameGUI {
	private int villainWon = 0;
	private int heroWon = 0;
	private boolean gameOver = false;
	private JFrame diceGameFrame;
	private Villain villain;
	private Hero heroPlaying;
	private BattleWindowGUI battleWindowGui;
	private boolean heroHasPowerUp = false;
	private int rollHero;
	private Team team;
	private CityGUI cityGui;
	private GameEnvironment gameEnvironment;
	private JButton buttonRollDice = new JButton("Roll Dice!");

	
	public void makeVisible() {
		this.diceGameFrame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public DiceGameGUI(Hero heroPlayingInput, BattleWindowGUI battleWindowGuiInput, GameEnvironment gameEnvironmentInput) {
		battleWindowGui = battleWindowGuiInput;
		heroPlaying = heroPlayingInput;
		gameEnvironment = gameEnvironmentInput;
		int currentCityIndex = gameEnvironment.getCurrentCityIndex();
		cityGui = gameEnvironment.getCurrentCity();
		villain = gameEnvironment.getVillain(currentCityIndex);
		this.team = gameEnvironmentInput.getTeam();
		if (heroPlayingInput.getHasDiceGamePowerUp() || heroPlayingInput.getType() == "Gambler") {
			heroHasPowerUp = true;
			heroPlayingInput.setHasDiceGamePowerUp(false);
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		diceGameFrame = new JFrame();
		diceGameFrame.setBounds(100, 100, 1200, 800);
		diceGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		diceGameFrame.getContentPane().setLayout(null);

		int villainsDamage = villain.getDamage();
		
		JLabel labelVillainRoll = new JLabel("0");
		labelVillainRoll.setHorizontalAlignment(SwingConstants.CENTER);
		labelVillainRoll.setFont(new Font("Dialog", Font.BOLD, 20));
		labelVillainRoll.setBounds(757, 235, 30, 20);
		diceGameFrame.getContentPane().add(labelVillainRoll);
		
		JLabel winOrLoseGameLabel = new JLabel("");
		winOrLoseGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winOrLoseGameLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		winOrLoseGameLabel.setBounds(203, 311, 800, 207);
		diceGameFrame.getContentPane().add(winOrLoseGameLabel);
		
		JLabel winOrLoseLabel = new JLabel("");
		winOrLoseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winOrLoseLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		winOrLoseLabel.setBounds(450, 311, 300, 20);
		diceGameFrame.getContentPane().add(winOrLoseLabel);
		
		JLabel heroWonLabel = new JLabel("0");
		heroWonLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		heroWonLabel.setBounds(487, 620, 70, 15);
		diceGameFrame.getContentPane().add(heroWonLabel);
		
		JLabel villainWonLabel = new JLabel("0");
		villainWonLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		villainWonLabel.setBounds(809, 620, 70, 15);
		diceGameFrame.getContentPane().add(villainWonLabel);
		
		JButton goBackButton = new JButton("Go Back!");
		goBackButton.setVisible(false);
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleWindowGui.changeGame();
				diceGameFrame.dispose();
				gameEnvironment.openBattleWindow(team, cityGui);
			}
		});
		goBackButton.setBounds(980, 680, 180, 60);
		diceGameFrame.getContentPane().add(goBackButton);
		
		JLabel labelHeroRoll = new JLabel("0");
		labelHeroRoll.setHorizontalAlignment(SwingConstants.CENTER);
		labelHeroRoll.setFont(new Font("Dialog", Font.BOLD, 20));
		labelHeroRoll.setBounds(350, 235, 30, 20);
		diceGameFrame.getContentPane().add(labelHeroRoll);
		
		buttonRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!gameOver) {
					Random randomHeroNumber = new Random();
					Random randomVillainNumber = new Random();
					if (heroHasPowerUp) {
						rollHero = randomHeroNumber.nextInt(4) + 3;
					} else {
						rollHero = randomHeroNumber.nextInt(6) + 1;
					}
					int rollVillain = randomVillainNumber.nextInt(6) + 1;
					labelHeroRoll.setText(Integer.toString(rollHero));
					labelVillainRoll.setText(Integer.toString(rollVillain));
					if (rollHero > rollVillain) {
						winOrLoseLabel.setText("You win this round");
						heroWon++;
						if (heroWon == 3) {
							winOrLoseGameLabel.setText("You Won!");
							gameOver = true;
							buttonRollDice.setVisible(false);
							goBackButton.setVisible(true);
							villain.loseLife();
							if (villain.getLives() == 0) {
								JOptionPane.showMessageDialog(diceGameFrame, "The villain is now dead!");
								if (gameEnvironment.finalCity()) {
									gameEnvironment.gameWon();
									diceGameFrame.dispose();
								} else {
									battleWindowGui.villainDies();
									diceGameFrame.dispose();
								}
							}
						}
						heroWonLabel.setText(Integer.toString(heroWon));
					} else if (rollVillain > rollHero) {
						winOrLoseLabel.setText("You lose this round");
						villainWon++;
						if (villainWon == 3) {
							gameOver = true;
							buttonRollDice.setVisible(false);
							goBackButton.setVisible(true);
							winOrLoseGameLabel.setText("You Lost!");
							
							heroPlaying.doDamage(villainsDamage, team, battleWindowGui);
							if (heroPlaying.getHealth() <= 0) {
								if (gameEnvironment.herosLeft()) {
									JOptionPane.showMessageDialog(diceGameFrame, "Your hero has died!");
									diceGameFrame.dispose();
									gameEnvironment.openBattleWindow(team, cityGui);
								} else {
									diceGameFrame.dispose();
									gameEnvironment.gameLost();
								}
							}
						}
						villainWonLabel.setText(Integer.toString(villainWon));
					} else {
						winOrLoseLabel.setText("Draw");
					}
					
				}
			}
		});
		buttonRollDice.setBounds(510, 400, 180, 60);
		diceGameFrame.getContentPane().add(buttonRollDice);
		
		JLabel lblYouGot = new JLabel("You rolled:");
		lblYouGot.setFont(new Font("Dialog", Font.BOLD, 20));
		lblYouGot.setBounds(300, 190, 150, 30);
		diceGameFrame.getContentPane().add(lblYouGot);
		
		JLabel lblVillainGot = new JLabel("Villain rolled:");
		lblVillainGot.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVillainGot.setBounds(705, 190, 170, 30);
		diceGameFrame.getContentPane().add(lblVillainGot);
		
		JLabel scoreLabel = new JLabel("Score");
		scoreLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		scoreLabel.setBounds(579, 580, 70, 15);
		diceGameFrame.getContentPane().add(scoreLabel);
		
		JLabel tallyHeroLabel = new JLabel("Hero:");
		tallyHeroLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		tallyHeroLabel.setBounds(405, 620, 70, 15);
		diceGameFrame.getContentPane().add(tallyHeroLabel);
		
		JLabel tallyVillainLabel = new JLabel("Villain:");
		tallyVillainLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		tallyVillainLabel.setBounds(717, 620, 70, 15);
		diceGameFrame.getContentPane().add(tallyVillainLabel);
		
		JLabel lblNewLabel = new JLabel("Dice Game");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(450, 40, 300, 100);
		diceGameFrame.getContentPane().add(lblNewLabel);
		

		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		diceGameFrame.getContentPane().add(backgroundPic);
	}
}
