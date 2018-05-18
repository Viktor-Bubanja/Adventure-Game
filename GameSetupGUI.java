import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class GameSetupGUI {

	private JFrame setupFrame;
	private JTextField txtTeamName;
	private JTextField inputHeroName;
	private String heroType = "Gambler";
	private int inputNumHeroes;
	//GameEnvironment gameEnv = new GameEnvironment();
	
	
	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSetupGUI window = new GameSetupGUI();
					window.setupFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameSetupGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setupFrame = new JFrame("Main setup frame");
		setupFrame.setBounds(new Rectangle(0, 0, 1000, 700));
		setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupFrame.getContentPane().setLayout(null);
		
		Map map = new Map();
		HealingItem smallPotion = new HealingItem(10.00, 1, 20, "Small Potion");
		HealingItem quickPotion = new HealingItem(25.00, 1, 5, "Quick Potion");
		HealingItem bigPotion = new HealingItem(40.00, 2, 10, "Big Potion");
		List<HealingItem> healingItems = new ArrayList<HealingItem>();
		healingItems.add(smallPotion);
		healingItems.add(quickPotion);
		healingItems.add(bigPotion);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like your super team to be called?");
		lblWhatWouldYou.setBounds(2, -7, 378, 59);
		setupFrame.getContentPane().add(lblWhatWouldYou);
		
		txtTeamName = new JTextField();
		txtTeamName.setBounds(22, 38, 306, 25);
		txtTeamName.setText("Must be between 2 and 10 chars long!");
		txtTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeamName.setToolTipText("Wowwo cools!");
		setupFrame.getContentPane().add(txtTeamName);
		txtTeamName.setColumns(10);
		
		inputHeroName = new JTextField();
		inputHeroName.setBounds(379, 454, 134, 31);
		setupFrame.getContentPane().add(inputHeroName);
		inputHeroName.setColumns(10);
		
		JLabel inputCheck = new JLabel("");
		inputCheck.setBounds(423, 131, 181, 26);
		setupFrame.getContentPane().add(inputCheck);
		
		JLabel lblHeroes = new JLabel("List of Heroes");
		lblHeroes.setBounds(70, 27, 973, 245);
		setupFrame.getContentPane().add(lblHeroes);
		
		JLabel picGambler = new JLabel("Pic Gambler");
		picGambler.setBounds(379, 267, 147, 50);
		setupFrame.getContentPane().add(picGambler);
		
		JLabel picMedic = new JLabel("Medic Pic");
		picMedic.setVisible(false);
		picMedic.setBounds(319, 234, 87, 50);
		setupFrame.getContentPane().add(picMedic);
		
		JLabel picDiplomat = new JLabel("Pic Diplomat");
		picDiplomat.setVisible(false);
		picDiplomat.setBounds(379, 224, 166, 31);
		setupFrame.getContentPane().add(picDiplomat);
		
		JLabel picTank = new JLabel("Pic Tank");
		picTank.setVisible(false);
		picTank.setBounds(362, 181, 117, 31);
		setupFrame.getContentPane().add(picTank);
		
		JLabel picExplorer = new JLabel("Pic Explorer");
		picExplorer.setVisible(false);
		picExplorer.setBounds(379, 302, 124, 39);
		setupFrame.getContentPane().add(picExplorer);
		
		JLabel picLucky = new JLabel("Pic Lucky");
		picLucky.setVisible(false);
		picLucky.setBounds(443, 181, 117, 50);
		setupFrame.getContentPane().add(picLucky);
		
		JLabel lblNewLabel = new JLabel("How many cities to explore?");
		lblNewLabel.setBounds(377, 6, 302, 31);
		setupFrame.getContentPane().add(lblNewLabel);

		JLabel sliderLabel = new JLabel("3             4              5             6");
		sliderLabel.setBounds(383, 64, 296, 26);
		setupFrame.getContentPane().add(sliderLabel);
		
		JLabel lblAddUpTo = new JLabel("");
		lblAddUpTo.setBounds(19, 67, 401, 39);
		setupFrame.getContentPane().add(lblAddUpTo);
		
		JButton btnPreviousHero = new JButton("Previous Hero");
		btnPreviousHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroType == "Gambler") {
					heroType = "Lucky";
					picGambler.setVisible(false);
					picLucky.setVisible(true);
				} else if (heroType == "Medic") {
					heroType = "Gambler";
					picMedic.setVisible(false);
					picGambler.setVisible(true);
				} else if (heroType == "Diplomat") {
					heroType = "Medic";
					picDiplomat.setVisible(false);
					picMedic.setVisible(true);
				} else if (heroType == "Tank") {
					heroType = "Diplomat";
					picTank.setVisible(false);
					picDiplomat.setVisible(true);
				} else if (heroType == "Explorer") {
					heroType = "Tank";
					picExplorer.setVisible(false);
					picTank.setVisible(true);
				} else if (heroType == "Lucky") {
					heroType = "Explorer";
					picLucky.setVisible(false);
					picExplorer.setVisible(true);
				}
			}
		});
		btnPreviousHero.setBounds(207, 317, 134, 25);
		setupFrame.getContentPane().add(btnPreviousHero);
		
		//Slider to choose number cities
		JSlider numCities = new JSlider();
		numCities.setMajorTickSpacing(1);
		numCities.setSnapToTicks(true);
		numCities.setPaintTicks(true);
		numCities.setMinimum(3);
		numCities.setMaximum(6);
		numCities.setBounds(379, 46, 200, 16);
		setupFrame.getContentPane().add(numCities);
		
		/** This is the button to add a new hero **/
		JButton btnAddHero = new JButton("Add hero");
		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if inputHeroName.getText().length() 
				Team.addHero(new Hero(inputHeroName.getText(), heroType));
				inputNumHeroes++; 
				
				lblHeroes.setText(Team.getHeroes().toString());
				//heroes.add();
				inputHeroName.setText("");
				
				
				//clear text box
				
			}
		});
		btnAddHero.setBounds(379, 511, 145, 25);
		setupFrame.getContentPane().add(btnAddHero);
		/**END**/
		
		
		
		/** Next HEROO BUTTON**/
		JButton btnNextHero = new JButton("Next Hero");
		btnNextHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroType == "Gambler") {
					heroType = "Medic";
					picGambler.setVisible(false);
					picMedic.setVisible(true);
				} else if (heroType == "Medic") {
					heroType = "Diplomat";
					picMedic.setVisible(false);
					picDiplomat.setVisible(true);
				} else if (heroType == "Diplomat") {
					heroType = "Tank";
					picDiplomat.setVisible(false);
					picTank.setVisible(true);
				} else if (heroType == "Tank") {
					heroType = "Explorer";
					picTank.setVisible(false);
					picExplorer.setVisible(true);
				} else if (heroType == "Explorer") {
					heroType = "Lucky";
					picExplorer.setVisible(false);
					picLucky.setVisible(true);
				} else if (heroType == "Lucky") {
					heroType = "Gambler";
					picLucky.setVisible(false);
					picGambler.setVisible(true);
				}
				
				
			}
		});
		btnNextHero.setBounds(625, 317, 145, 25);
		setupFrame.getContentPane().add(btnNextHero);
		/**END HERE!!!!**/
		
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEnvironment.setTeamName(txtTeamName.getText());
				GameEnvironment.setNumberCities(numCities.getValue());
				//Game_Environment.setNumberHeroes(heroes.size());//length of the list of heroes)
				GameEnvironment.moveToNewCity();
				setupFrame.dispose();
			}
		});
		btnDone.setBounds(841, 570, 117, 25);
		setupFrame.getContentPane().add(btnDone);	
	}
}
