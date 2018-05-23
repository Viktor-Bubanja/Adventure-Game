import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.SwingConstants;

public class HospitalGUI {

	private int healingItemIndex;
	private int heroIndex;
	private JFrame HospitalFrame;
	private int timeRemaining = 1;
	private ActionListener countdown;
	private Timer timer;
	private ActionListener healProgressListener;
	private Hero heroToHeal;
	private HealingItem currentHealingItem;
	private JLabel currentlyHealingLabel = new JLabel("Healing...");
	private JLabel timeLeftLabel = new JLabel("Time left:");
	private JLabel countdownTimerLabel = new JLabel("");
	private JButton healButton = new JButton("Heal");
	private JLabel fullHealthWarningLabel = new JLabel("Hero is already full health!");
	private CityGUI cityGui;
	private Team team;
	private GameEnvironment gameEnvironment;
	private JComboBox healingItemComboBox;

	/**
	 * Launch the application.
	 */
	
	public void makeVisible() {
		this.HospitalFrame.setVisible(true);
	}
	
	public HospitalGUI(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		cityGui = cityGuiInput;
		initialize();
	}
	private void finishedWindow() {
		cityGui.makeCityVisible();
		HospitalFrame.dispose();
	}
	
	private void healHero(HealingItem healingItem, Hero hero) {
		heroToHeal = hero;
		currentHealingItem = healingItem;
		if (heroToHeal.getHealth() < heroToHeal.getMaxHealth()) {
			team.removeHealingItem(healingItem);
			
			removeHealingItemFromComboBox(healingItem.getName());
			countdownTimerLabel.setVisible(true);
			timeRemaining = healingItem.getApplicationTime() + 1;
			currentlyHealingLabel.setVisible(true);
			timeLeftLabel.setVisible(true);
			healButton.setVisible(false);
			timer.start();
			
		} else {
			fullHealthWarningLabel.setVisible(true);
		}
	}
	private void removeHealingItemFromComboBox(String healingItemName) {
		healingItemComboBox.removeItem(healingItemName);
		if (team.getHealingItems().size() > 0) {
			healingItemComboBox.setSelectedIndex(0);
		}
	}

	/**
	 * Create the application.
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
		ActionListener countdown = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeRemaining--;
				countdownTimerLabel.setText(Integer.toString(timeRemaining));
				
				if (timeRemaining <= 0) {
					timer.stop();
					healButton.setVisible(true);
					currentlyHealingLabel.setVisible(false);
					countdownTimerLabel.setText("Done");
					heroToHeal.heal(currentHealingItem.getHealingAmount());
				}
			}
		};
		
		HospitalFrame = new JFrame();
		HospitalFrame.setTitle("Hospital");
		HospitalFrame.setBounds(100, 100, 1200, 800);
		HospitalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HospitalFrame.getContentPane().setLayout(null);
		countdownTimerLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		
		countdownTimerLabel.setBounds(973, 288, 105, 15);
		HospitalFrame.getContentPane().add(countdownTimerLabel);
		countdownTimerLabel.setVisible(false);
		currentlyHealingLabel.setFont(new Font("Dialog", Font.BOLD, 17));
	
		currentlyHealingLabel.setBounds(900, 239, 113, 15);
		HospitalFrame.getContentPane().add(currentlyHealingLabel);
		currentlyHealingLabel.setVisible(false);
		fullHealthWarningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		fullHealthWarningLabel.setBounds(500, 557, 200, 15);
		HospitalFrame.getContentPane().add(fullHealthWarningLabel);
		fullHealthWarningLabel.setVisible(false);
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
		lblHospital.setBounds(500, 20, 200, 48);
		HospitalFrame.getContentPane().add(lblHospital);

		JLabel lblChooseAHealing = new JLabel("Choose a healing item to use:");
		lblChooseAHealing.setFont(new Font("Dialog", Font.BOLD, 17));
		lblChooseAHealing.setBounds(461, 388, 290, 18);
		HospitalFrame.getContentPane().add(lblChooseAHealing);

		JComboBox heroComboBox = new JComboBox(team.getHeroNames());
		heroComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroIndex = heroComboBox.getSelectedIndex();
				fullHealthWarningLabel.setVisible(false);
			}
		});

		heroComboBox.setBounds(475, 289, 250, 30);
		HospitalFrame.getContentPane().add(heroComboBox);
		
		healingItemComboBox = new JComboBox(getListHealingItemNames());
		healingItemComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				healingItemIndex = healingItemComboBox.getSelectedIndex();
			}
		});
		healingItemComboBox.setBounds(475, 430, 250, 30);
		HospitalFrame.getContentPane().add(healingItemComboBox);
				
		healButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team.getHealingItems().size() == 0) {
					System.out.println("You have no potions!");
				} else {
					timer.start();
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

		
		timer = new Timer(1000, countdown);	
	}

}
