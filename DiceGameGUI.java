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
	/**
	 * Attributes:
	 * The frame for the DiceGame GUI to display
	 * The game Environment handling the game
	 * The villain you are currently fighting
	 * The hero you select to play with
	 * The current city you are fighting in.
	 * Your team
	 * The battleWindowGUI handling the fight
	 * keeping track of the number of times villain has won. The game is first to 3
	 * keeping track of the number of times your Hero has won. The game is first to 3
	 * Your roll
	 * boolean indicating if the game is over
	 * boolean that asks if you have the Golden Die PowerUp
	 * Button that rolls you and the villains Dice
	 */
	private JFrame diceGameFrame;
	private GameEnvironment gameEnvironment;
	private Villain villain;
	private Hero heroPlaying;
	private CityGUI cityGui;
	private Team team;
	private BattleWindowGUI battleWindowGui;
	private int villainWon = 0;
	private int heroWon = 0;
	private int rollHero;
	private boolean gameOver = false;
	private boolean heroHasPowerUp = false;
	private JButton rollDiceButton;

	/**
	 * Sets the dice game to visible
	 */
	public void makeVisible() {
		this.diceGameFrame.setVisible(true);
	}
	/**
	 * @param heroPlayingInput Hero
	 * @param battleWindowGuiInput BattleWindowGUI
	 * @param gameEnvironmentInput GameEnvironment
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
		
		JLabel villainRollLabel = new JLabel("0");
		villainRollLabel.setHorizontalAlignment(SwingConstants.CENTER); //Display the villains roll
		villainRollLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		villainRollLabel.setBounds(757, 235, 30, 20);
		diceGameFrame.getContentPane().add(villainRollLabel);
		
		JLabel winOrLoseGameLabel = new JLabel("");
		winOrLoseGameLabel.setHorizontalAlignment(SwingConstants.CENTER); //Display whether you've won or lost the game
		winOrLoseGameLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		winOrLoseGameLabel.setBounds(203, 311, 800, 207);
		diceGameFrame.getContentPane().add(winOrLoseGameLabel);
		
		JLabel winOrLoseLabel = new JLabel("");
		winOrLoseLabel.setHorizontalAlignment(SwingConstants.CENTER); //Display whether you've won or lost the round
		winOrLoseLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		winOrLoseLabel.setBounds(450, 311, 300, 20);
		diceGameFrame.getContentPane().add(winOrLoseLabel);
		
		JLabel heroWonLabel = new JLabel("0");
		heroWonLabel.setFont(new Font("Dialog", Font.BOLD, 17)); //Display how many games the hero has won
		heroWonLabel.setBounds(487, 620, 70, 15);
		diceGameFrame.getContentPane().add(heroWonLabel);
		
		JLabel villainWonLabel = new JLabel("0");
		villainWonLabel.setFont(new Font("Dialog", Font.BOLD, 17)); //Display how many games the Villain has won
		villainWonLabel.setBounds(809, 620, 70, 15);
		diceGameFrame.getContentPane().add(villainWonLabel);
		
		JButton goBackButton = new JButton("Go Back!"); //Button that sends you back to the battle window after a game
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
		
		JLabel heroRollLabel = new JLabel("0");
		heroRollLabel.setHorizontalAlignment(SwingConstants.CENTER);  //Displays your roll
		heroRollLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		heroRollLabel.setBounds(350, 235, 30, 20);
		diceGameFrame.getContentPane().add(heroRollLabel);
		
		rollDiceButton = new JButton("Roll Dice!");
		rollDiceButton.addActionListener(new ActionListener() { //Roll the dice
			public void actionPerformed(ActionEvent e) {
				if (!gameOver) {
					Random randomHeroNumber = new Random();
					Random randomVillainNumber = new Random();
					if (heroHasPowerUp) {
						rollHero = randomHeroNumber.nextInt(4) + 3; //Cant roll less than a 3 due to power up
					} else {
						rollHero = randomHeroNumber.nextInt(6) + 1;
					}
					int rollVillain = randomVillainNumber.nextInt(6) + 1;
					heroRollLabel.setText(Integer.toString(rollHero));
					villainRollLabel.setText(Integer.toString(rollVillain));
					if (rollHero > rollVillain) {
						winOrLoseLabel.setText("You win this round");
						heroWon++;
						if (heroWon == 3) {
							winOrLoseGameLabel.setText("You Won!");
							gameOver = true;
							rollDiceButton.setVisible(false);
							goBackButton.setVisible(true);
							villain.loseLife();
							if (villain.getLives() == 0) { //Villain dies
								JOptionPane.showMessageDialog(diceGameFrame, "The villain is now dead!");
								if (gameEnvironment.finalCity()) { //In last city so game now over
									gameEnvironment.gameWon();
									diceGameFrame.dispose();
								} else { 
									battleWindowGui.villainDies(); // next city
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
							rollDiceButton.setVisible(false);
							goBackButton.setVisible(true);
							winOrLoseGameLabel.setText("You Lost!");
							
							heroPlaying.doDamage(villainsDamage, team, battleWindowGui);
							if (heroPlaying.getHealth() <= 0) {
								if (gameEnvironment.herosLeft()) {//no heroes left so game now over
									JOptionPane.showMessageDialog(diceGameFrame, "Your hero has died!");
									diceGameFrame.dispose();
									gameEnvironment.openBattleWindow(team, cityGui);
								} else {
									diceGameFrame.dispose();//have to fight with next hero
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
		rollDiceButton.setBounds(510, 400, 180, 60);
		diceGameFrame.getContentPane().add(rollDiceButton);
		
		JLabel youGotLabel = new JLabel("You rolled:"); 
		youGotLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		youGotLabel.setBounds(300, 190, 150, 30);
		diceGameFrame.getContentPane().add(youGotLabel);
		
		JLabel villainGotLabel = new JLabel("Villain rolled:");
		villainGotLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		villainGotLabel.setBounds(705, 190, 170, 30);
		diceGameFrame.getContentPane().add(villainGotLabel);
		
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
		
		JLabel diceGameLabel = new JLabel("Dice Game");
		diceGameLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		diceGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		diceGameLabel.setBounds(450, 40, 300, 100);
		diceGameFrame.getContentPane().add(diceGameLabel);

		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));// background picture of the scroll
		backgroundPic.setBounds(0, 0, 1200, 800);
		diceGameFrame.getContentPane().add(backgroundPic);
	}
}
