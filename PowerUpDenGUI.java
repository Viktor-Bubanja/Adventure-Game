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
	/**
	 * Power Up Den GUI class
	 * Hero's can apply power ups while in the power up den.
	 * Attributes:
	 * JFrame for the PowerUpDenGUI
	 * Index of the power up the player selected from the combo box.
	 * Index of the hero the player selected from the combo box.
	 * The current city you are fighting in.
	 * Your team
	 * The game environment handling the game.
	 * Combo box for selecting which power up to apply.
	 * Warning label if hero already has a power up.
	 */
	private JFrame powerUpDenGUIFrame;
	private int powerUpIndex;
	private int heroIndex;
	private CityGUI cityGui;
	private Team team;
	private JComboBox powerUpSelectionBox;
	private JLabel heroAlreadyHasPowerUpLabel;
	/**
	 * Makes the window visible when first initialized.
	 */
	public void makeVisible() {
		this.powerUpDenGUIFrame.setVisible(true);
	}
	/**
	 * 
	 * @return String[] powerUpNames. List of power up names to display in the combo box.
	 */
	private String[] getListPowerUpNames() {

		int numberPowerUps = team.getPowerUps().size();
		String[] powerUpNames = new String[numberPowerUps];
		for (int i = 0; i < numberPowerUps; i++) {
			powerUpNames[i] = team.getPowerUps().get(i).getName();
		}	
		return powerUpNames;
	}
	/**
	 * Applies a given power up to a given hero. 
	 * Only applies if the hero does not already have the power up.
	 * Removes the power up from the teams inventory.
	 * Removes the power up from the power up combo box.
	 * @param PowerUp powerUp
	 * @param Hero hero
	 */
	private void applyPowerUp(PowerUp powerUp, Hero hero) {
		String powerUpName = powerUp.getName();
		boolean usedPowerUp = false;
		switch (powerUpName) {
		case "Golden Die":						if (!hero.getHasDiceGamePowerUp()) {
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
	/**
	 * Removes power up from selection box after a power up is consumed.
	 * @param String powerUpName. Name of power up to remove from combo box.
	 */
	private void removePowerUpFromSelectionBox(String powerUpName) {
		powerUpSelectionBox.removeItem(powerUpName);
		if (team.getPowerUps().size() > 0) {
			powerUpSelectionBox.setSelectedIndex(0);
		}
	}

/**
 * Calls initialize()
 * @param teamInput Team 
 * @param cityGuiInput CityGUI 
 */
	public PowerUpDenGUI(Team teamInput, CityGUI cityGuiInput) {
		team = teamInput;
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
		powerUpSelectionBox.setBounds(475, 430, 250, 30);
		powerUpDenGUIFrame.getContentPane().add(powerUpSelectionBox);
		
		JLabel choosePowerLabel = new JLabel("Choose a Power Up:");
		choosePowerLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		choosePowerLabel.setBounds(500, 388, 200, 25);
		powerUpDenGUIFrame.getContentPane().add(choosePowerLabel);
		
		JLabel chooseHeroLabel = new JLabel("Choose a Hero to apply the Power Up to:");
		chooseHeroLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		chooseHeroLabel.setBounds(400, 245, 400, 25);
		powerUpDenGUIFrame.getContentPane().add(chooseHeroLabel);
		
		JComboBox heroSelection = new JComboBox(team.getHeroNames());
		heroSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroAlreadyHasPowerUpLabel.setVisible(false); //When the player changes the selected hero, warning label is made invisible.
				heroIndex = heroSelection.getSelectedIndex();
			}
		});
		heroSelection.setBounds(475, 290, 250, 30);
		powerUpDenGUIFrame.getContentPane().add(heroSelection);
		
		JButton backHomeButton = new JButton("Back to Home Base!");
		backHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cityGui.makeCityVisible();
				powerUpDenGUIFrame.dispose();
			}
		});
		backHomeButton.setBounds(980, 680, 180, 60);
		powerUpDenGUIFrame.getContentPane().add(backHomeButton);
		
		JButton applyPowerUpButton = new JButton("Apply");
		applyPowerUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team.getPowerUps().size() == 0) {
					noPowerUpsLabel.setVisible(true); //If the team doesn't have any power ups, displays a warning label.
				} else {
					applyPowerUp(team.getPowerUps().get(powerUpIndex), team.getHeroes().get(heroIndex));
				}
			}
		});
		applyPowerUpButton.setBounds(540, 490, 120, 40);
		powerUpDenGUIFrame.getContentPane().add(applyPowerUpButton);
		
		JLabel denTitleLabel = new JLabel("PowerUp Den");
		denTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		denTitleLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		denTitleLabel.setBounds(400, 40, 400, 50);
		powerUpDenGUIFrame.getContentPane().add(denTitleLabel);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		powerUpDenGUIFrame.getContentPane().add(backgroundPic);
		

	}
}
