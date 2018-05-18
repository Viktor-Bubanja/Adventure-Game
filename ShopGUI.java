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
	private static JLabel notEnoughMoneyLabel = new JLabel("You don't have enough money!");
	private static JLabel powerUpsLabel = new JLabel("");
	private static JLabel healingItemsLabel = new JLabel("");

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
	
	public static void buyHealingItem(HealingItem healingItem) {
		double cost = healingItem.getCost();
		if (Team.getMoney() < cost) {
			notEnoughMoneyLabel.setVisible(true);
		} else {
			Team.addHealingItem(healingItem);
			Team.decreaseMoneyBy(cost);	
			updateHealingItemsLabel();
		}
		updateHealingItemsLabel();
		
	}
	
	public static void buyMap(Map map) {
		double cost = map.getCost();
		if (Team.getMoney() < cost) {
			notEnoughMoneyLabel.setVisible(true);
		} else {
			Team.addMap();
			Team.decreaseMoneyBy(cost);	
			updatePowerUpsLabel();
		}
		
	}
	
	private static void buyPowerUp(PowerUp powerUp) {
		
		double cost = powerUp.getCost();
		if (Team.getMoney() < cost) {
			notEnoughMoneyLabel.setVisible(true);
		} else {
			Team.decreaseMoneyBy(cost);	
			Team.addPowerUp(powerUp);
		}
		updatePowerUpsLabel();
	}
	
	private static void updateHealingItemsLabel() {
		String[] healingItemNames = Team.getHealingItemNames();
		String healingItemsString = "";
		for (int i = 0; i < healingItemNames.length; i++) {
			healingItemsString += healingItemNames[i] + " ";
		}
		healingItemsLabel.setText(healingItemsString);
	}
	private static void updatePowerUpsLabel() {
		String[] powerUpNames = Team.getPowerUpNames();
		String powerUpsString = "";
		for (int i = 0; i < powerUpNames.length; i++) {
			powerUpsString += powerUpNames[i] + " ";
		}
		powerUpsLabel.setText(powerUpsString);
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ShopGUIFrame = new JFrame();
		ShopGUIFrame.setBounds(100, 100, 1000, 700);
		ShopGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ShopGUIFrame.getContentPane().setLayout(null);
		
		Map map = new Map();
		HealingItem smallPotion = new HealingItem(10.00, 10, 5000, "Small Potion");
		HealingItem quickPotion = new HealingItem(25.00, 10, 2000, "Quick Potion");
		HealingItem bigPotion = new HealingItem(40.00, 20, 10000, "Big Potion");
		PowerUp extraRoll = new PowerUp(30.00, "Extra Roll");
		PowerUp extraGuess = new PowerUp(50.00, "Extra Guess");
		PowerUp paperScissorsRockClue = new PowerUp(50.00, "Clue for Paper Scissors Rock");
		
		List<PowerUp> powerUps = new ArrayList<PowerUp>();
		powerUps.add(paperScissorsRockClue);
		powerUps.add(extraGuess);
		powerUps.add(extraRoll);
		
		List<HealingItem> healingItems = new ArrayList<HealingItem>();
		healingItems.add(smallPotion);
		healingItems.add(quickPotion);
		healingItems.add(bigPotion);
		
		
		healingItemsLabel.setBounds(249, 407, 353, 15);
		ShopGUIFrame.getContentPane().add(healingItemsLabel);
			
		JLabel lblSmallPotion = new JLabel("Small Potion");
		lblSmallPotion.setBounds(47, 238, 122, 15);
		ShopGUIFrame.getContentPane().add(lblSmallPotion);
		
		JLabel lblQuickPotion = new JLabel("Quick Potion");
		lblQuickPotion.setBounds(210, 238, 122, 15);
		ShopGUIFrame.getContentPane().add(lblQuickPotion);
		
		JLabel lblBigPotion = new JLabel("Big Potion");
		lblBigPotion.setBounds(380, 238, 122, 15);
		ShopGUIFrame.getContentPane().add(lblBigPotion);
		
		JLabel lblMap = new JLabel("Map");
		lblMap.setBounds(580, 238, 122, 15);
		ShopGUIFrame.getContentPane().add(lblMap);
		
		JLabel label = new JLabel("$10");
		label.setBounds(99, 280, 70, 15);
		ShopGUIFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("$25");
		label_1.setBounds(243, 280, 70, 15);
		ShopGUIFrame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("$40");
		label_2.setBounds(403, 280, 70, 15);
		ShopGUIFrame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("$20");
		label_3.setBounds(580, 280, 70, 15);
		ShopGUIFrame.getContentPane().add(label_3);
		
		JLabel moneyLeftLabel = new JLabel("Money left: " + Team.getMoney());
		moneyLeftLabel.setBounds(754, 434, 172, 15);
		ShopGUIFrame.getContentPane().add(moneyLeftLabel);
		
		JLabel lblShop = new JLabel("Shop");
		lblShop.setFont(new Font("Dialog", Font.BOLD, 17));
		lblShop.setBounds(47, 12, 117, 36);
		ShopGUIFrame.getContentPane().add(lblShop);
		
		
		notEnoughMoneyLabel.setBounds(277, 489, 310, 25);
		ShopGUIFrame.getContentPane().add(notEnoughMoneyLabel);
		notEnoughMoneyLabel.setVisible(false);
		
		JButton buySmallPotionButton = new JButton("Buy");
		buySmallPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(smallPotion);
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buySmallPotionButton.setBounds(52, 307, 117, 25);
		ShopGUIFrame.getContentPane().add(buySmallPotionButton);
		
		JButton buyQuickPotionButton = new JButton("Buy");
		buyQuickPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(quickPotion);
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buyQuickPotionButton.setBounds(215, 307, 117, 25);
		ShopGUIFrame.getContentPane().add(buyQuickPotionButton);
		
		JButton buyBigPotionButton = new JButton("Buy");
		buyBigPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(bigPotion);
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buyBigPotionButton.setBounds(356, 307, 117, 25);
		ShopGUIFrame.getContentPane().add(buyBigPotionButton);
		
		JButton buyMapButton = new JButton("Buy");
		buyMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMap(map);
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buyMapButton.setBounds(544, 317, 117, 25);
		ShopGUIFrame.getContentPane().add(buyMapButton);
		
		JButton btnBackToHome = new JButton("Back to Home Base!");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityGUI.CityScreen.setVisible(true);
				ShopGUIFrame.dispose();
			}
		});
		btnBackToHome.setBounds(597, 531, 189, 25);
		ShopGUIFrame.getContentPane().add(btnBackToHome);
		
		JButton buyExtraRollButton = new JButton("Buy");
		buyExtraRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyPowerUp(extraRoll);
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		
		
		JButton buyExtraGuessButton = new JButton("Buy");
		buyExtraGuessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyPowerUp(extraGuess);
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		
		JButton buyPaperScissorsRockClueButton = new JButton("Buy");
		buyPaperScissorsRockClueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyPowerUp(paperScissorsRockClue);
				moneyLeftLabel.setText("Money left: " + Team.getMoney());
			}
		});
		buyPaperScissorsRockClueButton.setBounds(432, 140, 101, 25);
		ShopGUIFrame.getContentPane().add(buyPaperScissorsRockClueButton);
		buyExtraGuessButton.setBounds(221, 140, 101, 25);
		ShopGUIFrame.getContentPane().add(buyExtraGuessButton);
		
		JLabel lblNewLabel = new JLabel("Extra Roll");
		lblNewLabel.setBounds(52, 103, 101, 25);
		ShopGUIFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblExtraGuess = new JLabel("Extra Guess");
		lblExtraGuess.setBounds(221, 108, 108, 20);
		ShopGUIFrame.getContentPane().add(lblExtraGuess);
		
		JLabel lblClueForPaper = new JLabel("Clue for Paper Scissors Rock");
		lblClueForPaper.setBounds(380, 113, 241, 15);
		ShopGUIFrame.getContentPane().add(lblClueForPaper);
		

		buyExtraRollButton.setBounds(47, 140, 101, 25);
		ShopGUIFrame.getContentPane().add(buyExtraRollButton);
		
		JLabel lblInventory = new JLabel("Inventory:");
		lblInventory.setBounds(59, 370, 110, 15);
		ShopGUIFrame.getContentPane().add(lblInventory);
		
		JLabel lblHealingItems = new JLabel("Healing Items:");
		lblHealingItems.setBounds(118, 397, 122, 25);
		ShopGUIFrame.getContentPane().add(lblHealingItems);
		
		JLabel lblPowerUps = new JLabel("Power Ups:");
		lblPowerUps.setBounds(118, 444, 108, 15);
		ShopGUIFrame.getContentPane().add(lblPowerUps);
		
		
		powerUpsLabel.setBounds(259, 444, 413, 15);
		ShopGUIFrame.getContentPane().add(powerUpsLabel);
		
		
		

		

		


	}
}
