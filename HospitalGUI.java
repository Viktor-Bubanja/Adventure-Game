
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import java.awt.Color;

public class HospitalGUI {
	/**
	 * Hospital GUI Class
	 * Hero's can heal while in the hospital.
	 * Each healing item takes a certain amount of time to apply, which is reduced depending on the
	 * hero's recovery rate. A timer displays the time remaining for the healing item to apply.
	 * Attributes:
	 * JFrame for the HospitalGUI
	 * Index of the healing item the player selected from the combo box.
	 * Index of the hero the player selected from the combo box.
	 * The time remaining until the healing item is applying.
	 * Timer used to control how long healing items takes to apply.
	 * The hero currently selected.
	 * The healing item currently selected.
	 * The city the team is currently in.
	 * The team
	 * Combo Box for selecting the healing item.
	 * Button for healing the selected hero with the selected healing item.
	 * Labels displaying the status of the heal.
	 * Full health warning that displays if the player tries to heal a hero with full health.
	 */
	private JFrame HospitalFrame;
	private int healingItemIndex;
	private int heroIndex;
	private int timeRemaining = 1;
	private Timer timer;
	private Hero heroToHeal;
	private HealingItem currentHealingItem;
	private CityGUI cityGui;
	private Team team;
	private JComboBox<String> healingItemComboBox;
	private JButton healButton;
	private JLabel currentlyHealingLabel;
	private JLabel timeLeftLabel;
	private JLabel countdownTimerLabel;
	private JLabel fullHealthWarningLabel;
	
	/**
	 * Makes the Hospital window visible when first initialized.
	 */
	public void makeVisible() {
		this.HospitalFrame.setVisible(true);
	}
	/**
	 * Calls initialize()
	 * @param Team teamInput
	 * @param CityGUI cityGuiInput
	 */
	public HospitalGUI(Team teamInput, CityGUI cityGuiInput) {
		team = teamInput;
		cityGui = cityGuiInput;
		initialize();
	}
	/**
	 * If the team chooses to go back to the city, makes city visible again and closes the hospital window.
	 */
	private void finishedWindow() {
		cityGui.makeCityVisible();
		HospitalFrame.dispose();
	}
	/**
	 * Heals selected hero with the selected healing item.
	 * Only applies healing item if the hero is below full health, then removes the healing item from the team's inventory.
	 * Calculates the healing time by multiplying the application time of the healing item by 10 then dividing by the hero's recovery rate.
	 * (The higher the recovery rate, the lower the healing time)
	 * Player can only heal one hero at a time.
	 * @param HealingItem healingItem
	 * @param Hero hero
	 */
	private void healHero(HealingItem healingItem, Hero hero) {
		heroToHeal = hero;
		currentHealingItem = healingItem;
		if (heroToHeal.getHealth() < heroToHeal.getMaxHealth()) {
			team.removeHealingItem(healingItem);
			removeHealingItemFromComboBox(healingItem.getName());
			countdownTimerLabel.setVisible(true);
			timeRemaining = (healingItem.getApplicationTime() * 10 )/ heroToHeal.getRecoveryRate(); //calculate healing time.
			currentlyHealingLabel.setVisible(true);
			timeLeftLabel.setVisible(true);
			healButton.setVisible(false);
			timer.start();
			
		} else {
			fullHealthWarningLabel.setVisible(true);
		}
		
	}
	/**
	 * After consuming, removes the healing item from the healing item combo box.
	 * @param String healingItemName. Name of healing item to be removed.
	 */
	private void removeHealingItemFromComboBox(String healingItemName) {
		healingItemComboBox.removeItem(healingItemName);
		if (team.getHealingItems().size() > 0) {
			healingItemComboBox.setSelectedIndex(0); //while there are still healing items, resets the index of the selected item to zero to avoid index errors.
		}
	}

