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

public class BattleWindow {

	private JFrame battleWindowFrame;
	private Villain villain;
	private String currentGame;
	private JLabel gameLabel = new JLabel();
	private Hero heroPlaying;
	private CityGUI cityGui;
	private Team team;
	private GameEnvironment gameEnvironment;
	private JComboBox heroSelection;
	
	/**
	 * Launch the application.
	 */

	public void makeVisible() {
		this.battleWindowFrame.setVisible(true);
	}
	
	public String getVillain () {
		return villain.getName();
	}
	
	public String getGame() {
		ArrayList<String> villainsGames = new ArrayList<String>();
		villainsGames = villain.getGames();
		Collections.shuffle(villainsGames);
		return villainsGames.get(0);		
	}

	/**
	 * Create the application.
	 * @param villain 
	 */

	public BattleWindow(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		cityGui = cityGuiInput;
		int currentCityIndex = gameEnvironment.getCurrentCityIndex();
		villain = gameEnvironment.getVillain(currentCityIndex);
		initialize();
	}
	public void changeGame() {
		currentGame = getGame();
		gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		gameLabel.setForeground(Color.WHITE);
		gameLabel.setText(currentGame);
		
	}
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
	 * @param villain 
	 */
	private void initialize() {
		battleWindowFrame = new JFrame();
		battleWindowFrame.setBounds(100, 100, 1200, 800);
		battleWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		battleWindowFrame.getContentPane().setLayout(null);
		
		changeGame();
		
		JLabel lblTaunt = new JLabel(villain.getTaunt());
		lblTaunt.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaunt.setForeground(Color.WHITE);
		lblTaunt.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTaunt.setBounds(425, 125, 350, 22);
		battleWindowFrame.getContentPane().add(lblTaunt);
		
		JLabel lblName = new JLabel(villain.getName());
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Dialog", Font.BOLD, 24));
		lblName.setBounds(425, 50, 350, 22);
		battleWindowFrame.getContentPane().add(lblName);
		
		heroSelection = new JComboBox(team.getHeroNames());
		heroSelection.setBounds(475, 430, 250, 30);
		battleWindowFrame.getContentPane().add(heroSelection);
	
		JLabel lblSelectAHero = new JLabel("Select a hero to battle!");
		lblSelectAHero.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAHero.setForeground(Color.WHITE);
		lblSelectAHero.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSelectAHero.setBounds(475, 380, 250, 22);
		battleWindowFrame.getContentPane().add(lblSelectAHero);
		
		JLabel lblIChallengeYou = new JLabel("The villain challenges you to: ");
		lblIChallengeYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblIChallengeYou.setForeground(Color.WHITE);
		lblIChallengeYou.setFont(new Font("Dialog", Font.BOLD, 17));
		lblIChallengeYou.setBounds(450, 268, 300, 22);
		battleWindowFrame.getContentPane().add(lblIChallengeYou);
		
		
		gameLabel.setBounds(500, 302, 200, 15);
		battleWindowFrame.getContentPane().add(gameLabel);
		
		JButton fightButton = new JButton("Fight");
		fightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroPlaying = team.getHeroes().get(heroSelection.getSelectedIndex());
				openCurrentGame(heroPlaying);
			}
		});
		fightButton.setBounds(510, 550, 180, 60);
		battleWindowFrame.getContentPane().add(fightButton);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(BattleWindow.class.getResource("/Images/battlearena.jpg")));
		backgroundPic.setBounds(-240, 0, 1504, 800);
		battleWindowFrame.getContentPane().add(backgroundPic);
	}

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
	public void closeWindow() {
		battleWindowFrame.dispose();
		
	}
	public void removeDeadHeroFromComboBox(Hero hero) {
		//heroSelection.removeItem(hero);
		heroSelection = new JComboBox(team.getHeroNames());
		heroSelection.setBounds(267, 108, 161, 29);
		battleWindowFrame.getContentPane().add(heroSelection);
	}
}
