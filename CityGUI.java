import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class CityGUI {
/**
 * Attributes:
 * The Frame GUI for the city you are in
 * The game Environment handling the game
 * Your Team
 * A boolean to keep track of if you have explored the city
 * Button that uses a map and reveals the districts of the city
 * Displays if you have a Map available to use
 * Displays if you have been robbed
 * Displays if you have been gifted
 * Displays the item taken from you if robbed
 * Displays the item gifted to you if gifted
 * The background picture that changes every city
 * Displays the attributes of your hero
 */
	private JFrame cityScreenFrame;
	private GameEnvironment gameEnvironment;
	private Team team;
	private JButton useMapButton;
	private JLabel youHaveAMapLabel;
	private JLabel robLabel;
	private JLabel giftLabel;
	private JLabel randomRobbedItemLabel;
	private JLabel randomGiftedItemLabel;
	private JLabel backgroundPic;
	private JTextArea heroesListLabel;

	/**
	 * Initializes a city
	 * @param teamInput Team
	 * @param gameEnvironmentInput GameEnvironment
	 */
	public CityGUI(Team teamInput, GameEnvironment gameEnvironmentInput) {	
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		initialize();
	}
	/**
	 * Disposes of the Frame
	 */
	public void disposeCity() {
		cityScreenFrame.dispose();
	}
	/**
	 * Opens the city gui for you to explore the current city
	 */
	public void makeCityVisible() {
		cityScreenFrame.setVisible(true);
	}
	
	/**
	 * Hides the city
	 * Opens the GUI of the district you are moving too
	 * @param currentLocation String The district you are moving too
	 */
	public void enterDistrict(String currentLocation) {
		if (currentLocation == "SHOP") {
			gameEnvironment.openShopScreen(team, this);
			cityScreenFrame.setVisible(false);
		} else if (currentLocation == "POWERUPDEN") {
			gameEnvironment.openPowerUpDenScreen(team, this);
			cityScreenFrame.setVisible(false);
		} else if (currentLocation == "HOSPITAL") {
			gameEnvironment.openHospitalScreen(team, this);
			cityScreenFrame.setVisible(false);
		} else if (currentLocation == "VILLAINSLAIR") {
			gameEnvironment.openLairScreen(team, this);
			cityScreenFrame.setVisible(false);	
		}
	}
	/**
	 * makes visible the button to use a map
	 * makes visible the label indicating if you have a map ready to use
	 */
	public void showMapButtonAndLabel() {
		useMapButton.setVisible(true);
		youHaveAMapLabel.setVisible(true);
	}

	/**
	 * hides the button to use a map
	 * hides the label indicating if you have a map ready to use
	 */
	private void hideMapButtonAndLabel() {
		useMapButton.setVisible(false);
		youHaveAMapLabel.setVisible(false);
	}
	/**
	 * Gifts the Team a random item, either a Healing item or Power Up
	 */
	private void giftRandomItem() {
		Random random1 = new Random();
		Random random2 = new Random();
		int randomIndex = random1.nextInt(3);
		boolean giftedRandomHealingItem = random2.nextBoolean();
		giftLabel.setVisible(true);
		if (giftedRandomHealingItem) {
			List<HealingItem> healingItems = gameEnvironment.getHealingItemsList();
			HealingItem randomHealingItem = healingItems.get(randomIndex);
			team.addHealingItem(randomHealingItem);
			
			randomGiftedItemLabel.setText(randomHealingItem.getName());
		} else {
			List<PowerUp> powerUps = gameEnvironment.getPowerUpsList();
			PowerUp randomPowerUp = powerUps.get(randomIndex);
			team.addPowerUp(randomPowerUp);
			giftLabel.setVisible(true);
			randomGiftedItemLabel.setText(randomPowerUp.getName());
		}
	}
	/**
	 * Robs the team of a random Healing Item or Power Up
	 */
	private void robRandomItem() {
		Random random1 = new Random();
		Random random2 = new Random();
		boolean robbedRandomHealingItem = random1.nextBoolean();
		if (robbedRandomHealingItem) {
			int numberHealingItems = team.getHealingItems().size();
			if (numberHealingItems > 0) {
				robLabel.setVisible(true);
				int randomIndex = random2.nextInt(numberHealingItems);
				HealingItem robbedHealingItem = team.getHealingItems().get(randomIndex);
				team.removeHealingItem(robbedHealingItem);
				randomRobbedItemLabel.setText(robbedHealingItem.getName());
			}
		} else {
			int numberPowerUps = team.getPowerUps().size();
			if (numberPowerUps > 0) {
				int randomIndex = random2.nextInt(numberPowerUps);
				robLabel.setVisible(true);
				PowerUp robbedPowerUp = team.getPowerUps().get(randomIndex);
				team.removePowerUp(robbedPowerUp);
				randomRobbedItemLabel.setText(robbedPowerUp.getName());
			}
		}
	}
	/**
	 * calculates whether the team gets robbed, gifted or neither
	 */
	private void randomEvent() {
		Random random1 = new Random();
		Random random2 = new Random();
		boolean randomEventHappens = random1.nextBoolean();
		boolean giftedRandomItem = random2.nextBoolean();
		if (randomEventHappens) {
			if (giftedRandomItem) {
				giftRandomItem();
			} else {
				robRandomItem();
			}
		}
	}
	/**
	 * Updates the hero attributes displayed
	 * Mainly to update the displayed health
	 */
	public void updateHeroListLabel() {
		String heroesListString = "";
		for (Hero hero: team.getHeroes()) {
			heroesListString = heroesListString + hero.toString();
		}
		heroesListLabel.setText(heroesListString);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cityScreenFrame = new JFrame();
		cityScreenFrame.setTitle("");
		cityScreenFrame.setBounds(100, 100, 1200, 800);
		cityScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cityScreenFrame.getContentPane().setLayout(null);

		robLabel = new JLabel("Oh no! You've been robbed. You lost a:"); //Label declaring you've been robbed
		robLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		robLabel.setForeground(new Color(255, 255, 255));
		robLabel.setBounds(70, 548, 395, 15);
		cityScreenFrame.getContentPane().add(robLabel);
		robLabel.setVisible(false);
		
		randomRobbedItemLabel = new JLabel(""); //Displays the item stolen from you
		randomRobbedItemLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		randomRobbedItemLabel.setForeground(new Color(255, 255, 255));
		randomRobbedItemLabel.setBounds(70, 583, 388, 25);
		cityScreenFrame.getContentPane().add(randomRobbedItemLabel);
		
		randomGiftedItemLabel = new JLabel("");//displays the item gifted to you
		randomGiftedItemLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		randomGiftedItemLabel.setForeground(new Color(255, 255, 255));
		randomGiftedItemLabel.setBounds(770, 575, 338, 25);
		cityScreenFrame.getContentPane().add(randomGiftedItemLabel);
		
		JLabel finalCityLabel = new JLabel(); //label displaying which city you are in. Or if in the final city
		finalCityLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		finalCityLabel.setForeground(Color.WHITE);
		finalCityLabel.setBounds(28, 28, 429, 40);
		cityScreenFrame.getContentPane().add(finalCityLabel);
		if (gameEnvironment.finalCity()) {
			finalCityLabel.setText("You are in the final city");
		} else {
			finalCityLabel.setText("You are in city: " + (gameEnvironment.getCurrentCityIndex() + 1) + "/" + gameEnvironment.getNumberCities());;
		}

		heroesListLabel = new JTextArea(); 
		heroesListLabel.setFont(new Font("Dialog", Font.PLAIN, 17)); // Text area of the hero attributes
		heroesListLabel.setForeground(Color.WHITE);
		heroesListLabel.setRows(3);
		heroesListLabel.setColumns(1);
		heroesListLabel.setOpaque(false);
		heroesListLabel.setEditable(false);
		heroesListLabel.setBounds(50, 620, 851, 60);
		cityScreenFrame.getContentPane().add(heroesListLabel);
		updateHeroListLabel();
		
		giftLabel = new JLabel("Congratulations! You've been gifted a:"); //Label declaring you've gotten a gift
		giftLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		giftLabel.setForeground(new Color(255, 255, 255));
		giftLabel.setBounds(703, 548, 405, 15);
		cityScreenFrame.getContentPane().add(giftLabel);
		giftLabel.setVisible(false);
		
		randomEvent();		
		
		List<String> positions = new ArrayList<String>(); //Randomizes the positions of the districts
		positions.add("SHOP");
		positions.add("POWERUPDEN");
		positions.add("HOSPITAL");
		positions.add("VILLAINSLAIR");
		Collections.shuffle(positions);
		positions.add(0,"HOMEBASE");

		JButton westButton = new JButton("West"); //Button for going to the west district
		westButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(4);
				enterDistrict(currentLocation);
			}
		});
		westButton.setBounds(200, 275, 180, 60);
		cityScreenFrame.getContentPane().add(westButton);
		
		JButton eastButton = new JButton("East"); //Button for going to the East district
		eastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(2);
				enterDistrict(currentLocation);
			}
		});
		eastButton.setBounds(820, 275, 180, 60);
		cityScreenFrame.getContentPane().add(eastButton);
		
		JButton northButton = new JButton("North");//Button for going to the North district
		northButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(1);
				enterDistrict(currentLocation);	
			}
		});
		northButton.setBounds(510, 100, 180, 60);
		cityScreenFrame.getContentPane().add(northButton);
		
		JButton southButton = new JButton("South");//Button for going to the South district
		southButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(3);
				enterDistrict(currentLocation);
			}
		});
		southButton.setBounds(510, 450, 180, 60);
		cityScreenFrame.getContentPane().add(southButton);
		
		JLabel homeBaseLabel = new JLabel("Home Base"); // Label stating you are in the home base
		homeBaseLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		homeBaseLabel.setForeground(new Color(255, 255, 255));
		homeBaseLabel.setBackground(new Color(0, 0, 0));
		homeBaseLabel.setBounds(510, 283, 180, 40);
		cityScreenFrame.getContentPane().add(homeBaseLabel);
		
		
		youHaveAMapLabel = new JLabel("You have a map available to use!");  //label declaring whether or not you have a label
		youHaveAMapLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		youHaveAMapLabel.setForeground(new Color(255, 255, 255));
		youHaveAMapLabel.setBounds(830, 73, 331, 25);
		youHaveAMapLabel.setVisible(false);
		cityScreenFrame.getContentPane().add(youHaveAMapLabel);
		
		useMapButton = new JButton("Use map");  // Button that uses a map and reveals the city district
		useMapButton.setBounds(921, 110, 120, 40);
		cityScreenFrame.getContentPane().add(useMapButton);
		useMapButton.setVisible(false);
		useMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				northButton.setText(positions.get(1));
				eastButton.setText(positions.get(2));
				southButton.setText(positions.get(3));
				westButton.setText(positions.get(4));
				hideMapButtonAndLabel();
				if (!team.teamHasExplorer()) {
					team.removeMap();		
				}
			}
		});
		if (team.getNumberMaps() >0 || team.teamHasExplorer()) {
			showMapButtonAndLabel();
		}
		
		JButton closeLabel = new JButton("I Give Up"); // Closes the game mid game :(
		closeLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cityScreenFrame.dispose();
			}
		});
		closeLabel.setBounds(980, 680, 180, 60);
		cityScreenFrame.getContentPane().add(closeLabel);

		backgroundPic = new JLabel(); // background pic that changes for every city
		backgroundPic.setIcon(new ImageIcon(CityGUI.class.getResource(gameEnvironment.getBackground(gameEnvironment.getCurrentCityIndex()))));
		backgroundPic.setBounds(0, 12, 1200, 800);
		cityScreenFrame.getContentPane().add(backgroundPic);
	}
	
}
