import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PowerUpDenGUI {
	private int powerUpIndex;
	private int heroIndex;

	private JFrame powerUpDenGUIFrame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PowerUpDenGUI window = new PowerUpDenGUI();
					window.powerUpDenGUIFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String[] getListPowerUpNames() {
		int numberPowerUps = Team.getPowerUps().size();
		String[] powerUpNames = new String[numberPowerUps];
		for (int i = 0; i < numberPowerUps; i++) {
			powerUpNames[i] = Team.getPowerUps().get(i).getName();
		}	
		return powerUpNames;
	}
	private void applyPowerUp(PowerUp powerUp, Hero hero) {
		String powerUpName = powerUp.getName();
		switch (powerUpName) {
		case "Extra Roll":	hero.setHasDiceGamePowerUp(true);
							break;
		case "Extra Guess":	hero.setHasGuessNumberPowerUp(true);
							break;
		case "Clue for Paper Scissors Rock":	hero.setHasPaperScissorsRockPowerUp(true);
												break;
		}
		System.out.println(hero.getPowerUps());

	}

	/**
	 * Create the application.
	 */
	public PowerUpDenGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		powerUpDenGUIFrame = new JFrame();
		powerUpDenGUIFrame.setBounds(100, 100, 737, 438);
		powerUpDenGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		powerUpDenGUIFrame.getContentPane().setLayout(null);
		
		JComboBox powerUpSelectionBox = new JComboBox(getListPowerUpNames());
		powerUpSelectionBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				powerUpIndex = powerUpSelectionBox.getSelectedIndex();
			}
		});
		powerUpSelectionBox.setBounds(220, 75, 238, 34);
		powerUpDenGUIFrame.getContentPane().add(powerUpSelectionBox);
		
		JLabel lblChooseAPower = new JLabel("Choose a Power Up:");
		lblChooseAPower.setBounds(50, 43, 178, 20);
		powerUpDenGUIFrame.getContentPane().add(lblChooseAPower);
		
		JLabel lblChooseAHero = new JLabel("Choose a Hero to apply the Power Up to:");
		lblChooseAHero.setBounds(50, 121, 300, 20);
		powerUpDenGUIFrame.getContentPane().add(lblChooseAHero);
		
		JComboBox heroSelectionBox = new JComboBox(Team.getHeroNames());
		heroSelectionBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroIndex = heroSelectionBox.getSelectedIndex();
			}
		});
		heroSelectionBox.setBounds(220, 166, 249, 34);
		powerUpDenGUIFrame.getContentPane().add(heroSelectionBox);
		
		JButton btnBackToHome = new JButton("Back to Home Base!");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityGUI.CityScreen.setVisible(true);
				powerUpDenGUIFrame.dispose();
			}
		});
		btnBackToHome.setBounds(519, 331, 204, 34);
		powerUpDenGUIFrame.getContentPane().add(btnBackToHome);
		
		JButton applyPowerUpButton = new JButton("Apply");
		applyPowerUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Team.getPowerUps().size() == 0) {
					System.out.println("You have no Power Ups!");
				} else {
					applyPowerUp(Team.getPowerUps().get(powerUpIndex), Team.getHeroes().get(heroIndex));
				}
			}
		});
		applyPowerUpButton.setBounds(283, 282, 117, 25);
		powerUpDenGUIFrame.getContentPane().add(applyPowerUpButton);
		
		
	}
	
}