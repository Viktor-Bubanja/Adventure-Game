import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DiceGameGUI {
	private int villainWon = 0;
	private int heroWon = 0;
	private boolean gameOver = false;
	private JFrame diceGameFrame;
	private Villain villain;
	private Hero heroPlaying;
	private BattleWindow battleWindow;

	/**
	 * Launch the application.
	 */
	public static void NewScreen(Villain villain, Hero heroPlaying, BattleWindow battleWindow) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiceGameGUI window = new DiceGameGUI(villain, heroPlaying, battleWindow);
					window.diceGameFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DiceGameGUI(Villain villainInput, Hero heroPlayingInput, BattleWindow battleWindowInput) {
		battleWindow = battleWindowInput;
		heroPlaying = heroPlayingInput;
		villain = villainInput;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		diceGameFrame = new JFrame();
		diceGameFrame.setBounds(100, 100, 727, 386);
		diceGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		diceGameFrame.getContentPane().setLayout(null);

		int villainsDamage = villain.getDamage();
		
		JLabel lblPhotoOfDice = new JLabel("photo of dice");
		lblPhotoOfDice.setBounds(148, 45, 108, 20);
		diceGameFrame.getContentPane().add(lblPhotoOfDice);
		
		JLabel labelVillainRoll = new JLabel("0");
		labelVillainRoll.setBounds(321, 122, 70, 15);
		diceGameFrame.getContentPane().add(labelVillainRoll);
		
		JLabel winOrLoseLabel = new JLabel("");
		winOrLoseLabel.setBounds(177, 219, 70, 15);
		diceGameFrame.getContentPane().add(winOrLoseLabel);
		
		JLabel heroWonLabel = new JLabel("0");
		heroWonLabel.setBounds(95, 231, 70, 15);
		diceGameFrame.getContentPane().add(heroWonLabel);
		
		JLabel villainWonLabel = new JLabel("0");
		villainWonLabel.setBounds(321, 231, 70, 15);
		diceGameFrame.getContentPane().add(villainWonLabel);
		
		JLabel resultLabel = new JLabel("result");
		resultLabel.setBounds(340, 172, 70, 15);
		diceGameFrame.getContentPane().add(resultLabel);
		
		JButton goBackButton = new JButton("Go Back!");
		goBackButton.setVisible(false);
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleWindow.changeGame();
				diceGameFrame.dispose();
			}
		});
		goBackButton.setBounds(541, 292, 117, 25);
		diceGameFrame.getContentPane().add(goBackButton);
		
		JLabel labelHeroRoll = new JLabel("0");
		labelHeroRoll.setBounds(77, 122, 70, 15);
		diceGameFrame.getContentPane().add(labelHeroRoll);
		JButton buttonRollDice = new JButton("Roll Dice!");
		buttonRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!gameOver) {
					Random randomHeroNumber = new Random();
					Random randomVillainNumber = new Random();
					int rollHero = randomHeroNumber.nextInt(6) + 1;
					int rollVillain = randomVillainNumber.nextInt(6) + 1;
					labelHeroRoll.setText(Integer.toString(rollHero));
					labelVillainRoll.setText(Integer.toString(rollVillain));
					if (rollHero > rollVillain) {
						winOrLoseLabel.setText("You win this round");
						heroWon++;
						if (heroWon == 3) {
							gameOver = true;
							resultLabel.setText("You win the game!");
							villain.loseLife();
							goBackButton.setVisible(true);
							if (villain.getLives() == 0) {
								JOptionPane.showMessageDialog(diceGameFrame, "The villain is now dead!");
							}
						}
						heroWonLabel.setText(Integer.toString(heroWon));
					} else if (rollVillain > rollHero) {
						winOrLoseLabel.setText("You lose this round");
						villainWon++;
						if (villainWon == 3) {
							gameOver = true;
							resultLabel.setText("You lose the game!");
							heroPlaying.doDamage(villainsDamage);
							goBackButton.setVisible(true);
							if (heroPlaying.getHealth() <= 0)
								JOptionPane.showMessageDialog(diceGameFrame, "Your hero has died!");
						}
						villainWonLabel.setText(Integer.toString(villainWon));
					} else {
						winOrLoseLabel.setText("Draw");
					}
					
				}
			}
		});
		buttonRollDice.setBounds(149, 146, 117, 25);
		diceGameFrame.getContentPane().add(buttonRollDice);
		
		JLabel lblYouGot = new JLabel("you got:");
		lblYouGot.setBounds(29, 77, 70, 15);
		diceGameFrame.getContentPane().add(lblYouGot);
		
		JLabel lblVillainGot = new JLabel("villain got:");
		lblVillainGot.setBounds(232, 77, 102, 15);
		diceGameFrame.getContentPane().add(lblVillainGot);
		
		JLabel lblTally = new JLabel("Tally:");
		lblTally.setBounds(29, 204, 70, 15);
		diceGameFrame.getContentPane().add(lblTally);
		
		JLabel tallyHeroLabel = new JLabel("Hero:");
		tallyHeroLabel.setBounds(29, 231, 70, 15);
		diceGameFrame.getContentPane().add(tallyHeroLabel);
		
		JLabel tallyVillainLabel = new JLabel("Villain:");
		tallyVillainLabel.setBounds(232, 231, 70, 15);
		diceGameFrame.getContentPane().add(tallyVillainLabel);
		
		
	}
}
