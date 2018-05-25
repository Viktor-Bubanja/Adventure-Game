import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class BattleWindowGUI {
/**
 * Attributes:
 * The frame to display
 * The game Environment handling the game
 * The villain you are currently fighting
 * The hero you select to play with
 * The current city you are fighting in.
 * Your team
 * The game the villain challenges you to play
 * Label that displays the game you are challenged to play
 */
	private JFrame battleWindowFrame;
	private GameEnvironment gameEnvironment;
	private Villain villain;
	private Hero heroPlaying;
	private CityGUI cityGui;
	private Team team;
	private String currentGame;
	private JLabel gameLabel;
	private JComboBox heroSelection;
/**
 * Sets the battle window to visible
 */
	public void makeVisible() {
		this.battleWindowFrame.setVisible(true);
	}
	/**
	 * @return String The name of the villain you are fighting
	 */
	public String getVillain () {
		return villain.getName();
	}
	/**
	 * @return String A random game from the list of games the Villain currently fighting likes to play
	 */
	public String getGame() {
		ArrayList<String> villainsGames = new ArrayList<String>();
		villainsGames = villain.getGames();
		Collections.shuffle(villainsGames);
		return villainsGames.get(0);		
	}

/**
 * Initializes a Battle window, the window where you are locked in a fight with a villain
 * @param teamInput Team
 * @param gameEnvironmentInput GameEnvironmentt
 * @param cityGuiInput CityGUI
 */
	public BattleWindowGUI(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		cityGui = cityGuiInput;
		int currentCityIndex = gameEnvironment.getCurrentCityIndex();
		villain = gameEnvironment.getVillain(currentCityIndex);
		initialize();
	}
/**
 * change the label displaying the game you are challenged too
 */
	public void changeGame() {
		currentGame = getGame();
		gameLabel = new JLabel();
		gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		gameLabel.setForeground(Color.WHITE);
		gameLabel.setText(currentGame);
		gameLabel.setBounds(500, 302, 200, 15);
		battleWindowFrame.getContentPane().add(gameLabel);
		
	}
	/**
	 * Opens the game GUI of the game the villain challenges you too
	 * @param heroPlaying Your hero you have selected to fight with
	 */
	public void openCurrentGame(Hero heroPlaying) {
		battleWindowFrame.dispose();
		if (currentGame == "paper scissors rock") {
			gameEnvironment.openPaperScissorsRockGUI(heroPlaying, this, gameEnvironment);
		} else if (currentGame == "guess a number") {
			gameEnvironment.openGuessNumberGUI(heroPlaying, this, gameEnvironment);
		} else if (currentGame == "Dice game") {
			gameEnvironment.openDiceGameGUI(heroPlaying, this, gameEnvironment);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		battleWindowFrame = new JFrame();
		battleWindowFrame.setBounds(100, 100, 1200, 800);
		battleWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		battleWindowFrame.getContentPane().setLayout(null);
		
		changeGame(); // Selects and displays the first game you are challenges
		
		JLabel tauntLabel = new JLabel(villain.getTaunt()); //Label displaying the villains taunt
		tauntLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tauntLabel.setForeground(Color.WHITE);
		tauntLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		tauntLabel.setBounds(300, 125, 600, 22);
		battleWindowFrame.getContentPane().add(tauntLabel);
		
		JLabel nameLabel = new JLabel(villain.getName());		//Label displaying the villains name
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		nameLabel.setBounds(300, 50, 600, 22);
		battleWindowFrame.getContentPane().add(nameLabel);
		
		heroSelection = new JComboBox(team.getHeroNames()); //Combobox to select your hero
		heroSelection.setBounds(475, 430, 250, 30);
		battleWindowFrame.getContentPane().add(heroSelection);
	
		JLabel selectAHeroLabel = new JLabel("Select a hero to battle!");  //Label Instructions for you to select a hero to battle with
		selectAHeroLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectAHeroLabel.setForeground(Color.WHITE);
		selectAHeroLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		selectAHeroLabel.setBounds(475, 380, 250, 22);
		battleWindowFrame.getContentPane().add(selectAHeroLabel);
		
		JLabel challengeYouLabel = new JLabel("The villain challenges you to: ");  //Label challenge too:
		challengeYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
		challengeYouLabel.setForeground(Color.WHITE);
		challengeYouLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		challengeYouLabel.setBounds(450, 268, 300, 22);
		battleWindowFrame.getContentPane().add(challengeYouLabel);
		
		JButton fightButton = new JButton("Fight");				//Button that starts the fight in the selected game with the selected hero
		fightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroPlaying = team.getHeroes().get(heroSelection.getSelectedIndex());
				openCurrentGame(heroPlaying);
			}
		});
		fightButton.setBounds(510, 550, 180, 60);
		battleWindowFrame.getContentPane().add(fightButton);
		
		JLabel backgroundPic = new JLabel("");   // Background pic of the arena
		backgroundPic.setIcon(new ImageIcon(BattleWindowGUI.class.getResource("/Images/battlearena.jpg")));
		backgroundPic.setBounds(-240, 0, 1504, 800);
		battleWindowFrame.getContentPane().add(backgroundPic);
	}
/**
 * Called when you have won a game
 * Moves you to a new city
 * Gives you a reward for killing the villain
 */
	public void villainDies() {
		closeWindow();
		cityGui.disposeCity();
		if (team.teamHasLucky()) {
			team.increaseMoneyBy(200);
		} else {
			team.increaseMoneyBy(100);
		}
		gameEnvironment.moveToNewCity(team);
		
	}
	/**
	 * Disposes of the current battle window
	 */
	public void closeWindow() {
		battleWindowFrame.dispose();
		
	}
	/**
	 * Called when a hero dies
	 * Removes him from the select hero ComboBox
	 * @param hero The Hero to remove
	 */
	public void removeDeadHeroFromComboBox(Hero hero) {
		//heroSelection.removeItem(hero);
		heroSelection = new JComboBox(team.getHeroNames());
		heroSelection.setBounds(267, 108, 161, 29);
		battleWindowFrame.getContentPane().add(heroSelection);
	}
}
