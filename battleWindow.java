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

public class battleWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen(Villain villain) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					battleWindow window = new battleWindow(villain);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String getGame(Villain villain) {
		ArrayList<String> villainsGames = new ArrayList<String>();
		villainsGames = villain.getGames();
		Collections.shuffle(villainsGames);
		return villainsGames.get(0);		
	}

	/**
	 * Create the application.
	 * @param villain 
	 */

	public battleWindow(Villain villain) {
		initialize(villain);
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @param villain 
	 */
	private void initialize(Villain villain) {

		frame = new JFrame();
		frame.setBounds(100, 100, 724, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//PaperScissorsRockGUI paperScissorsRockGameGui = new PaperScissorsRockGUI();
		String villainsGame = getGame(villain);
		Hero heroPlaying;
		
		JLabel lblTaunt = new JLabel(villain.getTaunt());
		lblTaunt.setBounds(175, 60, 341, 22);
		frame.getContentPane().add(lblTaunt);
		
		JLabel lblName = new JLabel(villain.getName());
		lblName.setBounds(165, 28, 341, 22);
		frame.getContentPane().add(lblName);
		
		JComboBox heroSelection = new JComboBox(Team.getHeroNames());
		heroSelection.setBounds(267, 108, 161, 29);
		frame.getContentPane().add(heroSelection);
		
		heroPlaying = Team.getHeroes().get(heroSelection.getSelectedIndex());
		
		
		JLabel lblSelectAHero = new JLabel("Select a hero to battle!");
		lblSelectAHero.setBounds(31, 115, 195, 22);
		frame.getContentPane().add(lblSelectAHero);
		
		JLabel lblIChallengeYou = new JLabel("The villain challenges you to: ");
		lblIChallengeYou.setBounds(31, 175, 222, 22);
		frame.getContentPane().add(lblIChallengeYou);
		
		JLabel GameLabel = new JLabel(villainsGame);
		GameLabel.setBounds(267, 209, 175, 15);
		frame.getContentPane().add(GameLabel);
		
		JButton fightButton = new JButton("Fight");
		fightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(villainsGame);
				if (villainsGame == "paper scissors rock") {
					PaperScissorsRockGUI.NewScreen(villain, heroPlaying);
				} else if (villainsGame == "guess a number") {
					GuessNumberGUI.NewScreen(villain, heroPlaying);
				} else if (villainsGame == "Dice game") {
					DiceGameGUI.NewScreen(villain, heroPlaying);
				}
			}
		});
		fightButton.setBounds(277, 275, 117, 25);
		frame.getContentPane().add(fightButton);
	}
}
