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


	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalGUI window = new HospitalGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void healHero(HealingItem healingItem, Hero hero) {
		
	}

	/**
	 * Create the application.
	 */
	public HospitalGUI() {
		initialize();
	}
	
	private static String[] getListHeroNames() {
		String[] heroNames;
		
		int numberHeroes = 3;
		heroNames = new String[numberHeroes];
		/*int numberHeroes = Team.getNumberHeroes();
		3;
		
		heroNames = new String[numberHeroes];
		for (int i = 0; i < numberHeroes; i++) {
			System.out.println("fghgfh");
			heroNames[i] = Team.heroes.get(i).getName();
			System.out.println(i);
			System.out.println(heroNames[i]);
		}*/
		heroNames[0] = "hero1";
		heroNames[1] = "hero2";
		heroNames[2] = "hero3";
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		JLabel lblChooseAHero = new JLabel("Choose a hero to heal");
		lblChooseAHero.setBounds(24, 47, 202, 21);
		frame.getContentPane().add(lblChooseAHero);
		
		JLabel lblHospital = new JLabel("Hospital");
		lblHospital.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHospital.setBounds(174, 12, 166, 23);
		frame.getContentPane().add(lblHospital);

		
		JLabel lblChooseAHealing = new JLabel("Choose a healing item to use:");
		lblChooseAHealing.setBounds(24, 147, 227, 15);
		frame.getContentPane().add(lblChooseAHealing);
		
		JLabel heroToHealLabel = new JLabel("New label");
		heroToHealLabel.setBounds(351, 83, 70, 15);
		frame.getContentPane().add(heroToHealLabel);
		
		JLabel healingItemLabel = new JLabel("New label");
		healingItemLabel.setBounds(337, 190, 70, 15);
		frame.getContentPane().add(healingItemLabel);

		JComboBox heroComboBox = new JComboBox(getListHeroNames());
		heroComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int heroIndex = heroComboBox.getSelectedIndex();
				System.out.println(heroIndex);
				
				heroToHealLabel.setText(Integer.toString(heroIndex));
				//Hero heroToHeal = Team.getHeroes().get(heroIndex);
			}
		});
		
		heroComboBox.setBounds(126, 80, 149, 21);
		frame.getContentPane().add(heroComboBox);
		
		JComboBox healingItemComboBox = new JComboBox(getListHealingItemNames());
		healingItemComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int healingItemIndex = healingItemComboBox.getSelectedIndex();
				System.out.println(healingItemIndex);
				healingItemLabel.setText(Integer.toString(healingItemIndex));
				
				//HealingItem healingItem = Team.getHealingItems().get(healingItemIndex);
			}
		});
		


		healingItemComboBox.setBounds(126, 187, 166, 21);
		frame.getContentPane().add(healingItemComboBox);
		
		JButton healButton = new JButton("Heal");
		healButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		healButton.setBounds(160, 220, 117, 25);
		frame.getContentPane().add(healButton);
		

		

		
		
	}
}
