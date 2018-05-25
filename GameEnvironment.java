import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class GameEnvironment {
	
	/**
 	* Attributes:
 	* The current city you are fighting in.
	* Your team
	* The name of your team
	* A list of all the villains you can fight in this game
	* A list of all the cities you can possibly visit
	* The number of cities you need to visit in order to finish the game
	* The index of the city you are currently in
	* The maximum number of cities you could select to play
	* A list of the possible backgrounds for the citygui
	* Storing the time you start the game at
	*/
	private CityGUI currentCity;
	private Team team;
	private String teamName;
	private ArrayList<Villain> villains;
	private ArrayList<CityGUI> cities;
	private int numberCities;
	private int currentCityIndex = -1;
	private final int MAXNUMBERCITIES = 6;
	private ArrayList<String> cityBackgrounds;
	private long lStartTime;

	/**
	 * @return ArrayList  The list of Villains
	 */
	public ArrayList<Villain> getVillains() {
		return villains;
	}
	/**
	 * Stores the start time of the game
	 */
	public void startTimer() {
		lStartTime = System.currentTimeMillis();
	}
	/**
	 * @return String gets a formatted string of the time taken to complete the game in the format mm:ss
	 */
	public String getEndTime() {
		long endTimeLong = System.currentTimeMillis() - lStartTime;
		String displayTime = String.format("%d min, %d sec", 
				(int) ((endTimeLong / (1000 * 60)) % 60),
				(int) ((endTimeLong / 1000) % 60)
				);
		return displayTime;
	}
	/**
	 * @param inputName String  Sets the name of your team
	 */
	public void setTeamName(String inputName) {
		teamName = inputName;
	}
	/**
	 * @param inputNumberCities int Sets the number of cities
	 */
	public void setNumberCities(int inputNumberCities) {
		numberCities = inputNumberCities;
	}
	/**
	 * @return String gets the name of the team
	 */
	public String getTeamName() {
		return teamName;
	}
	/**
	 * @return int the index of the current city, MAXNUMBERCITIES - 1 if its already the final city
	 */
	public int getCurrentCityIndex() {
		if (finalCity()) {
			return MAXNUMBERCITIES - 1;
		} else {
			return currentCityIndex;
		}
	}
	/**
	 * @return CityGUI gets the current city
	 */
	public CityGUI getCurrentCity() {
		return currentCity;
	}
	/**
	 * @return ArrayList gets the list of cities
	 */
	public ArrayList<CityGUI> getCityList() {
		return cities;
	}
	/**
	 * @return int gets the number of cities
	 */
	public int getNumberCities() {
		return numberCities;
	}
	/**
	 * @return String[] gets a list of the hero names to display in a comboBox
	 */
	public String[] getVillainNames() {
		String[] heroNames = new String[villains.size()];
		for (int i = 0; i < villains.size(); i++) {
			heroNames[i] = villains.get(i).getName();
		}
		return heroNames;
	}
	/**
	 * Initializes the villains and their favorite games to play
	 * Randomizes the first 5 villains and adds a supervillain
	 */
	public void makeVillains() {
		ArrayList<String> games1 = new ArrayList<String>();
		ArrayList<String> games2 = new ArrayList<String>();
		ArrayList<String> games3 = new ArrayList<String>();
		ArrayList<String> games4 = new ArrayList<String>();
		ArrayList<String> games5 = new ArrayList<String>();
		
		games1.add("paper scissors rock");		//The Indigo Spark and Super
		games1.add("guess a number");			//The Indigo Spark and Super
		games1.add("Dice game");				//The Indigo Spark and Super
		games2.add("guess a number");			//The Master Mind
		games2.add("Dice game");				//The Master Mind
		games3.add("guess a number");			//Mysterio
		games4.add("Dice game");				//King Fusion 
		games4.add("paper scissors rock");		//King Fusion
		games5.add("paper scissors rock");		//Edward Scissor Hands
		
		Villain villain1 = new TheIndigoSpark(games1);
		Villain villain2 = new TheMasterMind(games2);
		Villain villain3 = new Mysterio(games3);
		Villain villain4 = new KingFusion(games4);
		Villain villain5 = new EdwardScissorHands(games5);
		Villain superVillain = new SuperVillain(games1);

		villains.add(villain1);
		villains.add(villain2);
		villains.add(villain3);
		villains.add(villain4);
		villains.add(villain5);
		Collections.shuffle(villains);
		villains.add(superVillain);
	}
	/**
	 * @param index int Your current city index
	 * @return Villain A villain that will be the super villain if you are in the last city, otherwise a random villain
	 */
	public Villain getVillain(int index) {
		if (finalCity()) {
			return villains.get(MAXNUMBERCITIES-1);
		} else {
			return villains.get(index);
		}	
	}
	/**
	 * makes the list of city backgrounds. The fiery lava background will always be the last item. The rest are shuffled
	 */
	public void makeCityBackgrounds() {
		cityBackgrounds.add("/Images/blue.jpg");
		cityBackgrounds.add("/Images/couldyBackground.png");
		cityBackgrounds.add("/Images/atlantic.jpeg");
		cityBackgrounds.add("/Images/forestbackground.jpg");
		cityBackgrounds.add("/Images/teampunkBackground.jpg");
		Collections.shuffle(cityBackgrounds);
		cityBackgrounds.add("/Images/lava.jpg");
	}
	/**
	 * @return ArrayList  gets the list of backgrounds
	 */
	public ArrayList<String> getCitybackgrounds() {
		return cityBackgrounds;
	}
	/**
	 * @param index int the index of the city that needs a background
	 * @return String gets the address of the background
	 */
	public String getBackground(int index) {
		if (finalCity()) {
			return cityBackgrounds.get(MAXNUMBERCITIES - 1);
		} else {
			return cityBackgrounds.get(index);
		}	
	}
	/**
	 * @return Team gets the team you're playing with
	 */
	public Team getTeam() {
		return team;
	}
	/**
	 * @param args Main!!!
	 */
	public static void main(String[] args) {
		GameEnvironment gameEnvironment = new GameEnvironment();
		gameEnvironment.cityBackgrounds = new ArrayList<String>(); //Initializes cityBackgrounds and fills it up
		gameEnvironment.makeCityBackgrounds();
		gameEnvironment.villains = new ArrayList<Villain>(); //Initializes villains and fills it up
		gameEnvironment.makeVillains();
		gameEnvironment.cities = new ArrayList<CityGUI>();//Initializes cities
		gameEnvironment.team = new Team(gameEnvironment);//Initializes Team
		GameSetupGUI gameSetupGui = new GameSetupGUI(gameEnvironment, gameEnvironment.team); //Opens the setup GUI
		gameSetupGui.makeVisible();
	}
	/**
	 * Moves a new city by opening its GUI, checks if we have won the game
	 * @param teamInput Team
	 */
	public void moveToNewCity(Team teamInput) {
		if (finalCity()) {
			this.gameWon();
		} else {
			currentCityIndex++; 
			CityGUI cityGui = new CityGUI(teamInput, this);
			cityGui.makeCityVisible();
			currentCity = cityGui;
		}
	}
	/**
	 * Opens up a new shop frame
	 * @param team Team
	 * @param cityGui CityGUI
	 */
	public void openShopScreen(Team team, CityGUI cityGui) {
		ShopGUI shopGui = new ShopGUI(team, this, cityGui);
		shopGui.makeVisible();
	} 
	/**
	 * Opens up a new PowerUpDen frame
	 * @param team Team
	 * @param cityGui CityGUI
	 */
	public void openPowerUpDenScreen(Team team, CityGUI cityGui) {
		PowerUpDenGUI powerUpDenGui = new PowerUpDenGUI(team, cityGui);
		powerUpDenGui.makeVisible();
	}
	/**
	 * Opens up a new Hospital frame
	 * @param team Team
	 * @param cityGui CityGUI
	 */
	public void openHospitalScreen(Team team, CityGUI cityGui) {
		HospitalGUI hospitalGui = new HospitalGUI(team, cityGui);
		hospitalGui.makeVisible();
	}
	/**
	 * Opens up a new Villains Lair frame
	 * @param team Team
	 * @param cityGui CityGUI
	 */
	public void openLairScreen(Team team, CityGUI cityGui) {
		LairGUI lair = new LairGUI(team, this, cityGui);
		lair.makeVisible();
	}
	/**
	 * Opens up a new BattleWindow frame
	 * @param team Team
	 * @param cityGui CityGUI
	 */
	public void openBattleWindow(Team team, CityGUI cityGui) {
		BattleWindowGUI battleWindowGui = new BattleWindowGUI(team, this, cityGui);
		battleWindowGui.makeVisible();
	}
	/**
	 * Opens a new guess number game frame
	 * @param heroPlaying Hero
	 * @param battleWindowGui BattleWindowGUI
	 * @param gameEnvironmentInput GameEnvironment
	 */
	public void openGuessNumberGUI(Hero heroPlaying, BattleWindowGUI battleWindowGui, GameEnvironment gameEnvironmentInput) {
		GuessNumberGUI guessNumberGui = new GuessNumberGUI(heroPlaying, battleWindowGui, gameEnvironmentInput);
		guessNumberGui.makeVisible();
	}
	/**
	 * Opens a new paper Scissors rock game frame
	 * @param heroPlaying Hero
	 * @param battleWindowGui BattleWindowGUI
	 * @param gameEnvironmentInput GameEnvironment
	 */
	public void openPaperScissorsRockGUI(Hero heroPlaying, BattleWindowGUI battleWindowGui, GameEnvironment gameEnvironmentInput) {
		PaperScissorsRockGUI paperScissorsRockGui = new PaperScissorsRockGUI(heroPlaying, battleWindowGui, gameEnvironmentInput);
		paperScissorsRockGui.makeVisible();
	}
	/**
	 * Opens a new Dice game frame
	 * @param heroPlaying Hero
	 * @param battleWindowGui BattleWindowGUI
	 * @param gameEnvironmentInput GameEnvironment
	 */
	public void openDiceGameGUI(Hero heroPlaying, BattleWindowGUI battleWindowGui, GameEnvironment gameEnvironmentInput) {
		DiceGameGUI diceGameGui = new DiceGameGUI(heroPlaying, battleWindowGui, gameEnvironmentInput);
		diceGameGui.makeVisible();
	}
	/**
	 * Opens a you won frame. passes gameEnvironment in
	 */
	public void gameWon() {
		GameWonGUI gameWonGui = new GameWonGUI(this);
		gameWonGui.makeVisible();
	}
	/**
	 * Opens a you lost frame
	 */
	public void gameLost() {
		GameLostGUI gameLostGui = new GameLostGUI();
		gameLostGui.makeVisible();
	}
	
	/**
	 * Makes a list of healing items
	 * @return List healingItems
	 */
	public List<HealingItem> getHealingItemsList() {
		List<HealingItem> healingItems = new ArrayList<HealingItem>();
		HealingItem smallPotion = new HealingItem(10, 0.25, 16, "Small Potion");
		HealingItem quickPotion = new HealingItem(25, 0.25, 8, "Quick Potion");
		HealingItem bigPotion = new HealingItem(40, 0.5, 32, "Big Potion");
		healingItems.add(smallPotion);
		healingItems.add(quickPotion);
		healingItems.add(bigPotion);
		return healingItems;
	}
	/**
	 * Makes a list of power Ups
	 * @return List powerUps
	 */
	public List<PowerUp> getPowerUpsList() {
		List<PowerUp> powerUps = new ArrayList<PowerUp>();
		PowerUp goldenDie = new PowerUp(40, "Golden Die");
		PowerUp extraGuess = new PowerUp(40, "Extra Guess");
		PowerUp paperScissorsRockClue = new PowerUp(60, "Clue for Paper Scissors Rock");
		powerUps.add(paperScissorsRockClue);
		powerUps.add(extraGuess);
		powerUps.add(goldenDie);
		return powerUps;
	}
	/**
	 * @return boolean true if you are in the final city, false if else
	 */
	public boolean finalCity() {
		if (currentCityIndex == numberCities - 1) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * @return boolean false if you do not have any heros left. true if you have at least one still alive
	 */
	public boolean herosLeft() {
		if (team.getHeroes().size() == 0) {
			return false;
		} else {
			return true;
		}
	}


	
}