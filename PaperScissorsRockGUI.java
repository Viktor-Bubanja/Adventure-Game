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


public class PaperScissorsRockGUI {

	private JFrame paperScissorsRockFrame;
	private Random random = new Random();
	private int numVillainWon = 0;
	private int numHeroWon = 0;
	private HandSign herosMove;
	private HandSign villainsMove;
	private JLabel lblPaperScissorsRock = new JLabel("Paper Scissors Rock");
	private JLabel winOrLoseGameLabel = new JLabel("");
	private JLabel lblVillainGot = new JLabel("Villain got:");
	private JLabel villainsMoveLabel = new JLabel("");
	private JLabel winOrLoseRoundLabel = new JLabel("");
	private JButton goBackButton = new JButton("Go Back!");
	private JButton paperButton = new JButton("Paper");
	private JButton rockButton = new JButton("Rock");
	private JButton scissorsButton = new JButton("Scissors");
	private boolean gameOver = false;
	private int villainsDamage;
	private Villain villain;
	private Hero heroPlaying;
	private CityGUI cityGui;
	private BattleWindow battleWindow;
	private boolean heroHasPowerUp = false;
	private JLabel clueLabel = new JLabel("");
	private Team team;
	private GameEnvironment gameEnvironment;
	private JLabel testLabel = new JLabel("");
	
	//Create the application.
	
	public void makeVisible() {
		this.paperScissorsRockFrame.setVisible(true);
	}
	 
	public PaperScissorsRockGUI(Hero heroPlayingInput, BattleWindow battleWindowInput, GameEnvironment gameEnvironmentInput) {
		gameEnvironment = gameEnvironmentInput;
		battleWindow = battleWindowInput;
		heroPlaying = heroPlayingInput;
		//villain = villainInput;
		int currentCityIndex = gameEnvironment.getCurrentCityIndex();
		cityGui = gameEnvironment.getCurrentCity();
		villain = gameEnvironment.getVillain(currentCityIndex);
		team = gameEnvironment.getTeam();
		
		villainsDamage = villain.getDamage();
		if (heroPlaying.getHasPaperScissorsRockPowerUp()) {
			heroHasPowerUp = true;
			heroPlaying.setHasPaperScissorsRockPowerUp(false);
		}
		initialize();
	}
	
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
	
	private int getRandomNumber() {
		return random.nextInt(3);
	}
	
	 //Initialize the contents of the frame.
	private void initialize() {
		
		testLabel.setBounds(609, 306, 70, 15);

		paperScissorsRockFrame = new JFrame();
		paperScissorsRockFrame.setBounds(100, 100, 1200, 800);
		paperScissorsRockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paperScissorsRockFrame.getContentPane().setLayout(null);
		
		clueLabel = new JLabel("dhgfh");
		clueLabel.setBounds(412, 64, 574, 72);
		paperScissorsRockFrame.getContentPane().add(clueLabel);
		
		lblVillainGot.setBounds(51, 121, 87, 15);
		paperScissorsRockFrame.getContentPane().add(lblVillainGot);
		
		villainsMoveLabel.setBounds(178, 142, 70, 15);
		paperScissorsRockFrame.getContentPane().add(villainsMoveLabel);
		
		winOrLoseRoundLabel.setBounds(349, 142, 70, 15);
		paperScissorsRockFrame.getContentPane().add(winOrLoseRoundLabel);
		
		lblPaperScissorsRock.setBounds(139, 12, 151, 15);
		paperScissorsRockFrame.getContentPane().add(lblPaperScissorsRock);
		
		int randomNumber = getRandomNumber();

		if (heroHasPowerUp) {
			giveClue(randomNumber);
		}
		villainsMove = HandSign.parseType(randomNumber);
		
		paperButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(0);
				
			}
		});
		paperButton.setBounds(42, 64, 96, 25);
		paperScissorsRockFrame.getContentPane().add(paperButton);
		
		
		scissorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!gameOver) {
					buttonClicked(1);
				} 
			}
		});
		scissorsButton.setBounds(167, 64, 96, 25);
		paperScissorsRockFrame.getContentPane().add(scissorsButton);
		
		
		rockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(2);
			}
		});
		rockButton.setBounds(298, 64, 96, 25);
		paperScissorsRockFrame.getContentPane().add(rockButton);
		
		winOrLoseGameLabel.setFont(new Font("Dialog", Font.BOLD, 55));
		winOrLoseGameLabel.setBounds(12, 39, 424, 165);
		winOrLoseGameLabel.setVisible(false);
		paperScissorsRockFrame.getContentPane().add(winOrLoseGameLabel);
		
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleWindow.changeGame();
				paperScissorsRockFrame.dispose();
				gameEnvironment.openBattleWindow(team, cityGui);
			}
		});
		goBackButton.setBounds(302, 212, 117, 25);
		goBackButton.setVisible(false);
		paperScissorsRockFrame.getContentPane().add(goBackButton);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		paperScissorsRockFrame.getContentPane().add(backgroundPic);
		
		paperScissorsRockFrame.getContentPane().add(testLabel);
		
		
	}
	private void giveClue(int randomNumber) {
		int secondRandomNumber = random.nextInt(3);
		while (secondRandomNumber == randomNumber) {
			secondRandomNumber = random.nextInt(3);
		}
		String clue = "Clue: The Villain is going to choose either " + HandSign.parseType(randomNumber) + " or " + HandSign.parseType(secondRandomNumber);
		clueLabel.setText(clue);
	}
	
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
				if (gameEnvironment.finalCity()) {
					gameEnvironment.gameWon();
					paperScissorsRockFrame.dispose();
				} else {
					battleWindow.villainDies();
					paperScissorsRockFrame.dispose();
				}
			} else {
				//gameEnvironment.openBattleWindow(team, cityGui);
			}
			
		} else if (numVillainWon == 3) {
			setButtonsInvisible();
			gameOver = true;
			endGameLabels("You lose!");
			heroPlaying.doDamage(villainsDamage, team, battleWindow);
			if (heroPlaying.getHealth() <= 0) {
				if (gameEnvironment.herosLeft()) {
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
		if (!gameOver) {
			int randomNumber = getRandomNumber();
			if (heroHasPowerUp) {
				giveClue(randomNumber);
			}
			villainsMove = HandSign.parseType(randomNumber);
		}
	}
	private void setButtonsInvisible() {
		paperButton.setVisible(false);
		scissorsButton.setVisible(false);
		rockButton.setVisible(false);
	}
	private void endGameLabels(String gameResult) {
		winOrLoseGameLabel.setText(gameResult);
		winOrLoseGameLabel.setVisible(true);
		goBackButton.setVisible(true);
	}
}