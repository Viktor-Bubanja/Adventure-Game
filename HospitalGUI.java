import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HospitalGUI {

	private int healingItemIndex;
	private int heroIndex;
	private JFrame HospitalFrame;

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
		hero.heal(healingItem);
	}

	/**
	 * Create the application.
	 */
	public HospitalGUI() {
		initialize();
	}
	
	private static String[] getListHeroNames() {
		String[] heroNames;
		
		int numberHeroes = Team.getNumberHeroes();
		heroNames = new String[numberHeroes];
		
		heroNames = new String[numberHeroes];
		for (int i = 0; i < numberHeroes; i++) {
			System.out.println("fghgfh");
			heroNames[i] = Team.getHeroes().get(i).getName();
			System.out.println(i);
			System.out.println(heroNames[i]);
		}
		//heroNames[0] = "hero1";
		//heroNames[1] = "hero2";
		//heroNames[2] = "hero3";
		return heroNames;
	}
	
	private static String[] getListHealingItemNames() {
		String[] healingItemNames;
		int numberHealingItems = 3;
		healingItemNames = new String[3];
		HealingItem smallPotion = new HealingItem(10.00, 1, 20, "Small Potion");
		HealingItem quickPotion = new HealingItem(25.00, 1, 5, "Quick Potion");
		HealingItem bigPotion = new HealingItem(40.00, 2, 10, "Big Potion");
		healingItemNames[0] = smallPotion.getName();
		healingItemNames[1] = quickPotion.getName();
		healingItemNames[2] = bigPotion.getName();
		return healingItemNames;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		HospitalFrame = new JFrame();
		HospitalFrame.setTitle("Hospital");
		HospitalFrame.setBounds(100, 100, 450, 300);
		HospitalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HospitalFrame.getContentPane().setLayout(null);
		

		
		JLabel lblChooseAHero = new JLabel("Choose a hero to heal");
		lblChooseAHero.setBounds(24, 47, 202, 21);
		HospitalFrame.getContentPane().add(lblChooseAHero);
		
		JLabel lblHospital = new JLabel("Hospital");
		lblHospital.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHospital.setBounds(174, 12, 166, 23);
		HospitalFrame.getContentPane().add(lblHospital);

		
		JLabel lblChooseAHealing = new JLabel("Choose a healing item to use:");
		lblChooseAHealing.setBounds(24, 147, 227, 15);
		HospitalFrame.getContentPane().add(lblChooseAHealing);

		JComboBox heroComboBox = new JComboBox(getListHeroNames());
		heroIndex = heroComboBox.getSelectedIndex();
		heroComboBox.setBounds(126, 80, 149, 21);
		HospitalFrame.getContentPane().add(heroComboBox);
		
		JComboBox healingItemComboBox = new JComboBox(getListHealingItemNames());
		healingItemIndex = healingItemComboBox.getSelectedIndex();
		healingItemComboBox.setBounds(126, 187, 166, 21);
		HospitalFrame.getContentPane().add(healingItemComboBox);
		
		JButton healButton = new JButton("Heal");
		healButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				healHero(Team.getHealingItems().get(healingItemIndex), Team.getHeroes().get(heroIndex));
				
			
				
			}
		});
		healButton.setBounds(160, 220, 117, 25);
		HospitalFrame.getContentPane().add(healButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalFrame.dispose();
			}
		});
		btnClose.setBounds(0, 220, 117, 25);
		HospitalFrame.getContentPane().add(btnClose);
		

		

		
		
	}
}