	/**
	 * Returns a list of the names of the healing items to display in the healing items combo box.
	 * @return String[] healingItemNames. List of healing item names.
	 */
	private String[] getListHealingItemNames() {
		int numberHealingItems = team.getHealingItems().size();
		String[] healingItemNames = new String[numberHealingItems];
		for (int i = 0; i < numberHealingItems; i++) {
			healingItemNames[i] = team.getHealingItems().get(i).getName();
		}	
		return healingItemNames;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ActionListener countdown = new ActionListener() { //gets called every second by the timer.
			public void actionPerformed(ActionEvent e) {
				timeRemaining--;
				countdownTimerLabel.setText(Integer.toString(timeRemaining));
				if (timeRemaining <= 0) { //healing is finished
					timer.stop();
					healButton.setVisible(true);
					currentlyHealingLabel.setVisible(false);
					countdownTimerLabel.setText("Done");
					heroToHeal.heal(currentHealingItem.getHealingAmount());
					cityGui.updateHeroListLabel(); //updates the status of the healed hero's health in the City GUI after healing.
				}
			}
		};
		
		HospitalFrame = new JFrame();
		HospitalFrame.setTitle("Hospital");
		HospitalFrame.setBounds(100, 100, 1200, 800);
		HospitalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HospitalFrame.getContentPane().setLayout(null);
		
		countdownTimerLabel = new JLabel("");
		countdownTimerLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		countdownTimerLabel.setBounds(973, 288, 105, 15);
		HospitalFrame.getContentPane().add(countdownTimerLabel);
		countdownTimerLabel.setVisible(false);
		
		currentlyHealingLabel = new JLabel("Healing...");
		currentlyHealingLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		currentlyHealingLabel.setBounds(900, 239, 113, 15);
		HospitalFrame.getContentPane().add(currentlyHealingLabel);
		
		fullHealthWarningLabel = new JLabel("Hero is already full health!");
		fullHealthWarningLabel.setForeground(Color.RED);
		fullHealthWarningLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		fullHealthWarningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fullHealthWarningLabel.setBounds(450, 557, 300, 15);
		HospitalFrame.getContentPane().add(fullHealthWarningLabel);
		fullHealthWarningLabel.setVisible(false);
		
		timeLeftLabel = new JLabel("Time left:");
		timeLeftLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		timeLeftLabel.setBounds(856, 288, 105, 15);
		HospitalFrame.getContentPane().add(timeLeftLabel);
		timeLeftLabel.setVisible(false);
		
		JLabel lblChooseAHero = new JLabel("Choose a hero to heal");
		lblChooseAHero.setFont(new Font("Dialog", Font.BOLD, 17));
		lblChooseAHero.setBounds(488, 245, 227, 32);
		HospitalFrame.getContentPane().add(lblChooseAHero);
		
		JLabel lblHospital = new JLabel("Hospital");
		lblHospital.setHorizontalAlignment(SwingConstants.CENTER);
		lblHospital.setFont(new Font("Dialog", Font.BOLD, 40));
		lblHospital.setBounds(500, 40, 200, 50);
		HospitalFrame.getContentPane().add(lblHospital);

		JLabel lblChooseAHealing = new JLabel("Choose a healing item to use:");
		lblChooseAHealing.setFont(new Font("Dialog", Font.BOLD, 17));
		lblChooseAHealing.setBounds(461, 388, 290, 18);
		HospitalFrame.getContentPane().add(lblChooseAHealing);
		
		JLabel noPotionsLabel = new JLabel("You have no Potions!");
		noPotionsLabel.setForeground(Color.RED);
		noPotionsLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		noPotionsLabel.setBounds(770, 430, 200, 30);
		HospitalFrame.getContentPane().add(noPotionsLabel);
		noPotionsLabel.setVisible(false);

		JComboBox<String> heroComboBox = new JComboBox<String>(team.getHeroNames());
		heroComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroIndex = heroComboBox.getSelectedIndex();
				fullHealthWarningLabel.setVisible(false);
			}
		});

		heroComboBox.setBounds(475, 290, 250, 30);
		HospitalFrame.getContentPane().add(heroComboBox);
		
		healingItemComboBox = new JComboBox<String>(getListHealingItemNames());
		healingItemComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				healingItemIndex = healingItemComboBox.getSelectedIndex();
			}
		});
		healingItemComboBox.setBounds(475, 430, 250, 30);
		HospitalFrame.getContentPane().add(healingItemComboBox);
		JButton healButton = new JButton("Heal");		
		healButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team.getHealingItems().size() == 0) {
					noPotionsLabel.setVisible(true); //Displayed if team doesn't have any potions.
				} else {
					
					healHero(team.getHealingItems().get(healingItemIndex), team.getHeroes().get(heroIndex));
				}
				
			}
		});
		healButton.setBounds(540, 490, 120, 40);
		HospitalFrame.getContentPane().add(healButton);
				
		JButton btnClose = new JButton("Back to Home Base!");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnClose.setBounds(980, 680, 180, 60);
		HospitalFrame.getContentPane().add(btnClose);
	
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		HospitalFrame.getContentPane().add(backgroundPic);

		
		timer = new Timer(1000, countdown);	//Calls the countdown ActionListener every 1000 milliseconds.
	}

}
