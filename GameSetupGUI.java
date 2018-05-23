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
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class GameSetupGUI {

	private JFrame setupFrame;
	private JTextField txtTeamName;
	private JTextField inputHeroName;
	private String heroTypeCurrentlySelected = "Gambler";
	private int inputNumHeroes;
	private GameEnvironment gameEnvironment;
	private Team team;
	private JLabel teamLengthRequirementLabel;
	

	public void makeVisible() {
		this.setupFrame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public GameSetupGUI(GameEnvironment gameEnvironmentInput, Team teamInput) {
		gameEnvironment = gameEnvironmentInput;
		team = teamInput;
		initialize();
	}
	
	private void addHeroTypeToTeam(String heroType) {
		if (heroType == "Medic") {
			team.setTeamHasMedic(true);
		} else if (heroType == "Diplomat") {
			team.setTeamHasDiplomat(true);
		} else if (heroType == "Explorer") {
			team.setTeamHasExplorer(true);
		} else if (heroType == "Lucky") {
			team.setTeamHasLucky(true);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setupFrame = new JFrame("Main setup frame");
		setupFrame.setBounds(new Rectangle(100, 100, 1200, 800));
		setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupFrame.getContentPane().setLayout(null);
		
		JLabel lblWhatWouldYou = new JLabel("");
		lblWhatWouldYou.setBounds(2, -7, 378, 59);
		setupFrame.getContentPane().add(lblWhatWouldYou);
		
		txtTeamName = new JTextField();
		txtTeamName.setBounds(450, 252, 300, 25);
		txtTeamName.setText(" ");
		txtTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeamName.setToolTipText("Wowwo cools!");
		setupFrame.getContentPane().add(txtTeamName);
		txtTeamName.setColumns(10);
		
		JLabel fullHeroesLabel = new JLabel("You can't add any more heroes");
		fullHeroesLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		fullHeroesLabel.setBounds(216, 668, 306, 20);
		setupFrame.getContentPane().add(fullHeroesLabel);
		fullHeroesLabel.setVisible(false);
		
		JLabel addSomeHeroesLabel = new JLabel("Add some heroes");
		addSomeHeroesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addSomeHeroesLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		addSomeHeroesLabel.setBounds(480, 700, 240, 45);
		setupFrame.getContentPane().add(addSomeHeroesLabel);
		addSomeHeroesLabel.setVisible(false);
		
		inputHeroName = new JTextField();
		inputHeroName.setBounds(500, 620, 200, 31);
		setupFrame.getContentPane().add(inputHeroName);
		inputHeroName.setColumns(10);

		JTextArea heroesListLabel = new JTextArea();
		heroesListLabel.setEditable(false);
		heroesListLabel.setBounds(12, 22, 477, 72);
		setupFrame.getContentPane().add(heroesListLabel);
		
		JLabel matchedNameLabel = new JLabel("A hero already exists with that name");
		matchedNameLabel.setBounds(175, 627, 314, 16);
		setupFrame.getContentPane().add(matchedNameLabel);
		matchedNameLabel.setVisible(false);
		
		JLabel inputCheck = new JLabel("");
		inputCheck.setBounds(423, 131, 181, 26);
		setupFrame.getContentPane().add(inputCheck);
		
		teamLengthRequirementLabel = new JLabel("Make your team name between two and ten characters");
		//teamLengthRequirementLabel.setForeground(Color.BLACK);
		teamLengthRequirementLabel.setBounds(394, 229, 425, 16);
		setupFrame.getContentPane().add(teamLengthRequirementLabel);
		
		JLabel picGambler = new JLabel("Pic Gambler");
		picGambler.setBounds(942, 338, 147, 50);
		setupFrame.getContentPane().add(picGambler);
		
		JLabel picMedic = new JLabel("Medic Pic");
		picMedic.setVisible(false);
		picMedic.setBounds(1002, 318, 87, 50);
		setupFrame.getContentPane().add(picMedic);
		
		JLabel picDiplomat = new JLabel("Pic Diplomat");
		picDiplomat.setVisible(false);
		picDiplomat.setBounds(993, 397, 166, 31);
		setupFrame.getContentPane().add(picDiplomat);
		
		JLabel picTank = new JLabel("Pic Tank");
		picTank.setVisible(false);
		picTank.setBounds(1042, 295, 117, 31);
		setupFrame.getContentPane().add(picTank);
		
		JLabel picExplorer = new JLabel();
		picExplorer.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/dora.png")));
		picExplorer.setVisible(false);
		
		JLabel picLucky = new JLabel("Pic Lucky");
		picLucky.setVisible(false);
		picLucky.setBounds(987, 412, 117, 50);
		setupFrame.getContentPane().add(picLucky);
		picExplorer.setBounds(522, 338, 240, 270);
		setupFrame.getContentPane().add(picExplorer);
		
		JLabel lblNewLabel = new JLabel("How many cities to explore?");
		lblNewLabel.setBounds(501, 126, 302, 31);
		setupFrame.getContentPane().add(lblNewLabel);
		
		JButton btnPreviousHero = new JButton("Previous Hero");
		btnPreviousHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroTypeCurrentlySelected == "Gambler") {
					heroTypeCurrentlySelected = "Lucky";
					picGambler.setVisible(false);
					picLucky.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Medic") {
					heroTypeCurrentlySelected = "Gambler";
					picMedic.setVisible(false);
					picGambler.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Diplomat") {
					heroTypeCurrentlySelected = "Medic";
					picDiplomat.setVisible(false);
					picMedic.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Tank") {
					heroTypeCurrentlySelected = "Diplomat";
					picTank.setVisible(false);
					picDiplomat.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Explorer") {
					heroTypeCurrentlySelected = "Tank";
					picExplorer.setVisible(false);
					picTank.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Lucky") {
					heroTypeCurrentlySelected = "Explorer";
					picLucky.setVisible(false);
					picExplorer.setVisible(true);
				}
			}
		});
		btnPreviousHero.setBounds(288, 583, 134, 25);
		setupFrame.getContentPane().add(btnPreviousHero);
		
		//Slider to choose number cities
		JSlider numCities = new JSlider();
		numCities.setBackground(Color.WHITE);
		numCities.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		numCities.setPaintLabels(true);
		numCities.setValue(3);
		numCities.setMajorTickSpacing(1);
		numCities.setSnapToTicks(true);
		numCities.setPaintTicks(true);
		numCities.setMinimum(3);
		numCities.setMaximum(6);
		numCities.setBounds(500, 169, 200, 45);
		setupFrame.getContentPane().add(numCities);
		
		/** This is the button to add a new hero **/
		JButton btnAddHero = new JButton("Add hero");
		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String listLabelString = "";
				boolean matched = false;
				if (team.getNumberHeroes() < 3) {
					for (String name: team.getHeroNames()) {
						if (inputHeroName.getText().equals(name)) {
							matched = true;
						}
					}
					if (!matched) {
						matchedNameLabel.setVisible(false);
						team.addHero(new Hero(inputHeroName.getText(), heroTypeCurrentlySelected));
						inputNumHeroes++; 
						addHeroTypeToTeam(heroTypeCurrentlySelected);
						for (Hero hero: team.getHeroes()) {
							listLabelString = listLabelString + hero.toString();
						}
						heroesListLabel.setText(listLabelString);
						inputHeroName.setText("");
					} else {
						matchedNameLabel.setVisible(true);
					}
				} else {
					fullHeroesLabel.setVisible(true);
					matchedNameLabel.setVisible(false);
				}
			}
		});
		btnAddHero.setBounds(540, 660, 120, 40);
		setupFrame.getContentPane().add(btnAddHero);
		
		JButton btnNextHero = new JButton("Next Hero");
		btnNextHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroTypeCurrentlySelected == "Gambler") {
					heroTypeCurrentlySelected = "Medic";
					picGambler.setVisible(false);
					picMedic.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Medic") {
					heroTypeCurrentlySelected = "Diplomat";
					picMedic.setVisible(false);
					picDiplomat.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Diplomat") {
					heroTypeCurrentlySelected = "Tank";
					picDiplomat.setVisible(false);
					picTank.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Tank") {
					heroTypeCurrentlySelected = "Explorer";
					picTank.setVisible(false);
					picExplorer.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Explorer") {
					heroTypeCurrentlySelected = "Lucky";
					picExplorer.setVisible(false);
					picLucky.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Lucky") {
					heroTypeCurrentlySelected = "Gambler";
					picLucky.setVisible(false);
					picGambler.setVisible(true);
				}
			}
		});
		btnNextHero.setBounds(764, 583, 145, 25);
		setupFrame.getContentPane().add(btnNextHero);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean allRequirementsPassed = true;
				if (team.teamHasMedic()) {
					for (Hero hero: team.getHeroes()) {
						hero.increaseMaxHealth(75);
					}
				}
				if (inputNumHeroes == 0) {
					addSomeHeroesLabel.setVisible(true);
					allRequirementsPassed = false;
				}
				if (txtTeamName.getText().length() > 10 || txtTeamName.getText().length() < 2) {
					teamLengthRequirementLabel.setForeground(Color.RED);
					allRequirementsPassed = false;
				}
				if (allRequirementsPassed) {
					gameEnvironment.startTimer();
					gameEnvironment.setTeamName(txtTeamName.getText());
					gameEnvironment.setNumberCities(numCities.getValue());
					gameEnvironment.moveToNewCity(team);
					setupFrame.dispose();
				}
			}
		});
		btnDone.setBounds(980, 680, 180, 60);
		setupFrame.getContentPane().add(btnDone);	
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, -7, 1200, 800);
		setupFrame.getContentPane().add(backgroundPic);
		
		
		
	}
}
