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
import javax.swing.UIManager;

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
	private void addHeroToTeam(String heroType) {
		switch (heroType) {
		case "Gambler":	team.addHero(new Gambler(inputHeroName.getText()));
						break;
		case "Medic":	team.addHero(new Medic(inputHeroName.getText()));
						break;
		case "Diplomat":team.addHero(new Diplomat(inputHeroName.getText()));
						break;
		case "Tank":	team.addHero(new Tank(inputHeroName.getText()));
						break;
		case "Explorer":team.addHero(new Explorer(inputHeroName.getText()));
						break;
		case "Lucky":	team.addHero(new Lucky(inputHeroName.getText()));
						break;
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
		
		txtTeamName = new JTextField();
		txtTeamName.setBounds(450, 200, 300, 25);
		txtTeamName.setText(" ");
		txtTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeamName.setToolTipText("Wowwo cools!");
		setupFrame.getContentPane().add(txtTeamName);
		txtTeamName.setColumns(10);
		
		JLabel fullHeroesLabel = new JLabel("You can't add any more heroes");
		fullHeroesLabel.setForeground(Color.RED);
		fullHeroesLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		fullHeroesLabel.setBounds(216, 668, 306, 20);
		setupFrame.getContentPane().add(fullHeroesLabel);
		fullHeroesLabel.setVisible(false);
		
		JTextArea heroesListLabel = new JTextArea();
		heroesListLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		heroesListLabel.setRows(4);
		heroesListLabel.setBackground(Color.WHITE);
		heroesListLabel.setOpaque(false);
		heroesListLabel.setEditable(false);
		heroesListLabel.setBounds(35, 349, 387, 143);
		setupFrame.getContentPane().add(heroesListLabel);
		
		JLabel addSomeHeroesLabel = new JLabel("Add some heroes");
		addSomeHeroesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addSomeHeroesLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		addSomeHeroesLabel.setBounds(480, 700, 240, 45);
		setupFrame.getContentPane().add(addSomeHeroesLabel);
		addSomeHeroesLabel.setVisible(false);
		
		inputHeroName = new JTextField();
		inputHeroName.setBounds(500, 620, 200, 30);
		setupFrame.getContentPane().add(inputHeroName);
		inputHeroName.setColumns(10);
		
		JLabel matchedNameLabel = new JLabel("A hero already exists with that name");
		matchedNameLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		matchedNameLabel.setForeground(Color.RED);
		matchedNameLabel.setBounds(175, 627, 314, 16);
		setupFrame.getContentPane().add(matchedNameLabel);
		matchedNameLabel.setVisible(false);
		
		teamLengthRequirementLabel = new JLabel("Make your team name between two and ten characters");
		//teamLengthRequirementLabel.setForeground(Color.BLACK);
		teamLengthRequirementLabel.setBounds(394, 240, 425, 16);
		setupFrame.getContentPane().add(teamLengthRequirementLabel);
		
		JLabel picGambler = new JLabel("");
		picGambler.setHorizontalAlignment(SwingConstants.CENTER);
		picGambler.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/gambler.png")));
		picGambler.setBounds(500, 310, 200, 270);
		setupFrame.getContentPane().add(picGambler);
		
		JLabel picMedic = new JLabel("");
		picMedic.setHorizontalAlignment(SwingConstants.CENTER);
		picMedic.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/medic.png")));
		picMedic.setVisible(false);
		picMedic.setBounds(500, 310, 200, 270);
		setupFrame.getContentPane().add(picMedic);
		
		JLabel picDiplomat = new JLabel("");
		picDiplomat.setHorizontalAlignment(SwingConstants.CENTER);
		picDiplomat.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/diplomat.png")));
		picDiplomat.setVisible(false);
		picDiplomat.setBounds(500, 310, 200, 270);
		setupFrame.getContentPane().add(picDiplomat);
		
		JLabel picTank = new JLabel("");
		picTank.setHorizontalAlignment(SwingConstants.CENTER);
		picTank.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/tank.png")));
		picTank.setVisible(false);
		picTank.setBounds(440, 276, 270, 340);
		setupFrame.getContentPane().add(picTank);
		
		JLabel picExplorer = new JLabel();
		picExplorer.setHorizontalAlignment(SwingConstants.CENTER);
		picExplorer.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/dora.png")));
		picExplorer.setVisible(false);
		
		JLabel picLucky = new JLabel("");
		picLucky.setHorizontalAlignment(SwingConstants.CENTER);
		picLucky.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/lucky.png")));
		picLucky.setVisible(false);
		picLucky.setBounds(510, 310, 200, 270);
		setupFrame.getContentPane().add(picLucky);
		picExplorer.setBounds(500, 310, 200, 270);
		setupFrame.getContentPane().add(picExplorer);
		
		JLabel lblNewLabel = new JLabel("How many cities do you want to explore?");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(400, 50, 400, 31);
		setupFrame.getContentPane().add(lblNewLabel);
		
		JLabel enterNameLabel = new JLabel("Enter a name!");
		enterNameLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		enterNameLabel.setForeground(Color.RED);
		enterNameLabel.setBounds(749, 620, 150, 30);
		setupFrame.getContentPane().add(enterNameLabel);
		enterNameLabel.setVisible(false);
		
		JTextArea luckyDescriptionTextArea = new JTextArea();
		luckyDescriptionTextArea.setEditable(false);
		luckyDescriptionTextArea.setText("Type: Lucky \n\nMaximum Health: 250 \n\nRecovery Rate: 20 \n\nSpecial Ability: Increases \nmoney reward for defeating\na Villain by 100.");
		luckyDescriptionTextArea.setRows(4);
		luckyDescriptionTextArea.setOpaque(false);
		luckyDescriptionTextArea.setFont(new Font("Dialog", Font.BOLD, 12));
		luckyDescriptionTextArea.setBackground(Color.WHITE);
		luckyDescriptionTextArea.setBounds(745, 349, 200, 182);
		setupFrame.getContentPane().add(luckyDescriptionTextArea);
		luckyDescriptionTextArea.setVisible(false);
		
		JTextArea medicDescriptionTextArea = new JTextArea();
		medicDescriptionTextArea.setText("Type: Medic \n\nMaximum Health: 200 \n\nRecovery Rate: 40 \n\nSpecial Ability: Increases \neveryone's max health by \n75.");
		medicDescriptionTextArea.setRows(4);
		medicDescriptionTextArea.setOpaque(false);
		medicDescriptionTextArea.setFont(new Font("Dialog", Font.BOLD, 12));
		medicDescriptionTextArea.setEditable(false);
		medicDescriptionTextArea.setBackground(Color.WHITE);
		medicDescriptionTextArea.setBounds(745, 349, 200, 182);
		setupFrame.getContentPane().add(medicDescriptionTextArea);
		medicDescriptionTextArea.setVisible(false);
		
		JTextArea gamblerDescriptionTextArea = new JTextArea();
		gamblerDescriptionTextArea.setText("Type: Gambler \n\nMaximum Health: 150 \n\nRecovery Rate: 20 \n\nSpecial Ability: Extra guess\nduring Guess the Number. \nHigher chance of winning \nat Dice Game.");
		gamblerDescriptionTextArea.setRows(4);
		gamblerDescriptionTextArea.setFont(new Font("Dialog", Font.BOLD, 12));
		gamblerDescriptionTextArea.setBackground(Color.WHITE);
		gamblerDescriptionTextArea.setOpaque(false);
		gamblerDescriptionTextArea.setEditable(false);
		gamblerDescriptionTextArea.setBounds(745, 349, 200, 182);
		gamblerDescriptionTextArea.setVisible(true);
		
		JTextArea explorerDescriptionTextArea = new JTextArea();
		explorerDescriptionTextArea.setText("Type: Explorer \n\nMaximum Health: 200 \n\nRecovery Rate: 20 \n\nSpecial Ability: Has a map \nfor every city.");
		explorerDescriptionTextArea.setRows(4);
		explorerDescriptionTextArea.setOpaque(false);
		explorerDescriptionTextArea.setFont(new Font("Dialog", Font.BOLD, 12));
		explorerDescriptionTextArea.setEditable(false);
		explorerDescriptionTextArea.setBackground(Color.WHITE);
		explorerDescriptionTextArea.setBounds(745, 349, 200, 182);
		setupFrame.getContentPane().add(explorerDescriptionTextArea);
		setupFrame.getContentPane().add(gamblerDescriptionTextArea);
		explorerDescriptionTextArea.setVisible(false);
		
		JTextArea tankDescriptionTextArea = new JTextArea();
		tankDescriptionTextArea.setText("Type: Tank \n\nMaximum Health: 400 \n\nRecovery Rate: 10 \n\nSpecial Ability: High \nmaximum health.");
		tankDescriptionTextArea.setRows(4);
		tankDescriptionTextArea.setOpaque(false);
		tankDescriptionTextArea.setFont(new Font("Dialog", Font.BOLD, 12));
		tankDescriptionTextArea.setEditable(false);
		tankDescriptionTextArea.setBackground(Color.WHITE);
		tankDescriptionTextArea.setBounds(745, 349, 200, 182);
		setupFrame.getContentPane().add(tankDescriptionTextArea);
		tankDescriptionTextArea.setVisible(false);
		

		
		JTextArea diplomatDescriptionTextArea = new JTextArea();
		diplomatDescriptionTextArea.setText("Type: Diplomat \n\nMaximum Health: 200 \n\nRecovery Rate: 20 \n\nSpecial Ability: Decreases \nprices in the Shop.");
		diplomatDescriptionTextArea.setRows(4);
		diplomatDescriptionTextArea.setOpaque(false);
		diplomatDescriptionTextArea.setFont(new Font("Dialog", Font.BOLD, 12));
		diplomatDescriptionTextArea.setEditable(false);
		diplomatDescriptionTextArea.setBackground(Color.WHITE);
		diplomatDescriptionTextArea.setBounds(745, 349, 200, 182);
		setupFrame.getContentPane().add(diplomatDescriptionTextArea);
		diplomatDescriptionTextArea.setVisible(false);
		
		JButton btnPreviousHero = new JButton("Previous Hero");
		btnPreviousHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroTypeCurrentlySelected == "Gambler") {
					heroTypeCurrentlySelected = "Lucky";
					picGambler.setVisible(false);
					gamblerDescriptionTextArea.setVisible(false);
					picLucky.setVisible(true);
					luckyDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Medic") {
					heroTypeCurrentlySelected = "Gambler";
					picMedic.setVisible(false);
					medicDescriptionTextArea.setVisible(false);
					picGambler.setVisible(true);
					gamblerDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Diplomat") {
					heroTypeCurrentlySelected = "Medic";
					picDiplomat.setVisible(false);
					diplomatDescriptionTextArea.setVisible(false);
					picMedic.setVisible(true);
					medicDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Tank") {
					heroTypeCurrentlySelected = "Diplomat";
					picTank.setVisible(false);
					tankDescriptionTextArea.setVisible(false);
					picDiplomat.setVisible(true);
					diplomatDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Explorer") {
					heroTypeCurrentlySelected = "Tank";
					picExplorer.setVisible(false);
					explorerDescriptionTextArea.setVisible(false);
					picTank.setVisible(true);
					tankDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Lucky") {
					heroTypeCurrentlySelected = "Explorer";
					picLucky.setVisible(false);
					luckyDescriptionTextArea.setVisible(false);
					picExplorer.setVisible(true);
					explorerDescriptionTextArea.setVisible(true);
				}
			}
		});
		btnPreviousHero.setBounds(288, 583, 135, 25);
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
		numCities.setBounds(500, 100, 200, 45);
		setupFrame.getContentPane().add(numCities);
		
		JButton btnAddHero = new JButton("Add hero");
		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String listLabelString = "";
				boolean matched = false;
				boolean nameLongEnough = false;
				if (team.getNumberHeroes() < 3) {
					for (String name: team.getHeroNames()) {
						if (inputHeroName.getText().equals(name)) {
							matched = true;
							
						}
					}
					if (inputHeroName.getText().length() > 0) {
						nameLongEnough = true;
						enterNameLabel.setVisible(false);
					}
					if (!matched && nameLongEnough) {
						matchedNameLabel.setVisible(false);
						addHeroToTeam(heroTypeCurrentlySelected);	
						inputNumHeroes++; 
						addHeroTypeToTeam(heroTypeCurrentlySelected);
						for (Hero hero: team.getHeroes()) {
							listLabelString = listLabelString + hero.toString();
						}
						heroesListLabel.setText(listLabelString);
						inputHeroName.setText("");
					} else {
						if (matched) {
							matchedNameLabel.setVisible(true);
						}
						if (!nameLongEnough) {
							enterNameLabel.setVisible(true);
						}
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
					gamblerDescriptionTextArea.setVisible(false);
					picMedic.setVisible(true);
					medicDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Medic") {
					heroTypeCurrentlySelected = "Diplomat";
					picMedic.setVisible(false);
					medicDescriptionTextArea.setVisible(false);
					picDiplomat.setVisible(true);
					diplomatDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Diplomat") {
					heroTypeCurrentlySelected = "Tank";
					picDiplomat.setVisible(false);
					diplomatDescriptionTextArea.setVisible(false);
					picTank.setVisible(true);
					tankDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Tank") {
					heroTypeCurrentlySelected = "Explorer";
					picTank.setVisible(false);
					tankDescriptionTextArea.setVisible(false);
					picExplorer.setVisible(true);
					explorerDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Explorer") {
					heroTypeCurrentlySelected = "Lucky";
					picExplorer.setVisible(false);
					explorerDescriptionTextArea.setVisible(false);
					picLucky.setVisible(true);
					luckyDescriptionTextArea.setVisible(true);
				} else if (heroTypeCurrentlySelected == "Lucky") {
					heroTypeCurrentlySelected = "Gambler";
					picLucky.setVisible(false);
					luckyDescriptionTextArea.setVisible(false);
					picGambler.setVisible(true);
					gamblerDescriptionTextArea.setVisible(true);
				}
			}
		});
		btnNextHero.setBounds(764, 583, 135, 25);
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
		
		JLabel lblPickYourTeam = new JLabel("Pick your team name");
		lblPickYourTeam.setFont(new Font("Dialog", Font.BOLD, 17));
		lblPickYourTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickYourTeam.setBounds(500, 173, 200, 15);
		setupFrame.getContentPane().add(lblPickYourTeam);
		

		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPic.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		setupFrame.getContentPane().add(backgroundPic);	
	}
}
