import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JProgressBar;

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
	private JProgressBar progressBar = new JProgressBar();
	private JLabel currentlyHealingLabel = new JLabel("Healing...");
	private JLabel timeLeftLabel = new JLabel("Time left:");
	private JLabel countdownTimerLabel = new JLabel("");
	private JButton healButton = new JButton("Heal");
	private JLabel fullHealthWarningLabel = new JLabel("Hero is already full health!");
	private CityGUI cityGui;
	private Team team;
	private GameEnvironment gameEnvironment;

	/**
	 * Launch the application.
	 */
	public static void NewScreen(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalGUI window = new HospitalGUI(teamInput, gameEnvironmentInput, cityGuiInput);
					window.HospitalFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
				System.out.println("yoyo");
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
		HospitalFrame.setBounds(100, 100, 1000, 700);
		HospitalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HospitalFrame.getContentPane().setLayout(null);
		
		countdownTimerLabel.setBounds(721, 312, 70, 15);
		HospitalFrame.getContentPane().add(countdownTimerLabel);
		countdownTimerLabel.setVisible(false);
	
		currentlyHealingLabel.setBounds(651, 286, 70, 15);
		HospitalFrame.getContentPane().add(currentlyHealingLabel);
		currentlyHealingLabel.setVisible(false);
		
		fullHealthWarningLabel.setBounds(631, 364, 214, 15);
		HospitalFrame.getContentPane().add(fullHealthWarningLabel);
		fullHealthWarningLabel.setVisible(false);
		
		timeLeftLabel.setBounds(594, 312, 70, 15);
		HospitalFrame.getContentPane().add(timeLeftLabel);
		timeLeftLabel.setVisible(false);
		
		JLabel lblChooseAHero = new JLabel("Choose a hero to heal");
		lblChooseAHero.setBounds(75, 70, 202, 21);
		HospitalFrame.getContentPane().add(lblChooseAHero);
		
		JLabel lblHospital = new JLabel("Hospital");
		lblHospital.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHospital.setBounds(312, 12, 166, 23);
		HospitalFrame.getContentPane().add(lblHospital);

		JLabel lblChooseAHealing = new JLabel("Choose a healing item to use:");
		lblChooseAHealing.setBounds(70, 192, 227, 15);
		HospitalFrame.getContentPane().add(lblChooseAHealing);

		JComboBox heroComboBox = new JComboBox(team.getHeroNames());
		heroComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroIndex = heroComboBox.getSelectedIndex();
				fullHealthWarningLabel.setVisible(false);
			}
		});

		heroComboBox.setBounds(296, 122, 166, 21);
		HospitalFrame.getContentPane().add(heroComboBox);
		
		JComboBox healingItemComboBox = new JComboBox(getListHealingItemNames());
		healingItemComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				healingItemIndex = healingItemComboBox.getSelectedIndex();
			}
		});
		healingItemComboBox.setBounds(296, 230, 166, 21);
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
		healButton.setBounds(312, 281, 117, 25);
		HospitalFrame.getContentPane().add(healButton);
				
		JButton btnClose = new JButton("Back to Home Base!");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnClose.setBounds(34, 328, 209, 25);
		HospitalFrame.getContentPane().add(btnClose);
		
		
		progressBar.setBounds(51, 470, 246, 25);
		HospitalFrame.getContentPane().add(progressBar);
		
		timer = new Timer(1000, countdown);	
	}

}
