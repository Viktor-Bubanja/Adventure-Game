import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class ShopGUI {
	private HealingItem smallPotion;
	private JFrame ShopGUIFrame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopGUI window = new ShopGUI();
					window.ShopGUIFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public ShopGUI() {
		initialize();
	}
	
	public static void buyHealingItem(HealingItem healingItem, double cost) {
		Team.addHealingItem(healingItem);
		Team.decreaseMoneyBy(cost);	
	}
	
	public static void buyMap(double cost) {
		Team.addMap();
		Team.decreaseMoneyBy(cost);	
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ShopGUIFrame = new JFrame();
		ShopGUIFrame.setBounds(100, 100, 724, 462);
		ShopGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ShopGUIFrame.getContentPane().setLayout(null);
		
		Map map = new Map();
		HealingItem smallPotion = new HealingItem(10.00, 10, 20, "Small Potion");
		HealingItem quickPotion = new HealingItem(25.00, 10, 5, "Quick Potion");
		HealingItem bigPotion = new HealingItem(40.00, 20, 10, "Big Potion");
		List<HealingItem> healingItems = new ArrayList<HealingItem>();
		healingItems.add(smallPotion);
		healingItems.add(quickPotion);
		healingItems.add(bigPotion);
			
		JLabel lblSmallPotion = new JLabel("Small Potion");
		lblSmallPotion.setBounds(47, 193, 122, 15);
		ShopGUIFrame.getContentPane().add(lblSmallPotion);
		
		JLabel lblQuickPotion = new JLabel("Quick Potion");
		lblQuickPotion.setBounds(207, 193, 122, 15);
		ShopGUIFrame.getContentPane().add(lblQuickPotion);
		
		JLabel lblBigPotion = new JLabel("Big Potion");
		lblBigPotion.setBounds(380, 193, 122, 15);
		ShopGUIFrame.getContentPane().add(lblBigPotion);
		
		JLabel lblMap = new JLabel("Map");
		lblMap.setBounds(588, 193, 122, 15);
		ShopGUIFrame.getContentPane().add(lblMap);
		
		JLabel label = new JLabel("$10");
		label.setBounds(67, 229, 70, 15);
		ShopGUIFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("$25");
		label_1.setBounds(243, 229, 70, 15);
		ShopGUIFrame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("$40");
		label_2.setBounds(403, 229, 70, 15);
		ShopGUIFrame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("$20");
		label_3.setBounds(586, 229, 70, 15);
		ShopGUIFrame.getContentPane().add(label_3);
		
		JLabel moneyLeftLabel = new JLabel("Money left: " + Team.getMoney());
		moneyLeftLabel.setBounds(272, 352, 172, 15);
		ShopGUIFrame.getContentPane().add(moneyLeftLabel);
		
		JLabel lblShop = new JLabel("Shop");
		lblShop.setFont(new Font("Dialog", Font.BOLD, 17));
		lblShop.setBounds(293, 50, 117, 36);
		ShopGUIFrame.getContentPane().add(lblShop);
		
		JButton buySmallPotionButton = new JButton("Buy");
		buySmallPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(smallPotion, smallPotion.getCost());
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buySmallPotionButton.setBounds(47, 268, 117, 25);
		ShopGUIFrame.getContentPane().add(buySmallPotionButton);
		
		JButton buyQuickPotionButton = new JButton("Buy");
		buyQuickPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(quickPotion, quickPotion.getCost());
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buyQuickPotionButton.setBounds(196, 268, 117, 25);
		ShopGUIFrame.getContentPane().add(buyQuickPotionButton);
		
		JButton buyBigPotionButton = new JButton("Buy");
		buyBigPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(bigPotion, bigPotion.getCost());
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buyBigPotionButton.setBounds(363, 268, 117, 25);
		ShopGUIFrame.getContentPane().add(buyBigPotionButton);
		
		JButton buyMapButton = new JButton("Buy");
		buyMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMap(map.getCost());
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buyMapButton.setBounds(539, 268, 117, 25);
		ShopGUIFrame.getContentPane().add(buyMapButton);
		
		JButton btnBackToHome = new JButton("Back to Home Base!");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityGUI.CityScreen.setVisible(true);
				ShopGUIFrame.dispose();
			}
		});
		btnBackToHome.setBounds(47, 384, 189, 25);
		ShopGUIFrame.getContentPane().add(btnBackToHome);

	}
}
