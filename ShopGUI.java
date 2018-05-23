import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ShopGUI {
	private JFrame shopGUIFrame;
	private JLabel notEnoughMoneyLabel = new JLabel("You don't have enough money!");
	private JLabel powerUpsLabel = new JLabel("");
	private JLabel healingItemsLabel = new JLabel("");
	private JLabel tooManyMapsLabel = new JLabel("You already have maps for all remaining cities!");
	private HealingItem smallPotion = new HealingItem(10, 0.25, 5, "Small Potion");
	private HealingItem quickPotion = new HealingItem(25, 0.25, 2, "Quick Potion");
	private HealingItem bigPotion = new HealingItem(40, 0.5, 10, "Big Potion");
	private PowerUp extraRoll = new PowerUp(40, "Extra Roll");
	private PowerUp extraGuess = new PowerUp(40, "Extra Guess");
	private PowerUp paperScissorsRockClue = new PowerUp(60, "Clue for Paper Scissors Rock");
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
		this.shopGUIFrame.setVisible(true);
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
		} else if (team.getNumberMaps() < gameEnvironment.getNumberCities() - gameEnvironment.getCurrentCityIndex()){
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
		
		shopGUIFrame = new JFrame();
		shopGUIFrame.setBounds(100, 100, 1200, 800);
		shopGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shopGUIFrame.getContentPane().setLayout(null);
		if (team.teamHasDiplomat()) {
			smallPotion.reduceCost(5);
			quickPotion.reduceCost(10);
			bigPotion.reduceCost(15);
			extraRoll.reduceCost(10);
			extraGuess.reduceCost(15);
			paperScissorsRockClue.reduceCost(15);
		}
	
		healingItemsLabel.setBounds(820, 360, 353, 20);
		shopGUIFrame.getContentPane().add(healingItemsLabel);
			
		JLabel lblSmallPotion = new JLabel("Small Potion");
		lblSmallPotion.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSmallPotion.setBounds(48, 360, 122, 15);
		shopGUIFrame.getContentPane().add(lblSmallPotion);
		
		JLabel lblQuickPotion = new JLabel("Quick Potion");
		lblQuickPotion.setFont(new Font("Dialog", Font.BOLD, 17));
		lblQuickPotion.setBounds(250, 360, 122, 15);
		shopGUIFrame.getContentPane().add(lblQuickPotion);
		
		JLabel lblBigPotion = new JLabel("Big Potion");
		lblBigPotion.setFont(new Font("Dialog", Font.BOLD, 17));
		lblBigPotion.setBounds(461, 360, 122, 15);
		shopGUIFrame.getContentPane().add(lblBigPotion);
		
		JLabel lblMap = new JLabel("Map");
		lblMap.setFont(new Font("Dialog", Font.BOLD, 17));
		lblMap.setBounds(85, 560, 61, 15);
		shopGUIFrame.getContentPane().add(lblMap);
		
		JLabel smallPotionCostLabel = new JLabel("$" + Integer.toString(smallPotion.getCost()));
		smallPotionCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		smallPotionCostLabel.setBounds(85, 390, 70, 15);
		shopGUIFrame.getContentPane().add(smallPotionCostLabel);
		
		JLabel quickPotionCostLabel = new JLabel("$" + Integer.toString(quickPotion.getCost()));
		quickPotionCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		quickPotionCostLabel.setBounds(285, 390, 70, 15);
		shopGUIFrame.getContentPane().add(quickPotionCostLabel);
		
		JLabel bigPotionCostLabel = new JLabel("$" + Integer.toString(bigPotion.getCost()));
		bigPotionCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		bigPotionCostLabel.setBounds(485, 390, 70, 15);
		shopGUIFrame.getContentPane().add(bigPotionCostLabel);
		
		JLabel mapCostLabel = new JLabel("$" + Integer.toString(map.getCost()));
		mapCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		mapCostLabel.setBounds(85, 590, 70, 15);
		shopGUIFrame.getContentPane().add(mapCostLabel);
		
		JLabel moneyLeftLabel = new JLabel("Money left: " + team.getMoney());
		moneyLeftLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		moneyLeftLabel.setBounds(700, 522, 172, 15);
		shopGUIFrame.getContentPane().add(moneyLeftLabel);
		
		JLabel lblShop = new JLabel("Shop");
		lblShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblShop.setFont(new Font("Dialog", Font.BOLD, 40));
		lblShop.setBounds(540, 12, 120, 73);
		shopGUIFrame.getContentPane().add(lblShop);
		notEnoughMoneyLabel.setForeground(new Color(255, 0, 0));
		notEnoughMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notEnoughMoneyLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		
		
		notEnoughMoneyLabel.setBounds(400, 697, 400, 25);
		shopGUIFrame.getContentPane().add(notEnoughMoneyLabel);
		notEnoughMoneyLabel.setVisible(false);
		
		JButton buySmallPotionButton = new JButton("Buy");
		buySmallPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(smallPotion);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buySmallPotionButton.setBounds(50, 420, 120, 40);
		shopGUIFrame.getContentPane().add(buySmallPotionButton);
		
		JButton buyQuickPotionButton = new JButton("Buy");
		buyQuickPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(quickPotion);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buyQuickPotionButton.setBounds(250, 420, 120, 40);
		shopGUIFrame.getContentPane().add(buyQuickPotionButton);
		
		JButton buyBigPotionButton = new JButton("Buy");
		buyBigPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(bigPotion);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buyBigPotionButton.setBounds(450, 420, 120, 40);
		shopGUIFrame.getContentPane().add(buyBigPotionButton);
		
		JButton buyMapButton = new JButton("Buy");
		buyMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyMap(map);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buyMapButton.setBounds(50, 620, 120, 40);
		shopGUIFrame.getContentPane().add(buyMapButton);
		
		JButton btnBackToHome = new JButton("Back to Home Base!");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cityGui.makeCityVisible();
				shopGUIFrame.dispose();
			}
		});
		btnBackToHome.setBounds(980, 680, 180, 60);
		shopGUIFrame.getContentPane().add(btnBackToHome);
		
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
		buyPaperScissorsRockClueButton.setBounds(450, 220, 120, 40);
		shopGUIFrame.getContentPane().add(buyPaperScissorsRockClueButton);
		buyExtraGuessButton.setBounds(250, 220, 120, 40);
		shopGUIFrame.getContentPane().add(buyExtraGuessButton);
		
		JLabel lblNewLabel = new JLabel("Extra Roll");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setBounds(62, 160, 101, 25);
		shopGUIFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblExtraGuess = new JLabel("Extra Guess");
		lblExtraGuess.setForeground(new Color(0, 0, 0));
		lblExtraGuess.setFont(new Font("Dialog", Font.BOLD, 17));
		lblExtraGuess.setBounds(250, 160, 134, 23);
		shopGUIFrame.getContentPane().add(lblExtraGuess);
		
		JLabel lblClueForPaper = new JLabel("Clue for Paper Scissors Rock");
		lblClueForPaper.setFont(new Font("Dialog", Font.BOLD, 17));
		lblClueForPaper.setBounds(396, 165, 273, 15);
		shopGUIFrame.getContentPane().add(lblClueForPaper);
		
		buyExtraRollButton.setBounds(50, 220, 120, 40);
		shopGUIFrame.getContentPane().add(buyExtraRollButton);
		
		JLabel lblInventory = new JLabel("Inventory:");
		lblInventory.setFont(new Font("Dialog", Font.BOLD, 20));
		lblInventory.setBounds(700, 320, 150, 15);
		shopGUIFrame.getContentPane().add(lblInventory);
		
		JLabel lblHealingItems = new JLabel("Healing Items:");
		lblHealingItems.setBounds(700, 360, 122, 20);
		shopGUIFrame.getContentPane().add(lblHealingItems);
		
		JLabel lblPowerUps = new JLabel("Power Ups:");
		lblPowerUps.setBounds(700, 400, 108, 20);
		shopGUIFrame.getContentPane().add(lblPowerUps);
		
		powerUpsLabel.setBounds(820, 400, 366, 20);
		shopGUIFrame.getContentPane().add(powerUpsLabel);
		tooManyMapsLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		
		tooManyMapsLabel.setBounds(188, 628, 413, 25);
		shopGUIFrame.getContentPane().add(tooManyMapsLabel);
		tooManyMapsLabel.setVisible(false);
		
		
		JLabel extraRollCostLabel = new JLabel("$" + Integer.toString(extraRoll.getCost()));
		extraRollCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		extraRollCostLabel.setBounds(83, 190, 70, 15);
		shopGUIFrame.getContentPane().add(extraRollCostLabel);
		
		JLabel extraGuessCostLabel = new JLabel("$" + Integer.toString(extraGuess.getCost()));
		extraGuessCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		extraGuessCostLabel.setBounds(285, 190, 70, 15);
		shopGUIFrame.getContentPane().add(extraGuessCostLabel);
		
		JLabel clueCostLabel = new JLabel("$" + Integer.toString(paperScissorsRockClue.getCost()));
		clueCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		clueCostLabel.setBounds(485, 190, 70, 15);
		shopGUIFrame.getContentPane().add(clueCostLabel);
		
		JLabel lblMaps = new JLabel("Maps:");
		lblMaps.setBounds(700, 440, 70, 20);
		shopGUIFrame.getContentPane().add(lblMaps);
		
		
		numberMapsLabel.setBounds(820, 440, 70, 20);
		shopGUIFrame.getContentPane().add(numberMapsLabel);
		
		JLabel titlePowerUpsLabel = new JLabel("Power Ups");
		titlePowerUpsLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePowerUpsLabel.setBounds(250, 114, 140, 44);
		shopGUIFrame.getContentPane().add(titlePowerUpsLabel);
		
		JLabel titleHealingItemsLabel = new JLabel("Healing Items");
		titleHealingItemsLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		titleHealingItemsLabel.setBounds(232, 320, 172, 15);
		shopGUIFrame.getContentPane().add(titleHealingItemsLabel);
		
		JLabel innKeeperPic = new JLabel("");
		innKeeperPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/innkeeper.png")));
		innKeeperPic.setBounds(911, 78, 140, 205);
		shopGUIFrame.getContentPane().add(innKeeperPic);
		
		JLabel speechLabel = new JLabel("Welcome to my Shop!");
		speechLabel.setBounds(782, 97, 244, 25);
		shopGUIFrame.getContentPane().add(speechLabel);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		shopGUIFrame.getContentPane().add(backgroundPic);
		
		
		
		updatePowerUpsLabel();
		updateHealingItemsLabel();
		updateNumberMapsLabel();	


	}
}
