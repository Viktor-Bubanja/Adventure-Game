import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;


public class PaperScissorsRockGUI {
	/**
	 * Attributes:
	 * JFrame for game.
	 * Random number for generating the Villains move.
	 * Number of times the villain has won.
	 * Number of times the hero has won.
	 * The heroes move as a HandSign (either PAPER, SCISSORS, or ROCK),
	 * The villains move as a HandSign.
	 * Boolean indicating if the game is over.
	 * Amount of damage the villain deals if he wins.
	 * Villain that the hero is facing.
	 * The hero playing the game.
	 * The city the team is currently in.
	 * The battle window handling the fight.
	 * Boolean indicating whether the hero fighting has the Paper Scissors Rock Clue power up.
	 * The team.
	 * The game environment handling the game.
	 * Buttons to pick either paper, scissors or rock, and to go to back to the city after finishing the game.
	 * Clue Label which is visible only if the hero has the Paper Scissors Rock Clue power up.
	 * Labels displaying the game title, the round result, the villains move, and the game result.
	 */
	private JFrame paperScissorsRockFrame;
	private Random random = new Random();
	private int numVillainWon = 0;
	private int numHeroWon = 0;
	private HandSign herosMove;
	private HandSign villainsMove;
	private boolean gameOver = false;
	private int villainsDamage;
	private Villain villain;
	private Hero heroPlaying;
	private CityGUI cityGui;
	private BattleWindowGUI battleWindowGui;
	private boolean heroHasPowerUp = false;
	private Team team;
	private GameEnvironment gameEnvironment;
	private JButton scissorsButton;
	private JButton goBackButton;
	private JButton paperButton;
	private JButton rockButton;
	private JLabel clueLabel;
	private JLabel paperScissorsRockTitleLabel;
	private JLabel winOrLoseRoundLabel;
	private JLabel villainChoseLabel;
	private JLabel villainsMoveLabel;
	private JLabel winOrLoseGameLabel;


