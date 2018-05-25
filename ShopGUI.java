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

	
	/**
	 * Shop GUI Class.
	 * The team can buy healing items, power ups, and maps from the shop.
	 * Attributes:
	 * JFrame for the ShopGUI.
	 * Small Potion (Subclass of HealingItem). Heals hero by 25%.
	 * Quick Potion (Subclass of HealingItem). Heals hero by 25% quickly.
	 * Big Potion (Subclass of HealingItem). Heals the hero by 50%.
	 * Golden Die (Subclass of PowerUp). In the Dice Game, a hero with this power up can only roll higher than or equal to 3.
	 * Extra Guess (Subclass of PowerUp). In the Guess the Number Game, a hero with this power up has an extra guess to guess the number.
	 * Paper Scissors Rock Clue (Subclass of PowerUp). In the Paper Scissors Rock Game, a hero with this power up gets a clue indicating what the Villain's move might be.
	 * Map. Reveals the layout of the city.
	 * The current city you are fighting in.
	 * Your team
	 * The game environment handling the game.
	 * List of power ups the team can buy.
	 * List of healing items the team can buy.
	 * Warning labels and Labels showing the inventory of the team.
	 * 
	 */
	private JFrame shopGUIFrame;
	private HealingItem smallPotion;
	private HealingItem quickPotion;
	private HealingItem bigPotion;
	private PowerUp goldenDie;
	private PowerUp extraGuess;
	private PowerUp paperScissorsRockClue;
	private Map map;
	private CityGUI cityGui;
	private Team team;
	private GameEnvironment gameEnvironment;
	private static List<PowerUp> powerUps;
	private static List<HealingItem> healingItems;
	private JLabel notEnoughMoneyLabel;
	private JLabel powerUpsLabel;
	private JLabel healingItemsLabel;
	private JLabel tooManyMapsLabel;
	private JLabel numberMapsLabel;
	
	/**
	 * Adds power ups to list of power ups, and adds healing items to list of healing items.
	 * Calls initialize()
	 * @param Team teamInput.
	 * @param GameEnvironment gameEnvironmentInput
	 * @param CityGUI cityGuiInput
	 */
	public ShopGUI(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		map = new Map();
		smallPotion = new SmallPotion();
		quickPotion = new QuickPotion();
		bigPotion = new BigPotion();
		goldenDie = new GoldenDie();
		extraGuess = new ExtraGuess();
		paperScissorsRockClue = new PaperScissorsRockClue();
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		powerUps = new ArrayList<PowerUp>();
		powerUps.add(paperScissorsRockClue);
		powerUps.add(extraGuess);
		powerUps.add(goldenDie);
		healingItems = new ArrayList<HealingItem>();
		healingItems.add(smallPotion);
		healingItems.add(quickPotion);
		healingItems.add(bigPotion);
		cityGui = cityGuiInput;
		
		initialize();
	}
	/**
	 * Makes the window visible when first initialized.
	 */
	public void makeVisible() {
		this.shopGUIFrame.setVisible(true);
	}
	/**
	 * 
	 * @return List<PowerUp>  powerUps
	 */
	public List<PowerUp> getPowerUpList() {
		return powerUps;
	}
	/**
	 * 
	 * @return List<HealingItem> healingItems
	 */
	public List<HealingItem> getHealingItemList() {
		return healingItems;
	}
	/**
	 * 
	 * @return HealingItem smallPotion
	 */
	public HealingItem getSmallPotion() {
		return smallPotion;
	}
	/**
	 * 
	 * @return HealingItem quickPotion
	 */
	public HealingItem getQuickPotion() {
		return quickPotion;
	}
	/**
	 * 
	 * @return HealingItem bigPotion
	 */
	public HealingItem getBigPotion() {
		return bigPotion;
	}
	/**
	 * 
	 * @return PowerUp goldenDie
	 */
	public PowerUp getGoldenDie() {
		return goldenDie;
	}
	/**
	 * 
	 * @return PowerUp extraGuess
	 */
	public PowerUp getExtraGuess() {
		return extraGuess;
	}
	/**
	 * 
	 * @return PowerUp paperScissorsRockClue
	 */
	public PowerUp getpaperScissorsRockClue() {
		return paperScissorsRockClue;
	}


	/**
	 * Method for purchasing a healing item.
	 * If the team has enough money, adds the healing item to the teams inventory, and reduces their money by
	 * the cost of the item.
	 * @param HealingItem healingItem
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
	/**
	 * Method for purchasing a healing item.
	 * If the team has enough money, and doesn't already have a map for each city, adds the map to their inventory
	 * and reduces their money by the cost of the item.
	 * @param Map map
	 */
	public void buyMap(Map map) {
		int cost = map.getCost();
		if (team.getMoney() < cost) {
			notEnoughMoneyLabel.setVisible(true);
		} else if (team.getNumberMaps() < gameEnvironment.getNumberCities() - gameEnvironment.getCurrentCityIndex()){
			notEnoughMoneyLabel.setVisible(false);
			team.addMap();
			team.decreaseMoneyBy(cost);	
			updateNumberMapsLabel();
			cityGui.showMapButtonAndLabel();
			
		} else {
			tooManyMapsLabel.setVisible(true);
		}
	}
	/**
	 * Method for purchasing a power up.
	 * If the team has enough money, adds the power up to their inventory, and reduces their money by the cost of the item.
	 * @param PowerUp powerUp
	 */
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
	/**
	 * Updates the label showing the healing items in the teams inventory after the team either consumes a healing item,
	 * or buys a healing item.
	 */
	public void updateHealingItemsLabel() {
		String[] healingItemNames = team.getHealingItemNames();
		String healingItemsString = "";
		for (int i = 0; i < healingItemNames.length; i++) {
			healingItemsString += healingItemNames[i] + " ";
		}
		healingItemsLabel.setText(healingItemsString);
	}
	/**
	 * Updates the label showing the power ups in the teams inventory after the team either consumes a power up,
	 * or buys a power up.
	 */
	public void updatePowerUpsLabel() {
		String[] powerUpNames = team.getPowerUpNames();
		String powerUpsString = "";
		for (int i = 0; i < powerUpNames.length; i++) {
			powerUpsString += powerUpNames[i] + " ";
		}
		powerUpsLabel.setText(powerUpsString);
	}
	/**
	 * Updates the label showing the number of maps in the teams inventory after the team either uses a map,
	 * or buys a map.
	 */
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
		// Checks if the team has a diplomat, if so, reduces the cost of items in the shop.
		if (team.teamHasDiplomat()) {
			smallPotion.reduceCost(5);
			quickPotion.reduceCost(10);
			bigPotion.reduceCost(15);
			goldenDie.reduceCost(10);
			extraGuess.reduceCost(15);
			paperScissorsRockClue.reduceCost(15);
		}
		
		healingItemsLabel = new JLabel("");
		healingItemsLabel.setBounds(820, 360, 353, 20);
		shopGUIFrame.getContentPane().add(healingItemsLabel);
			
		JLabel smallPotLabel = new JLabel("Small Potion");
		smallPotLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		smallPotLabel.setBounds(48, 345, 122, 23);
		shopGUIFrame.getContentPane().add(smallPotLabel);
		
		JLabel quickPotLabel = new JLabel("Quick Potion");
		quickPotLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		quickPotLabel.setBounds(275, 345, 122, 23);
		shopGUIFrame.getContentPane().add(quickPotLabel);
		
		JLabel bigPotLabel = new JLabel("Big Potion");
		bigPotLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		bigPotLabel.setBounds(501, 345, 122, 23);
		shopGUIFrame.getContentPane().add(bigPotLabel);
		
		JLabel mapLabel = new JLabel("Map");
		mapLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		mapLabel.setBounds(85, 560, 61, 15);
		shopGUIFrame.getContentPane().add(mapLabel);
		
		JLabel smallPotionCostLabel = new JLabel("$" + Integer.toString(smallPotion.getCost()));
		smallPotionCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		smallPotionCostLabel.setBounds(85, 395, 70, 15);
		shopGUIFrame.getContentPane().add(smallPotionCostLabel);
		
		JLabel quickPotionCostLabel = new JLabel("$" + Integer.toString(quickPotion.getCost()));
		quickPotionCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quickPotionCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		quickPotionCostLabel.setBounds(300, 395, 70, 15);
		shopGUIFrame.getContentPane().add(quickPotionCostLabel);
		
		JLabel bigPotionCostLabel = new JLabel("$" + Integer.toString(bigPotion.getCost()));
		bigPotionCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		bigPotionCostLabel.setBounds(535, 395, 70, 15);
		shopGUIFrame.getContentPane().add(bigPotionCostLabel);
		
		JLabel mapCostLabel = new JLabel("$" + Integer.toString(map.getCost()));
		mapCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		mapCostLabel.setBounds(85, 600, 70, 15);
		shopGUIFrame.getContentPane().add(mapCostLabel);
		
		JLabel moneyLeftLabel = new JLabel("Money left: " + team.getMoney());
		moneyLeftLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		moneyLeftLabel.setBounds(700, 522, 172, 15);
		shopGUIFrame.getContentPane().add(moneyLeftLabel);
		
		JLabel shopTitleLabel = new JLabel("Shop");
		shopTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		shopTitleLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		shopTitleLabel.setBounds(540, 20, 120, 73);
		shopGUIFrame.getContentPane().add(shopTitleLabel);
		notEnoughMoneyLabel = new JLabel("You don't have enough money!");
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
		buyQuickPotionButton.setBounds(275, 420, 120, 40);
		shopGUIFrame.getContentPane().add(buyQuickPotionButton);
		
		JButton buyBigPotionButton = new JButton("Buy");
		buyBigPotionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHealingItem(bigPotion);
				moneyLeftLabel.setText("Money left: " + team.getMoney());
			}
		});
		buyBigPotionButton.setBounds(500, 420, 120, 40);
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
		
		JButton backHomeButton = new JButton("Back to Home Base!");
		backHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cityGui.makeCityVisible();
				shopGUIFrame.dispose();
			}
		});
		backHomeButton.setBounds(980, 680, 180, 60);
		shopGUIFrame.getContentPane().add(backHomeButton);
		
		JButton buyExtraRollButton = new JButton("Buy");
		buyExtraRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyPowerUp(goldenDie);
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
		buyPaperScissorsRockClueButton.setBounds(500, 220, 120, 40);
		shopGUIFrame.getContentPane().add(buyPaperScissorsRockClueButton);
		buyExtraGuessButton.setBounds(275, 220, 120, 40);
		shopGUIFrame.getContentPane().add(buyExtraGuessButton);
		
		JLabel goldenDieTitleLabel = new JLabel("Golden Die");
		goldenDieTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goldenDieTitleLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		goldenDieTitleLabel.setBounds(45, 145, 125, 23);
		shopGUIFrame.getContentPane().add(goldenDieTitleLabel);
		
		JLabel extraGuessTitleLabel = new JLabel("Extra Guess");
		extraGuessTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		extraGuessTitleLabel.setForeground(new Color(0, 0, 0));
		extraGuessTitleLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		extraGuessTitleLabel.setBounds(275, 145, 134, 23);
		shopGUIFrame.getContentPane().add(extraGuessTitleLabel);
		
		JLabel paperClueLabel = new JLabel("Clue for Paper Scissors Rock");
		paperClueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paperClueLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		paperClueLabel.setBounds(430, 145, 273, 23);
		shopGUIFrame.getContentPane().add(paperClueLabel);
		
		buyExtraRollButton.setBounds(50, 220, 120, 40);
		shopGUIFrame.getContentPane().add(buyExtraRollButton);
		
		JLabel inventoryLabel = new JLabel("Inventory:");
		inventoryLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		inventoryLabel.setBounds(700, 320, 150, 15);
		shopGUIFrame.getContentPane().add(inventoryLabel);
		
		JLabel yourHealingItemsLabel = new JLabel("Healing Items:");
		yourHealingItemsLabel.setBounds(700, 360, 122, 20);
		shopGUIFrame.getContentPane().add(yourHealingItemsLabel);
		
		JLabel yourPowerUpsLabel = new JLabel("Power Ups:");
		yourPowerUpsLabel.setBounds(700, 400, 108, 20);
		shopGUIFrame.getContentPane().add(yourPowerUpsLabel);
		
		powerUpsLabel = new JLabel("");
		powerUpsLabel.setBounds(820, 400, 366, 20);
		shopGUIFrame.getContentPane().add(powerUpsLabel);
		
		tooManyMapsLabel = new JLabel("You already have maps for all remaining cities!");
		tooManyMapsLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		tooManyMapsLabel.setBounds(188, 628, 413, 25);
		shopGUIFrame.getContentPane().add(tooManyMapsLabel);
		tooManyMapsLabel.setVisible(false);
		
		JLabel extraRollCostLabel = new JLabel("$" + Integer.toString(goldenDie.getCost()));
		extraRollCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		extraRollCostLabel.setBounds(83, 195, 70, 15);
		shopGUIFrame.getContentPane().add(extraRollCostLabel);
		
		JLabel extraGuessCostLabel = new JLabel("$" + Integer.toString(extraGuess.getCost()));
		extraGuessCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		extraGuessCostLabel.setBounds(310, 195, 70, 15);
		shopGUIFrame.getContentPane().add(extraGuessCostLabel);
		
		JLabel clueCostLabel = new JLabel("$" + Integer.toString(paperScissorsRockClue.getCost()));
		clueCostLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		clueCostLabel.setBounds(535, 195, 70, 15);
		shopGUIFrame.getContentPane().add(clueCostLabel);
		
		JLabel mapsLabel = new JLabel("Maps:");
		mapsLabel.setBounds(700, 440, 70, 20);
		shopGUIFrame.getContentPane().add(mapsLabel);
		
		numberMapsLabel = new JLabel("");
		numberMapsLabel.setBounds(820, 440, 70, 20);
		shopGUIFrame.getContentPane().add(numberMapsLabel);
		
		JLabel titlePowerUpsLabel = new JLabel("Power Ups");
		titlePowerUpsLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePowerUpsLabel.setBounds(276, 100, 140, 44);
		shopGUIFrame.getContentPane().add(titlePowerUpsLabel);
		
		JLabel titleHealingItemsLabel = new JLabel("Healing Items");
		titleHealingItemsLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		titleHealingItemsLabel.setBounds(258, 300, 172, 15);
		shopGUIFrame.getContentPane().add(titleHealingItemsLabel);
		
		JLabel innKeeperPic = new JLabel("");
		innKeeperPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/innkeeper.png")));
		innKeeperPic.setBounds(934, 39, 226, 296);
		shopGUIFrame.getContentPane().add(innKeeperPic);
		
		JLabel speechLabel = new JLabel("Welcome to my Shop!");
		speechLabel.setBounds(836, 55, 166, 30);
		shopGUIFrame.getContentPane().add(speechLabel);
		
		JLabel goldenDieDescLabel = new JLabel("Rolls higher numbers");
		goldenDieDescLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goldenDieDescLabel.setBounds(34, 172, 150, 15);
		shopGUIFrame.getContentPane().add(goldenDieDescLabel);
		
		JLabel extraGuessLabel = new JLabel("Extra guess at Guess the Number");
		extraGuessLabel.setBounds(210, 172, 250, 15);
		shopGUIFrame.getContentPane().add(extraGuessLabel);
		
		JLabel paperClueDescLabel = new JLabel("Hints at Villains move");
		paperClueDescLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paperClueDescLabel.setBounds(475, 172, 175, 15);
		shopGUIFrame.getContentPane().add(paperClueDescLabel);
		
		JLabel smallHealthLabel = new JLabel("Heals 25% health");
		smallHealthLabel.setBounds(48, 370, 150, 15);
		shopGUIFrame.getContentPane().add(smallHealthLabel);
		
		JLabel quickHealthDescLabel = new JLabel("Heals 25% health quickly");
		quickHealthDescLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quickHealthDescLabel.setBounds(250, 370, 180, 15);
		shopGUIFrame.getContentPane().add(quickHealthDescLabel);
		
		JLabel bigHealthDescLabel = new JLabel("Heals 50% health");
		bigHealthDescLabel.setBounds(490, 370, 150, 15);
		shopGUIFrame.getContentPane().add(bigHealthDescLabel);
		
		JLabel mapDescLabel = new JLabel("Reveals layout of City");
		mapDescLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mapDescLabel.setBounds(28, 580, 170, 15);
		shopGUIFrame.getContentPane().add(mapDescLabel);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		shopGUIFrame.getContentPane().add(backgroundPic);
		
		// Updates labels when a ShopGUI window is opened in case the team consumed a power up or healing item, or used a map
		updatePowerUpsLabel();
		updateHealingItemsLabel();
		updateNumberMapsLabel();	


	}
}
