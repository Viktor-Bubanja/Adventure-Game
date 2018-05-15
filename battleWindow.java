import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class battleWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					battleWindow window = new battleWindow();
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
	 */
	public battleWindow() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 724, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		GameEnvironment gameEnvironment = new GameEnvironment();
		int currentCity = gameEnvironment.getCurrentCity();
		Villain villain = gameEnvironment.getVillains().get(currentCity);
		PaperScissorsRock paperScissorsRockGame = new PaperScissorsRock();
		String villainsGame = getGame(villain);
		
		
		JLabel lblTaunt = new JLabel(villain.getTaunt());
		lblTaunt.setBounds(175, 60, 341, 22);
		frame.getContentPane().add(lblTaunt);
		
		JLabel lblName = new JLabel(villain.getName());
		lblName.setBounds(165, 28, 341, 22);
		frame.getContentPane().add(lblName);
		
		JComboBox comboBox = new JComboBox(Team.getHeroNames());
		comboBox.setBounds(267, 108, 161, 29);
		frame.getContentPane().add(comboBox);
		
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
				if (villainsGame == "paper scissors rock") {
					
				} else if (villainsGame == "guess number") {
					
				} else if (villainsGame == "dice game") {
					
				}
			}
		});
		fightButton.setBounds(277, 275, 117, 25);
		frame.getContentPane().add(fightButton);
	}
}
