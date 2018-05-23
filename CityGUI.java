import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class CityGUI {

	private JFrame CityScreen;
	private boolean usedMap = false;
	private JButton useMapButton = new JButton("Use map");
	private JLabel youHaveAMapLabel = new JLabel("You have a map available to use!");
	private JLabel robLabel = new JLabel("Oh no! You've been robbed. You lost a:");
	private JLabel giftLabel = new JLabel("Congratulations! You've been gifted a:");
	private JLabel randomRobbedItemLabel = new JLabel("");
	private JLabel randomGiftedItemLabel = new JLabel("");
	private Team team;
	private GameEnvironment gameEnvironment;
	private CityGUI cityGuiWindow;
	private JLabel finalCityLabel = new JLabel("Final City!");
	private final JLabel backgroundPic = new JLabel("");
	

	/**
	 * Create the application.
	 */
	public void makeWindowVisible() {
		this.CityScreen.setVisible(true);
	}
	
	public CityGUI(Team teamInput, GameEnvironment gameEnvironmentInput) {
		
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		initialize();
	}
	public void disposeCity() {
		CityScreen.dispose();
	}
	public void setUsedMap(boolean bool) {
		usedMap = bool;
	}
	public boolean getUsedMap() {
		return usedMap;
	}
	public void makeCityVisible() {
		CityScreen.setVisible(true);
	}
	
	public void enterDistrict(String currentLocation) {
		if (currentLocation == "SHOP") {
			gameEnvironment.openShopScreen(team, this);
			CityScreen.setVisible(false);
		} else if (currentLocation == "POWERUPDEN") {
			gameEnvironment.openPowerUpDenScreen(team, this);
			CityScreen.setVisible(false);
		} else if (currentLocation == "HOSPITAL") {
			gameEnvironment.openHospitalScreen(team, this);
			CityScreen.setVisible(false);
		} else if (currentLocation == "VILLAINSLAIR") {
			gameEnvironment.openLairScreen(team, this);
			CityScreen.setVisible(false);	
		} //else if (currentLocation == "HOMEBASE") {}
	
	}
	public void showMapButtonAndLabel() {
		useMapButton.setVisible(true);
		youHaveAMapLabel.setVisible(true);
	}
	private void hideMapButtonAndLabel() {
		useMapButton.setVisible(false);
		youHaveAMapLabel.setVisible(false);
	}
	
	
	private void giftRandomItem() {
		Random random = new Random();
		int randomIndex = random.nextInt(3);
		boolean giftedRandomHealingItem = random.nextBoolean();
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
	private void robRandomItem() {
		Random random = new Random();
		boolean robbedRandomHealingItem = random.nextBoolean();
		if (robbedRandomHealingItem) {
			int numberHealingItems = team.getHealingItems().size();
			if (numberHealingItems > 0) {
				robLabel.setVisible(true);
				int randomIndex = random.nextInt(numberHealingItems);
				HealingItem robbedHealingItem = team.getHealingItems().get(randomIndex);
				team.removeHealingItem(robbedHealingItem);
				randomRobbedItemLabel.setText(robbedHealingItem.getName());
			}
		} else {
			int numberPowerUps = team.getPowerUps().size();
			if (numberPowerUps > 0) {
				int randomIndex = random.nextInt(numberPowerUps);
				robLabel.setVisible(true);
				PowerUp robbedPowerUp = team.getPowerUps().get(randomIndex);
				team.removePowerUp(robbedPowerUp);
				randomRobbedItemLabel.setText(robbedPowerUp.getName());
			}
		}
	}
	private void randomEvent() {
		Random random = new Random();
		
		boolean randomEventHappens = random.nextBoolean();
		//boolean randomEventHappens = true;
		boolean giftedRandomItem = random.nextBoolean();
		//boolean giftedRandomItem = false;
		if (randomEventHappens) {
			if (giftedRandomItem) {
				giftRandomItem();
			} else {
				robRandomItem();
			}
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CityScreen = new JFrame();
		CityScreen.setTitle("");
		CityScreen.setBounds(100, 100, 1200, 800);
		CityScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CityScreen.getContentPane().setLayout(null);
		robLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		robLabel.setForeground(new Color(255, 255, 255));
		
		
		
		robLabel.setBounds(70, 548, 395, 15);
		CityScreen.getContentPane().add(robLabel);
		robLabel.setVisible(false);
		giftLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		giftLabel.setForeground(new Color(255, 255, 255));
		
		giftLabel.setBounds(703, 548, 405, 15);
		CityScreen.getContentPane().add(giftLabel);
		giftLabel.setVisible(false);
		
		randomEvent();
		
		useMapButton.setBounds(921, 110, 120, 40);
		CityScreen.getContentPane().add(useMapButton);
		useMapButton.setVisible(false);
		youHaveAMapLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		youHaveAMapLabel.setForeground(new Color(255, 255, 255));
		
		youHaveAMapLabel.setBounds(830, 73, 331, 25);
		youHaveAMapLabel.setVisible(false);
		CityScreen.getContentPane().add(youHaveAMapLabel);
		
		if (team.getNumberMaps() >0 || team.teamHasExplorer()) {
			showMapButtonAndLabel();
		}
		int currentPosition = 0;
		List<String> positions = new ArrayList<String>();
		positions.add("SHOP");
		positions.add("POWERUPDEN");
		positions.add("HOSPITAL");
		positions.add("VILLAINSLAIR");
		Collections.shuffle(positions);
		positions.add(0,"HOMEBASE");
		String currentLocation = "HOMEBASE";

		JButton westButton = new JButton("West");
		westButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(4);
				enterDistrict(currentLocation);
				
			}
		});
		westButton.setBounds(200, 275, 180, 60);
		CityScreen.getContentPane().add(westButton);
		
		JButton eastButton = new JButton("East");
		eastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(2);
				enterDistrict(currentLocation);
			}
		});
		eastButton.setBounds(820, 275, 180, 60);
		CityScreen.getContentPane().add(eastButton);
		
		JButton northButton = new JButton("North");
		northButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(1);
				enterDistrict(currentLocation);	
			}
		});
		northButton.setBounds(510, 100, 180, 60);
		CityScreen.getContentPane().add(northButton);
		
		JButton southButton = new JButton("South");
		southButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(3);
				enterDistrict(currentLocation);
			}
		});
		southButton.setBounds(510, 450, 180, 60);
		CityScreen.getContentPane().add(southButton);
		
		JLabel lblHomeBase = new JLabel("Home Base");
		lblHomeBase.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHomeBase.setForeground(new Color(255, 255, 255));
		lblHomeBase.setBackground(new Color(0, 0, 0));
		lblHomeBase.setBounds(510, 283, 180, 40);
		CityScreen.getContentPane().add(lblHomeBase);

		useMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				northButton.setText(positions.get(1));
				eastButton.setText(positions.get(2));
				southButton.setText(positions.get(3));
				westButton.setText(positions.get(4));
				
				usedMap = true;
				hideMapButtonAndLabel();
				if (!team.teamHasExplorer()) {
					team.removeMap();		
				}
			}
		});
		JButton close = new JButton("I Give Up");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityScreen.dispose();
			}
		});
		close.setBounds(59, 685, 120, 40);
		CityScreen.getContentPane().add(close);
		randomRobbedItemLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		randomRobbedItemLabel.setForeground(new Color(255, 255, 255));
		
		randomRobbedItemLabel.setBounds(154, 585, 265, 25);
		CityScreen.getContentPane().add(randomRobbedItemLabel);
		randomGiftedItemLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		randomGiftedItemLabel.setForeground(new Color(255, 255, 255));
		
		randomGiftedItemLabel.setBounds(786, 575, 265, 25);
		CityScreen.getContentPane().add(randomGiftedItemLabel);
		
		finalCityLabel.setBounds(30, 58, 189, 33);
		CityScreen.getContentPane().add(finalCityLabel);
		System.out.println(gameEnvironment.getBackground(gameEnvironment.getCurrentCityIndex()));
		backgroundPic.setIcon(new ImageIcon(CityGUI.class.getResource(gameEnvironment.getBackground(gameEnvironment.getCurrentCityIndex()))));
		backgroundPic.setBounds(0, 0, 1200, 800);
		CityScreen.getContentPane().add(backgroundPic);
		
		if (!gameEnvironment.finalCity()) {
			finalCityLabel.setVisible(false);
		}
		

	}
}