	/**
	 * Makes the window visible when first initialized.
	 */
	public void makeVisible() {
		this.paperScissorsRockFrame.setVisible(true);
	}
	/**
	 * Calls initialize()
	 * @param heroPlayingInput Hero 
	 * @param battleWindowInputGui BattleWindowGUI 
	 * @param gameEnvironmentInput GamEnvironment 
	 */
	public PaperScissorsRockGUI(Hero heroPlayingInput, BattleWindowGUI battleWindowInputGui, GameEnvironment gameEnvironmentInput) {
		gameEnvironment = gameEnvironmentInput;
		battleWindowGui = battleWindowInputGui;
		heroPlaying = heroPlayingInput;
		int currentCityIndex = gameEnvironment.getCurrentCityIndex();
		cityGui = gameEnvironment.getCurrentCity();
		villain = gameEnvironment.getVillain(currentCityIndex); //gets the Villain corresponding to the current city.
		team = gameEnvironment.getTeam();
		
		villainsDamage = villain.getDamage();
		if (heroPlaying.getHasPaperScissorsRockPowerUp()) {
			heroHasPowerUp = true;
			heroPlaying.setHasPaperScissorsRockPowerUp(false); //consumes power up.
		}
		initialize();
	}
	/**
	 * Returns the result of the round.
	 * @param herosMove HandSign 
	 * @param villainsMove HandSign 
	 * @return int 0 = you lost the round, 1 means you won the round, 2 means a tie
	 */
	private int returnRoundResult(HandSign herosMove, HandSign villainsMove) {
		int loss = 0;
		int win = 1;
		int tie = 2;

        if (villainsMove == herosMove) {
            return tie;
        } else if (villainsMove == HandSign.SCISSORS && herosMove == HandSign.PAPER) {
        	numVillainWon++;
            return loss;
        } else if (villainsMove == HandSign.PAPER && herosMove == HandSign.ROCK) {
        	numVillainWon++;
            return loss;
        } else if (villainsMove == HandSign.ROCK && herosMove == HandSign.SCISSORS) {
        	numVillainWon++;
            return loss;
        } else {
            numHeroWon++;
            return win;
        }
	}
	/**
	 * Returns a String stating the round result, based on the result given as an int.
	 * @param int roundResult
	 * @return String
	 */
	private String returnRoundLabelText(int roundResult) {
		String labelText;
		if (roundResult == 0) {
			labelText = "You lose this round";
		} else if (roundResult == 1) {
			labelText = "You win this round";
		} else {
			labelText = "You draw this round";
		}
		return labelText;
	}
	/**
	 * Returns a random integer between 0 and 2 inclusive.
	 * @return int
	 */
	private int getRandomNumber() {
		return random.nextInt(3);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		paperScissorsRockFrame = new JFrame();
		paperScissorsRockFrame.setBounds(100, 100, 1200, 800);
		paperScissorsRockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paperScissorsRockFrame.getContentPane().setLayout(null);
		
		clueLabel = new JLabel("");
		clueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clueLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		clueLabel.setBounds(300, 230, 600, 72);
		paperScissorsRockFrame.getContentPane().add(clueLabel);
		
		villainChoseLabel = new JLabel("Villain chose:");
		villainChoseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		villainChoseLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		
		villainChoseLabel.setBounds(400, 170, 200, 30);
		paperScissorsRockFrame.getContentPane().add(villainChoseLabel);
		
		villainsMoveLabel = new JLabel("");
		villainsMoveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		villainsMoveLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		
		villainsMoveLabel.setBounds(629, 170, 150, 30);
		paperScissorsRockFrame.getContentPane().add(villainsMoveLabel);
		
		winOrLoseGameLabel = new JLabel("");
		winOrLoseGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winOrLoseGameLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		winOrLoseGameLabel.setBounds(375, 450, 450, 165);
		winOrLoseGameLabel.setVisible(false);
		paperScissorsRockFrame.getContentPane().add(winOrLoseGameLabel);
		
		winOrLoseRoundLabel = new JLabel("");
		winOrLoseRoundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winOrLoseRoundLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		
		winOrLoseRoundLabel.setBounds(400, 399, 400, 60);
		paperScissorsRockFrame.getContentPane().add(winOrLoseRoundLabel);
		
		paperScissorsRockTitleLabel = new JLabel("Paper Scissors Rock");
		paperScissorsRockTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paperScissorsRockTitleLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		
		paperScissorsRockTitleLabel.setBounds(350, 40, 500, 100);
		paperScissorsRockFrame.getContentPane().add(paperScissorsRockTitleLabel);
		
		int randomNumber = getRandomNumber();

		if (heroHasPowerUp) {
			giveClue(randomNumber);
		}
		villainsMove = HandSign.parseType(randomNumber);
		
		paperButton = new JButton("Paper");
		paperButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(0); //0 indicated the player chose paper.
				
			}
		});
		paperButton.setBounds(160, 500, 180, 60);
		paperScissorsRockFrame.getContentPane().add(paperButton);
		
		scissorsButton = new JButton("Scissors");
		scissorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(1); //1 indicated the player chose scissors.
			}
		});
		scissorsButton.setBounds(510, 500, 180, 60);
		paperScissorsRockFrame.getContentPane().add(scissorsButton);
		
		rockButton = new JButton("Rock");
		rockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(2); //2 indicates the player chose rock.
			}
		});
		rockButton.setBounds(860, 500, 180, 60);
		paperScissorsRockFrame.getContentPane().add(rockButton);
		
		goBackButton = new JButton("Go Back!");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleWindowGui.changeGame();
				paperScissorsRockFrame.dispose();
				gameEnvironment.openBattleWindow(team, cityGui);
			}
		});
		goBackButton.setBounds(980, 680, 180, 60);
		goBackButton.setVisible(false);
		paperScissorsRockFrame.getContentPane().add(goBackButton);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		paperScissorsRockFrame.getContentPane().add(backgroundPic);
		
	}
	/**
	 * Gives clue if the hero has the Paper Scissors Rock Clue power up.
	 * The clue displays two moves, one is the villains move, and the other is random.
	 * @param randomNumber int 
	 */
	private void giveClue(int randomNumber) {
		int secondRandomNumber = random.nextInt(3); 
		while (secondRandomNumber == randomNumber) {
			secondRandomNumber = random.nextInt(3); //Generates random numbers until the number is different to the number corresponding to the villains move.
		}
		String clue = "Clue: The Villain is going to choose either " + HandSign.parseType(randomNumber) + " or " + HandSign.parseType(secondRandomNumber);
		clueLabel.setText(clue);
	}
	/**
	 * Displays the villains move and the round result.
	 * 
	 * If the game is finished, hides the Paper, Scissors, and Rock buttons and displays the appropriate label.
	 * If the Villain or hero die, notifies the player.
	 * Allows the player to go back to the city, unless the entire game is over.
	 * 
	 * If the game is not over, displays the next clue, and decrements the villains lives or the hero's lives.
	 * @param num int . The players move as an int. 0 is paper, 1 is scissors, 2 is rock.
	 */
	private void buttonClicked(int num) {
		herosMove = HandSign.parseType(num);
		villainsMoveLabel.setText(villainsMove.toString());
		int roundResult = returnRoundResult(herosMove, villainsMove);
		if (numHeroWon == 3) {
			setButtonsInvisible();
			gameOver = true;
			endGameLabels("You win!");
			villain.loseLife();
			if (villain.getLives() == 0) {
				JOptionPane.showMessageDialog(paperScissorsRockFrame, "The villain is now dead!");
				goBackButton.setVisible(false);
				if (gameEnvironment.finalCity()) {
					gameEnvironment.gameWon();
					paperScissorsRockFrame.dispose();
				} else {
					battleWindowGui.villainDies();
					paperScissorsRockFrame.dispose();
				}
			}
			
		} else if (numVillainWon == 3) {
			setButtonsInvisible();
			gameOver = true;
			endGameLabels("You lose!");
			heroPlaying.doDamage(villainsDamage, team, battleWindowGui);
			if (heroPlaying.getHealth() <= 0) {
				if (gameEnvironment.herosLeft()) {
					goBackButton.setVisible(false);
					JOptionPane.showMessageDialog(paperScissorsRockFrame, "Your hero has died!");
					paperScissorsRockFrame.dispose();
					gameEnvironment.openBattleWindow(team, cityGui);
				} else {
					paperScissorsRockFrame.dispose();
					gameEnvironment.gameLost();
				}
				
			}
		}
		winOrLoseRoundLabel.setText(returnRoundLabelText(roundResult));
		//Displays next clue.
		if (!gameOver) {
			int randomNumber = getRandomNumber();
			
			if (heroHasPowerUp) {
				giveClue(randomNumber);
			}
			villainsMove = HandSign.parseType(randomNumber);
		}
		
	}
	/**
	 * Hides the Paper, Scissors and Rock buttons after the game is over.
	 */
	private void setButtonsInvisible() {
		paperButton.setVisible(false);
		scissorsButton.setVisible(false);
		rockButton.setVisible(false);
		
	}
	/**
	 * Displays either "You Win!" or "You Lose!".
	 * 
	 * Displays the button to go back to the battle window.
	 * @param gameResult String 
	 */
	private void endGameLabels(String gameResult) {
		winOrLoseGameLabel.setText(gameResult);
		winOrLoseGameLabel.setVisible(true);
		goBackButton.setVisible(true);
	}
}