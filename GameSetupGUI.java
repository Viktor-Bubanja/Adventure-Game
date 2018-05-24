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
	/**
	 * Attributes:
	 * The frame for the GameSetup GUI to display.
	 * The game Environment handling the game.
	 * Your team.
 	 * Textfield where you enter your teams name.
 	 * Textfield where you enter your Heroes name.
 	 * Label declaring your team name isnt the correct length (between 2-10)
 	 * number of heroes you added to your team
 	 * currently selected hero, the type of the hero you are about to add
 	*/
	private JFrame setupFrame;
	private GameEnvironment gameEnvironment;
	private Team team;
	private JTextField inputTeamName;
	private JTextField inputHeroName;
	private JLabel teamLengthRequirementLabel;
	private int inputNumHeroes;
	private String heroTypeCurrentlySelected = "Gambler";

	/**
	 * Makes the game Setup GUI visible
	 */
	public void makeVisible() {
		this.setupFrame.setVisible(true);
	}

	/**
	 * @param gameEnvironmentInput GameEnvironment
	 * @param teamInput Team
	 */
	public GameSetupGUI(GameEnvironment gameEnvironmentInput, Team teamInput) {
		gameEnvironment = gameEnvironmentInput;
		team = teamInput;
		initialize();
	}
	/**
	 * adds a Hero of heroType to your team
	 * if you added a hero with a special team affecting power it enables that power
	 * @param heroType String
	 */
	private void addHeroToTeam(String heroType) {
		switch (heroType) {
		case "Gambler":	team.addHero(new Gambler(inputHeroName.getText()));
						break;
		case "Medic":	team.addHero(new Medic(inputHeroName.getText()));
						team.setTeamHasMedic(true);
						break;
		case "Diplomat":team.addHero(new Diplomat(inputHeroName.getText()));
						team.setTeamHasDiplomat(true);
						break;
		case "Tank":	team.addHero(new Tank(inputHeroName.getText()));
						break;
		case "Explorer":team.addHero(new Explorer(inputHeroName.getText()));
						team.setTeamHasExplorer(true);
						break;
		case "Lucky":	team.addHero(new Lucky(inputHeroName.getText()));
						team.setTeamHasLucky(true);
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
		
		inputTeamName = new JTextField(); //Where you input your team name
		inputTeamName.setBounds(450, 200, 300, 25);
		inputTeamName.setText(" ");
		inputTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		setupFrame.getContentPane().add(inputTeamName);
		inputTeamName.setColumns(10);
		
		JLabel fullHeroesLabel = new JLabel("You can't add any more heroes"); //Heroes now full label
		fullHeroesLabel.setForeground(Color.RED);
		fullHeroesLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		fullHeroesLabel.setBounds(216, 668, 306, 20);
		setupFrame.getContentPane().add(fullHeroesLabel);
		fullHeroesLabel.setVisible(false);
		
		JTextArea heroesListLabel = new JTextArea(); //Text area displaying your team attributes
		heroesListLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		heroesListLabel.setRows(4);
		heroesListLabel.setBackground(Color.WHITE);
		heroesListLabel.setOpaque(false);
		heroesListLabel.setEditable(false);
		heroesListLabel.setBounds(35, 349, 387, 143);
		setupFrame.getContentPane().add(heroesListLabel);
		
		JLabel addSomeHeroesLabel = new JLabel("Add some heroes"); //You need to play with at least one hero label
		addSomeHeroesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addSomeHeroesLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		addSomeHeroesLabel.setBounds(480, 700, 240, 45);
		setupFrame.getContentPane().add(addSomeHeroesLabel);
		addSomeHeroesLabel.setVisible(false);
		
		inputHeroName = new JTextField(); // Where you enter your individual hero names
		inputHeroName.setBounds(500, 620, 200, 30);
		setupFrame.getContentPane().add(inputHeroName);
		inputHeroName.setColumns(10);
		
		JLabel matchedNameLabel = new JLabel("A hero already exists with that name"); //Label stating you needa pic a new name
		matchedNameLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		matchedNameLabel.setForeground(Color.RED);
		matchedNameLabel.setBounds(175, 627, 314, 16);
		setupFrame.getContentPane().add(matchedNameLabel);
		matchedNameLabel.setVisible(false);
		
		teamLengthRequirementLabel = new JLabel("Make your team name between two and ten characters"); //Label stating the team name requirements
		teamLengthRequirementLabel.setBounds(394, 240, 425, 16);
		setupFrame.getContentPane().add(teamLengthRequirementLabel);
		
		JLabel picGambler = new JLabel("");
		picGambler.setHorizontalAlignment(SwingConstants.CENTER);
		picGambler.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/gambler.png"))); //Picture of the gambler Hero
		picGambler.setBounds(500, 310, 200, 270);
		setupFrame.getContentPane().add(picGambler);
		
		JLabel picMedic = new JLabel("");
		picMedic.setHorizontalAlignment(SwingConstants.CENTER);
		picMedic.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/medic.png"))); //Picture of the medic Hero
		picMedic.setVisible(false);
		picMedic.setBounds(500, 310, 200, 270);
		setupFrame.getContentPane().add(picMedic);
		
		JLabel picDiplomat = new JLabel("");
		picDiplomat.setHorizontalAlignment(SwingConstants.CENTER);
		picDiplomat.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/diplomat.png"))); //Picture of the diplomat Hero
		picDiplomat.setVisible(false);
		picDiplomat.setBounds(500, 310, 200, 270);
		setupFrame.getContentPane().add(picDiplomat);
		
		JLabel picTank = new JLabel("");
		picTank.setHorizontalAlignment(SwingConstants.CENTER);
		picTank.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/tank.png"))); //Picture of the tank Hero
		picTank.setVisible(false);
		picTank.setBounds(440, 276, 270, 340);// Hes a little bigger
		setupFrame.getContentPane().add(picTank);
		
		JLabel picExplorer = new JLabel();
		picExplorer.setHorizontalAlignment(SwingConstants.CENTER);
		picExplorer.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/dora.png"))); //Picture of the explorer Hero
		picExplorer.setVisible(false);
		picExplorer.setBounds(500, 310, 200, 270);
		setupFrame.getContentPane().add(picExplorer);
		
		JLabel picLucky = new JLabel("");
		picLucky.setHorizontalAlignment(SwingConstants.CENTER);
		picLucky.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/lucky.png")));//picture of the lucky Hero
		picLucky.setVisible(false);
		picLucky.setBounds(510, 310, 200, 270);
		setupFrame.getContentPane().add(picLucky);
		
		
		JLabel howManyCitiesLabel = new JLabel("How many cities do you want to explore?");  //label for how many cities selection slider
		howManyCitiesLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		howManyCitiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		howManyCitiesLabel.setBounds(400, 50, 400, 31);
		setupFrame.getContentPane().add(howManyCitiesLabel);
		
		JLabel enterNameLabel = new JLabel("Enter a name!");  //Label stating you didnt enter a name
		enterNameLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		enterNameLabel.setForeground(Color.RED);
		enterNameLabel.setBounds(749, 620, 150, 30);
		setupFrame.getContentPane().add(enterNameLabel);
		enterNameLabel.setVisible(false);
		
		//Slider to choose number cities
		JSlider numCities = new JSlider();
		numCities.setOpaque(false);
		numCities.setBackground(Color.WHITE);
		numCities.setPaintLabels(true);
		numCities.setValue(3);
		numCities.setMajorTickSpacing(1);
		numCities.setSnapToTicks(true);
		numCities.setPaintTicks(true);
		numCities.setMinimum(3);
		numCities.setMaximum(6);
		numCities.setBounds(500, 100, 200, 45);
		setupFrame.getContentPane().add(numCities);
		
		/**
		 * These are the text areas for describing the attributes of each heroType
		 */
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
		
		
		JButton previousHeroButton = new JButton("Previous Hero"); //Change the hero you want to add to your team
		previousHeroButton.addActionListener(new ActionListener() {
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
		previousHeroButton.setBounds(288, 583, 135, 25);
		setupFrame.getContentPane().add(previousHeroButton);
		
		JButton addHeroButton = new JButton("Add hero"); //Adds the selected herotype and name typed in the input box
		addHeroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String listLabelString = "";
				boolean matched = false;
				boolean nameLongEnough = false;
				if (team.getNumberHeroes() < 3) {
					for (String name: team.getHeroNames()) {
						if (inputHeroName.getText().equals(name)) { //Cant have same name twice
							matched = true;
						}
					}
					if (inputHeroName.getText().length() > 0) { //Needs to actaully have a name
						nameLongEnough = true;
						enterNameLabel.setVisible(false);
					}
					if (!matched && nameLongEnough) {
						matchedNameLabel.setVisible(false);
						addHeroToTeam(heroTypeCurrentlySelected);
						inputNumHeroes++; 
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
		addHeroButton.setBounds(540, 660, 120, 40);
		setupFrame.getContentPane().add(addHeroButton);
		
		JButton nextHeroButton = new JButton("Next Hero"); //Change the hero you want to add to your team in the other direction
		nextHeroButton.addActionListener(new ActionListener() {
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
		nextHeroButton.setBounds(764, 583, 135, 25);
		setupFrame.getContentPane().add(nextHeroButton);
		
		JButton doneButton = new JButton("Play!");  //Enters the game
		doneButton.addActionListener(new ActionListener() {
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
				if (inputTeamName.getText().length() > 10 || inputTeamName.getText().length() < 2) {
					teamLengthRequirementLabel.setForeground(Color.RED);
					allRequirementsPassed = false;
				}
				if (allRequirementsPassed) {
					gameEnvironment.startTimer();	//starts the timer for the game
					gameEnvironment.setTeamName(inputTeamName.getText());//
					gameEnvironment.setNumberCities(numCities.getValue());
					gameEnvironment.moveToNewCity(team);
					setupFrame.dispose();
				}
			}
		});
		doneButton.setBounds(980, 680, 180, 60);
		setupFrame.getContentPane().add(doneButton);	
		
		JLabel pickYourTeamLabel = new JLabel("Pick your team name");  // Label intructions to choose  a team name
		pickYourTeamLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		pickYourTeamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pickYourTeamLabel.setBounds(500, 173, 200, 15);
		setupFrame.getContentPane().add(pickYourTeamLabel);

		JLabel backgroundPic = new JLabel("");   // The background Picture of the scroll
		backgroundPic.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPic.setIcon(new ImageIcon(GameSetupGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		setupFrame.getContentPane().add(backgroundPic);	
	}
}
