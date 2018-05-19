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

	public static JFrame CityScreen;
	private boolean hasMap = false;
	private JButton useMapButton = new JButton("Use map");
	private JLabel youHaveAMapLabel = new JLabel("You have a map available to use!");

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CityGUI window = new CityGUI();
					window.CityScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CityGUI() {
		initialize();
	}
	
	public static void enterDistrict(String currentLocation) {
		if (currentLocation == "SHOP") {
			ShopGUI shop = new ShopGUI();
			shop.NewScreen();
			CityScreen.setVisible(false);
		} else if (currentLocation == "POWERUPDEN") {
			PowerUpDenGUI den = new PowerUpDenGUI();
			den.NewScreen();
			CityScreen.setVisible(false);
		} else if (currentLocation == "HOSPITAL") {
			HospitalGUI hospital = new HospitalGUI();
			hospital.NewScreen();
			CityScreen.setVisible(false);
		} else if (currentLocation == "VILLAINSLAIR") {
			LairGUI lair = new LairGUI();
			lair.NewScreen();
			CityScreen.setVisible(false);
			
		} //else if (currentLocation == "HOMEBASE") {}
	
	}
	private void showMapButtonAndLabel() {
		useMapButton.setVisible(true);
		youHaveAMapLabel.setVisible(true);
	}
	private void hideMapButtonAndLabel() {
		useMapButton.setVisible(false);
		youHaveAMapLabel.setVisible(false);
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
		
		useMapButton.setBounds(494, 53, 117, 25);
		CityScreen.getContentPane().add(useMapButton);
		useMapButton.setVisible(false);
		
		youHaveAMapLabel.setBounds(406, 12, 295, 25);
		youHaveAMapLabel.setVisible(false);
		CityScreen.getContentPane().add(youHaveAMapLabel);
		for (int i = 0; i < Team.getHeroes().size(); i++) {
			System.out.println(Team.getHeroTypes().get(i));
		}
		
		if (Team.getNumberMaps() >0 || Team.teamHasExplorer()) {
			System.out.println("FDHFDHFDGHGFDHGFD");
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
				hideMapButtonAndLabel();
				Team.removeMap();
				
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
	}
}
