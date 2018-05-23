import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class PowerUpDenGUI {
	private JFrame powerUpDenGUIFrame;
	private int powerUpIndex;
	private int heroIndex;
	private CityGUI cityGui;
	private Team team;
	private GameEnvironment gameEnvironment;
	private JComboBox powerUpSelectionBox;
	private JLabel heroAlreadyHasPowerUpLabel;
	private JLabel noPowerUpsLabel;

	/**
	 * Launch the application.
	 */

	
	public void makeVisible() {
		this.powerUpDenGUIFrame.setVisible(true);
	}
	/**
	 * 
	 * @return 
	 */
	private String[] getListPowerUpNames() {

		int numberPowerUps = team.getPowerUps().size();
		String[] powerUpNames = new String[numberPowerUps];
		for (int i = 0; i < numberPowerUps; i++) {
			powerUpNames[i] = team.getPowerUps().get(i).getName();
		}	
		return powerUpNames;
	}
	private void applyPowerUp(PowerUp powerUp, Hero hero) {
		String powerUpName = powerUp.getName();
		boolean usedPowerUp = false;
		switch (powerUpName) {
		case "Extra Roll":						if (!hero.getHasDiceGamePowerUp()) {
													hero.setHasDiceGamePowerUp(true);
													usedPowerUp = true;
												} else {
													heroAlreadyHasPowerUpLabel.setVisible(true);
												}
												break;
		case "Extra Guess":						if (!hero.getHasGuessNumberPowerUp()) {
													hero.setHasGuessNumberPowerUp(true);
													usedPowerUp = true;
												} else {
													heroAlreadyHasPowerUpLabel.setVisible(true);
												}
												break;
		case "Clue for Paper Scissors Rock":	if (!hero.getHasPaperScissorsRockPowerUp()) {
													hero.setHasPaperScissorsRockPowerUp(true);
													usedPowerUp = true;
												} else {
													heroAlreadyHasPowerUpLabel.setVisible(true);
												}
												break;
		}
		if (usedPowerUp) {
			team.removePowerUp(powerUp);
			removePowerUpFromSelectionBox(powerUpName);
		}

	}
	private void removePowerUpFromSelectionBox(String powerUpName) {
		powerUpSelectionBox.removeItem(powerUpName);
		if (team.getPowerUps().size() > 0) {
			powerUpSelectionBox.setSelectedIndex(0);
		}
	}

	/**
	 * Create the application.
	 */
	public PowerUpDenGUI(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		cityGui = cityGuiInput;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		powerUpDenGUIFrame = new JFrame();
		powerUpDenGUIFrame.setBounds(100, 100, 1200, 800);
		powerUpDenGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		powerUpDenGUIFrame.getContentPane().setLayout(null);
		
		heroAlreadyHasPowerUpLabel = new JLabel("Hero already has this power up");
		heroAlreadyHasPowerUpLabel.setForeground(Color.RED);
		heroAlreadyHasPowerUpLabel.setBounds(781, 433, 238, 25);
		powerUpDenGUIFrame.getContentPane().add(heroAlreadyHasPowerUpLabel);
		heroAlreadyHasPowerUpLabel.setVisible(false);
		
		JLabel noPowerUpsLabel = new JLabel("You have no power ups!");
		noPowerUpsLabel.setBounds(523, 552, 193, 15);
		powerUpDenGUIFrame.getContentPane().add(noPowerUpsLabel);
		noPowerUpsLabel.setVisible(false);
		
		powerUpSelectionBox = new JComboBox(getListPowerUpNames());
		powerUpSelectionBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroAlreadyHasPowerUpLabel.setVisible(false);
				powerUpIndex = powerUpSelectionBox.getSelectedIndex();
			}
		});
		powerUpSelectionBox.setBounds(475, 290, 250, 30);
		powerUpDenGUIFrame.getContentPane().add(powerUpSelectionBox);
		
		JLabel lblChooseAPower = new JLabel("Choose a Power Up:");
		lblChooseAPower.setFont(new Font("Dialog", Font.BOLD, 17));
		lblChooseAPower.setBounds(500, 388, 200, 25);
		powerUpDenGUIFrame.getContentPane().add(lblChooseAPower);
		
		JLabel lblChooseAHero = new JLabel("Choose a Hero to apply the Power Up to:");
		lblChooseAHero.setFont(new Font("Dialog", Font.BOLD, 17));
		lblChooseAHero.setBounds(400, 245, 400, 25);
		powerUpDenGUIFrame.getContentPane().add(lblChooseAHero);
		
		JComboBox heroSelectionBox = new JComboBox(team.getHeroNames());
		heroSelectionBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroAlreadyHasPowerUpLabel.setVisible(false);
				heroIndex = heroSelectionBox.getSelectedIndex();
			}
		});
		heroSelectionBox.setBounds(475, 430, 250, 30);
		powerUpDenGUIFrame.getContentPane().add(heroSelectionBox);
		
		JButton btnBackToHome = new JButton("Back to Home Base!");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cityGui.makeCityVisible();
				powerUpDenGUIFrame.dispose();
			}
		});
		btnBackToHome.setBounds(980, 680, 180, 60);
		powerUpDenGUIFrame.getContentPane().add(btnBackToHome);
		
		JButton applyPowerUpButton = new JButton("Apply");
		applyPowerUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team.getPowerUps().size() == 0) {
					noPowerUpsLabel.setVisible(true);
				} else {
					applyPowerUp(team.getPowerUps().get(powerUpIndex), team.getHeroes().get(heroIndex));
					
				}
			}
		});
		applyPowerUpButton.setBounds(540, 490, 120, 40);
		powerUpDenGUIFrame.getContentPane().add(applyPowerUpButton);
		
		JLabel lblPowerupDen = new JLabel("PowerUp Den");
		lblPowerupDen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPowerupDen.setFont(new Font("Dialog", Font.BOLD, 40));
		lblPowerupDen.setBounds(400, 40, 400, 50);
		powerUpDenGUIFrame.getContentPane().add(lblPowerupDen);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		powerUpDenGUIFrame.getContentPane().add(backgroundPic);
		

	}
}
