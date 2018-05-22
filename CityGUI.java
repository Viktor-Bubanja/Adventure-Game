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
	
	/**
	 * Launch the application.
	 */
	/* public static void NewScreen(Team teamInput, GameEnvironment gameEnvironmentInput) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CityGUI cityGuiWindow = new CityGUI(teamInput, gameEnvironmentInput);
					cityGuiWindow.CityScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */
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
		System.out.println("gifted fdgfdgfd");
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
			System.out.println("number healing items ");
			System.out.println(Integer.toString(numberHealingItems));
			if (numberHealingItems > 0) {
				robLabel.setVisible(true);
				int randomIndex = random.nextInt(numberHealingItems);
				HealingItem robbedHealingItem = team.getHealingItems().get(randomIndex);
				team.removeHealingItem(robbedHealingItem);
				randomRobbedItemLabel.setText(robbedHealingItem.getName());
			}
		} else {
			int numberPowerUps = team.getPowerUps().size();
			System.out.println("number power ups");
			System.out.println(Integer.toString(numberPowerUps));
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
		CityScreen.setBounds(100, 100, 1000, 700);
		CityScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CityScreen.getContentPane().setLayout(null);
		
		robLabel.setBounds(75, 428, 308, 15);
		CityScreen.getContentPane().add(robLabel);
		robLabel.setVisible(false);
		
		giftLabel.setBounds(463, 428, 295, 15);
		CityScreen.getContentPane().add(giftLabel);
		giftLabel.setVisible(false);
		
		randomEvent();
		
		useMapButton.setBounds(494, 53, 117, 25);
		CityScreen.getContentPane().add(useMapButton);
		useMapButton.setVisible(false);
		
		youHaveAMapLabel.setBounds(406, 12, 295, 25);
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
		westButton.setBounds(30, 172, 189, 33);
		CityScreen.getContentPane().add(westButton);
		
		JButton eastButton = new JButton("East");
		eastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(2);
				enterDistrict(currentLocation);
			}
		});
		eastButton.setBounds(463, 176, 181, 25);
		CityScreen.getContentPane().add(eastButton);
		
		JButton northButton = new JButton("North");
		northButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(1);
				enterDistrict(currentLocation);	
			}
		});
		northButton.setBounds(273, 53, 168, 25);
		CityScreen.getContentPane().add(northButton);
		
		JButton southButton = new JButton("South");
		southButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(3);
				enterDistrict(currentLocation);
			}
		});
		southButton.setBounds(273, 309, 180, 25);
		CityScreen.getContentPane().add(southButton);
		
		JLabel lblHomeBase = new JLabel("Home Base");
		lblHomeBase.setBounds(273, 181, 110, 15);
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
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityScreen.dispose();
			}
		});
		close.setBounds(0, 309, 117, 25);
		CityScreen.getContentPane().add(close);
		
		randomRobbedItemLabel.setBounds(162, 465, 134, 15);
		CityScreen.getContentPane().add(randomRobbedItemLabel);
		
		randomGiftedItemLabel.setBounds(541, 465, 134, 15);
		CityScreen.getContentPane().add(randomGiftedItemLabel);
		
		finalCityLabel.setBounds(30, 58, 189, 33);
		CityScreen.getContentPane().add(finalCityLabel);
		if (!gameEnvironment.finalCity()) {
			finalCityLabel.setVisible(false);
		}
		

	}
}
