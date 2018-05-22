import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	public static void NewScreen(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleWindow window = new BattleWindow(teamInput, gameEnvironmentInput, cityGuiInput);
					window.battleWindowFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		battleWindowFrame.setBounds(100, 100, 1000, 700);
		battleWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		battleWindowFrame.getContentPane().setLayout(null);
		
		changeGame();
		
		JLabel lblTaunt = new JLabel(villain.getTaunt());
		lblTaunt.setBounds(175, 60, 341, 22);
		battleWindowFrame.getContentPane().add(lblTaunt);
		
		JLabel lblName = new JLabel(villain.getName());
		lblName.setBounds(165, 28, 341, 22);
		battleWindowFrame.getContentPane().add(lblName);
		
		heroSelection = new JComboBox(team.getHeroNames());
		heroSelection.setBounds(267, 108, 161, 29);
		battleWindowFrame.getContentPane().add(heroSelection);
	
		JLabel lblSelectAHero = new JLabel("Select a hero to battle!");
		lblSelectAHero.setBounds(31, 115, 195, 22);
		battleWindowFrame.getContentPane().add(lblSelectAHero);
		
		JLabel lblIChallengeYou = new JLabel("The villain challenges you to: ");
		lblIChallengeYou.setBounds(31, 175, 222, 22);
		battleWindowFrame.getContentPane().add(lblIChallengeYou);
		
		
		gameLabel.setBounds(267, 209, 175, 15);
		battleWindowFrame.getContentPane().add(gameLabel);
		
		JButton fightButton = new JButton("Fight");
		fightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroPlaying = team.getHeroes().get(heroSelection.getSelectedIndex());
				openCurrentGame(heroPlaying);
			}
		});
		fightButton.setBounds(277, 275, 117, 25);
		battleWindowFrame.getContentPane().add(fightButton);
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
