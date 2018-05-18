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
	private static int timeRemaining;
	private ActionListener countdown;
	private static Timer timer;
	private static ActionListener healProgressListener;
	private static Hero heroToHeal;
	private JProgressBar progressBar = new JProgressBar();

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalGUI window = new HospitalGUI();
					window.HospitalFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void healHero(HealingItem healingItem, Hero hero) {
		heroToHeal = hero;
		timeRemaining = healingItem.getApplicationTime();
		//healProgressListener;
		timer.start();
	}

	/**
	 * Create the application.
	 */
	public HospitalGUI() {
		initialize();
	}
	

	private static String[] getListHealingItemNames() {
		int numberHealingItems = Team.getHealingItems().size();
		String[] healingItemNames = new String[numberHealingItems];
		for (int i = 0; i < numberHealingItems; i++) {
			healingItemNames[i] = Team.getHealingItems().get(i).getName();
		}	
		return healingItemNames;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		countdown = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeRemaining--;
				if (timeRemaining <= 0) {
					timer.stop();
				}
			}
		};
		
		timer = new Timer(1000, countdown);
		
		healProgressListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroToHeal.heal(10);
				
			}
		};
		
		HospitalFrame = new JFrame();
		HospitalFrame.setTitle("Hospital");
		HospitalFrame.setBounds(100, 100, 1000, 700);
		HospitalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HospitalFrame.getContentPane().setLayout(null);
		

		
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

		JComboBox heroComboBox = new JComboBox(Team.getHeroNames());
		heroComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroIndex = heroComboBox.getSelectedIndex();
				System.out.println(heroIndex);
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
		
		JButton healButton = new JButton("Heal");
		healButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Team.getHealingItems().size() == 0) {
					System.out.println("You have no potions!");
				} else {
					healHero(Team.getHealingItems().get(healingItemIndex), Team.getHeroes().get(heroIndex));
				}
				
			}
		});
		healButton.setBounds(312, 281, 117, 25);
		HospitalFrame.getContentPane().add(healButton);
		
		
		
		JButton btnClose = new JButton("Back to Home Base!");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityGUI.CityScreen.setVisible(true);
				HospitalFrame.dispose();
			}
		});
		btnClose.setBounds(34, 328, 209, 25);
		HospitalFrame.getContentPane().add(btnClose);
		
		
		progressBar.setBounds(51, 470, 246, 25);
		HospitalFrame.getContentPane().add(progressBar);
		

		

		
		
	}
}
