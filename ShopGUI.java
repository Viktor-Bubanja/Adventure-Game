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
	private JFrame ShopGUIFrame;
	private JLabel notEnoughMoneyLabel = new JLabel("You don't have enough money!");
	private JLabel powerUpsLabel = new JLabel("");
	private JLabel healingItemsLabel = new JLabel("");
	private JLabel tooManyMapsLabel = new JLabel("You already have maps for all remaining cities!");
	private HealingItem smallPotion = new HealingItem(10, 10, 5, "Small Potion");
	private HealingItem quickPotion = new HealingItem(25, 10, 2, "Quick Potion");
	private HealingItem bigPotion = new HealingItem(40, 20, 10, "Big Potion");
	private PowerUp extraRoll = new PowerUp(30, "Extra Roll");
	private PowerUp extraGuess = new PowerUp(50, "Extra Guess");
	private PowerUp paperScissorsRockClue = new PowerUp(50, "Clue for Paper Scissors Rock");
	private Map map = new Map();
	private static List<PowerUp> powerUps = new ArrayList<PowerUp>();
	private static List<HealingItem> healingItems = new ArrayList<HealingItem>();
	private JLabel numberMapsLabel = new JLabel("");
	private CityGUI cityGui;
	private Team team;
	private GameEnvironment gameEnvironment;
	/**
	 * Launch the application.
	 */
	public ShopGUI(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		powerUps.add(paperScissorsRockClue);
		powerUps.add(extraGuess);
		powerUps.add(extraRoll);
		healingItems.add(smallPotion);
		healingItems.add(quickPotion);
		healingItems.add(bigPotion);
		cityGui = cityGuiInput;
		
		initialize();
	}
	
	public void makeVisible() {
		this.ShopGUIFrame.setVisible(true);
	}
	
	public List<PowerUp> getPowerUpList() {
		return powerUps;
	}
	public List<HealingItem> getHealingItemList() {
		return healingItems;
	}
	
	public HealingItem getSmallPotion() {
		return smallPotion;
	}

	public HealingItem getQuickPotion() {
		return quickPotion;
	}

	public HealingItem getBigPotion() {
		return bigPotion;
	}

	public PowerUp getExtraRoll() {
		return extraRoll;
	}

	public PowerUp getExtraGuess() {
		return extraGuess;
	}
	public PowerUp getpaperScissorsRockClue() {
		return paperScissorsRockClue;
	}

	/**
	 * Create the application.
	 */

	
	public void buyHealingItem(HealingItem healingItem) {
		int cost = healingItem.getCost();
		if (team.getMoney() < cost) {
			notEnoughMoneyLabel.setVisible(true);
		} else {
			
			notEnoughMoneyLabel.setVisible(false);
			team.addHealingItem(healingItem);
			team.decreaseMoneyBy(cost);	
			updateHealingItemsLabel();
		}
		
	}
	
	public void buyMap(Map map) {
		int cost = map.getCost();
		if (team.getMoney() < cost) {
			notEnoughMoneyLabel.setVisible(true);
		} else if (team.getNumberMaps() < gameEnvironment.getNumberCities() - gameEnvironment.getCurrentCityIndex() + 1){
			notEnoughMoneyLabel.setVisible(false);
			team.addMap();
			team.decreaseMoneyBy(cost);	
			updateNumberMapsLabel();
			if (!cityGui.getUsedMap()) {
				cityGui.showMapButtonAndLabel();
			}
		} else {
			tooManyMapsLabel.setVisible(true);
		}
		
	}
	
	private void buyPowerUp(PowerUp powerUp) {
		
		int cost = powerUp.getCost();
		if (team.getMoney() < cost) {
			notEnoughMoneyLabel.setVisible(true);
		} else {
			notEnoughMoneyLabel.setVisible(false);
			team.decreaseMoneyBy(cost);	
			team.addPowerUp(powerUp);
			updatePowerUpsLabel();
		}
	}
	
	public void updateHealingItemsLabel() {
		String[] healingItemNames = team.getHealingItemNames();
		String healingItemsString = "";
		for (int i = 0; i < healingItemNames.length; i++) {
			healingItemsString += healingItemNames[i] + " ";
		}
		healingItemsLabel.setText(healingItemsString);
	}
	public void updatePowerUpsLabel() {
		String[] powerUpNames = team.getPowerUpNames();
		String powerUpsString = "";
		for (int i = 0; i < powerUpNames.length; i++) {
			powerUpsString += powerUpNames[i] + " ";
		}
		powerUpsLabel.setText(powerUpsString);
	}
	public void updateNumberMapsLabel() {
		int numberMaps = team.getNumberMaps();
		numberMapsLabel.setText(Integer.toString(numberMaps));
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ShopGUIFrame = new JFrame();
		ShopGUIFrame.setBounds(100, 100, 1000, 700);
		ShopGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ShopGUIFrame.getContentPane().setLayout(null);
		if (team.teamHasDiplomat()) {
			smallPotion.reduceCost(5);
			quickPotion.reduceCost(10);
			bigPotion.reduceCost(15);
			extraRoll.reduceCost(10);
			extraGuess.reduceCost(15);
			paperScissorsRockClue.reduceCost(15);
		}
	
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
		
		JLabel smallPotionCostLabel = new JLabel("$" + Integer.toString(smallPotion.getCost()));
		smallPotionCostLabel.setBounds(99, 280, 70, 15);
		ShopGUIFrame.getContentPane().add(smallPotionCostLabel);
		
		JLabel quickPotionCostLabel = new JLabel("$" + Integer.toString(quickPotion.getCost()));
		quickPotionCostLabel.setBounds(243, 280, 70, 15);
		ShopGUIFrame.getContentPane().add(quickPotionCostLabel);
		
		JLabel bigPotionCostLabel = new JLabel("$" + Integer.toString(bigPotion.getCost()));
		bigPotionCostLabel.setBounds(403, 280, 70, 15);
		ShopGUIFrame.getContentPane().add(bigPotionCostLabel);
		
		JLabel mapCostLabel = new JLabel("$" + Integer.toString(map.getCost()));
		mapCostLabel.setBounds(580, 280, 70, 15);
		ShopGUIFrame.getContentPane().add(mapCostLabel);
		
		JLabel moneyLeftLabel = new JLabel("Money left: " + team.getMoney());
		moneyLeftLabel.setBounds(754, 434, 172, 15);
		ShopGUIFrame.getContentPane().add(moneyLeftLabel);
		
		JLabel lblShop = new JLabel("Shop");
		lblShop.setFont(new Font("Dialog", Font.BOLD, 17));
		lblShop.setBounds(47, 12, 117, 36);
		ShopGUIFrame.getContentPane().add(lblShop);
		
		
		notEnoughMoneyLabel.setBounds(274, 515, 310, 25);
		ShopGUIFrame.getContentPane().add(notEnoughMoneyLabel);
		notEnoughMoneyLabel.setVisible(false);
		
		JButton buySmallPotionButton = new JButton("Buy");
		buySmallPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(smallPotion);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buySmallPotionButton.setBounds(52, 307, 117, 25);
		ShopGUIFrame.getContentPane().add(buySmallPotionButton);
		
		JButton buyQuickPotionButton = new JButton("Buy");
		buyQuickPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(quickPotion);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buyQuickPotionButton.setBounds(215, 307, 117, 25);
		ShopGUIFrame.getContentPane().add(buyQuickPotionButton);
		
		JButton buyBigPotionButton = new JButton("Buy");
		buyBigPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(bigPotion);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buyBigPotionButton.setBounds(356, 307, 117, 25);
		ShopGUIFrame.getContentPane().add(buyBigPotionButton);
		
		JButton buyMapButton = new JButton("Buy");
		buyMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMap(map);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buyMapButton.setBounds(544, 317, 117, 25);
		ShopGUIFrame.getContentPane().add(buyMapButton);
		
		JButton btnBackToHome = new JButton("Back to Home Base!");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cityGui.makeCityVisible();
				ShopGUIFrame.dispose();
			}
		});
		btnBackToHome.setBounds(597, 531, 189, 25);
		ShopGUIFrame.getContentPane().add(btnBackToHome);
		
		JButton buyExtraRollButton = new JButton("Buy");
		buyExtraRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyPowerUp(extraRoll);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		
		
		JButton buyExtraGuessButton = new JButton("Buy");
		buyExtraGuessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyPowerUp(extraGuess);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		
		JButton buyPaperScissorsRockClueButton = new JButton("Buy");
		buyPaperScissorsRockClueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyPowerUp(paperScissorsRockClue);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buyPaperScissorsRockClueButton.setBounds(432, 140, 101, 25);
		ShopGUIFrame.getContentPane().add(buyPaperScissorsRockClueButton);
		buyExtraGuessButton.setBounds(221, 140, 101, 25);
		ShopGUIFrame.getContentPane().add(buyExtraGuessButton);
		
		JLabel lblNewLabel = new JLabel("Extra Roll");
		lblNewLabel.setBounds(47, 71, 101, 25);
		ShopGUIFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblExtraGuess = new JLabel("Extra Guess");
		lblExtraGuess.setBounds(210, 73, 108, 20);
		ShopGUIFrame.getContentPane().add(lblExtraGuess);
		
		JLabel lblClueForPaper = new JLabel("Clue for Paper Scissors Rock");
		lblClueForPaper.setBounds(380, 76, 241, 15);
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
		
		
		tooManyMapsLabel.setBounds(503, 365, 344, 25);
		ShopGUIFrame.getContentPane().add(tooManyMapsLabel);
		
		JLabel extraRollCostLabel = new JLabel("$" + Integer.toString(extraRoll.getCost()));
		extraRollCostLabel.setBounds(52, 108, 70, 15);
		ShopGUIFrame.getContentPane().add(extraRollCostLabel);
		
		JLabel extraGuessCostLabel = new JLabel("$" + Integer.toString(extraGuess.getCost()));
		extraGuessCostLabel.setBounds(243, 113, 70, 15);
		ShopGUIFrame.getContentPane().add(extraGuessCostLabel);
		
		JLabel clueCostLabel = new JLabel("$" + Integer.toString(paperScissorsRockClue.getCost()));
		clueCostLabel.setBounds(450, 103, 70, 15);
		ShopGUIFrame.getContentPane().add(clueCostLabel);
		
		JLabel lblMaps = new JLabel("Maps:");
		lblMaps.setBounds(118, 471, 70, 15);
		ShopGUIFrame.getContentPane().add(lblMaps);
		
		
		numberMapsLabel.setBounds(243, 471, 70, 15);
		ShopGUIFrame.getContentPane().add(numberMapsLabel);
		tooManyMapsLabel.setVisible(false);
		
		updatePowerUpsLabel();
		updateHealingItemsLabel();
		updateNumberMapsLabel();	


	}
}
