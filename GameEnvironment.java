import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class GameEnvironment {
	private String teamName;
	private int numberCities;
	private ArrayList<Villain> villains = new ArrayList<Villain>();
	private ArrayList<CityGUI> cities = new ArrayList<CityGUI>();
	private int currentCityIndex = -1;
	private final int MAXNUMBERCITIES = 6;
	private Team team;
	private CityGUI currentCity;
	private ArrayList<String> cityBackgrounds = new ArrayList<String>();
	private long lStartTime;

	public ArrayList<Villain> getVillains() {
		return villains;
	}
	public void startTimer() {
		lStartTime = System.currentTimeMillis();
	}
	public String getEndTime() {
		long endTimeLong = System.currentTimeMillis() - lStartTime;
		String displayTime = String.format("%d min, %d sec", 
				(int) ((endTimeLong / (1000 * 60)) % 60),
				(int) ((endTimeLong / 1000) % 60)
				);
		return displayTime;
	}
	
	public void setTeamName(String inputName) {
		teamName = inputName;
	}
	public void setNumberCities(int inputNumberCities) {
		numberCities = inputNumberCities;
	}
	public String getTeamName() {
		return teamName;
	}
	public int getCurrentCityIndex() {
		if (currentCityIndex == numberCities - 1) {
			return MAXNUMBERCITIES - 1;
		} else {
			return currentCityIndex;
		}
	}
	public CityGUI getCurrentCity() {
		return currentCity;
	}
	public ArrayList<CityGUI> getCityList() {
		return cities;
	}
	public int getNumberCities() {
		return numberCities;
	}
	
	public String[] getVillainNames() {
		String[] heroNames = new String[villains.size()];
		for (int i = 0; i < villains.size(); i++) {
			heroNames[i] = villains.get(i).getName();
		}
		return heroNames;
	}
	
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
		games5.add("paper scissors rock");		//Edward Scissor Hands
		games4.add("Dice game");				//King Fusion 
		games4.add("paper scissors rock");
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
	
	public Villain getVillain(int index) {
		if (finalCity()) {
			return villains.get(MAXNUMBERCITIES-1);
		} else {
			return villains.get(index);
		}	
	}
	public void makeCityBackgrounds() {
		cityBackgrounds.add("/Images/blue.jpg");
		cityBackgrounds.add("/Images/couldyBackground.png");
		cityBackgrounds.add("/Images/atlantic.jpeg");
		cityBackgrounds.add("/Images/forestbackground.jpg");
		cityBackgrounds.add("/Images/teampunkBackground.jpg");
		cityBackgrounds.add("/Images/slime.png");
		Collections.shuffle(cityBackgrounds);
		cityBackgrounds.add("/Images/lava.jpg");
	}
	public ArrayList<String> getCitybackgrounds() {
		return cityBackgrounds;
	}
	public String getBackground(int index) {
		if (finalCity()) {
			return cityBackgrounds.get(MAXNUMBERCITIES);
		} else {
			return cityBackgrounds.get(index);
		}	
	}
	
	public Team getTeam() {
		return team;
	}

	public static void main(String[] args) {
		GameEnvironment gameEnvironment = new GameEnvironment();
		gameEnvironment.makeVillains();
		gameEnvironment.team = new Team(gameEnvironment);
		GameSetupGUI gameSetupGui = new GameSetupGUI(gameEnvironment, gameEnvironment.team);
		gameEnvironment.makeCityBackgrounds();
		gameSetupGui.makeVisible();
	}
	public void moveToNewCity(Team teamInput) {
		if (finalCity()) {
			this.gameWon();
		} else {
			currentCityIndex++; 
			CityGUI cityGui = new CityGUI(teamInput, this);
			cityGui.makeWindowVisible();
			currentCity = cityGui;
		}
		
	}
	public void openShopScreen(Team team, CityGUI cityGui) {
		ShopGUI shopGui = new ShopGUI(team, this, cityGui);
		shopGui.makeVisible();
	} 
	public void openPowerUpDenScreen(Team team, CityGUI cityGui) {
		PowerUpDenGUI powerUpDenGui = new PowerUpDenGUI(team, this, cityGui);
		powerUpDenGui.makeVisible();
	}
	public void openHospitalScreen(Team team, CityGUI cityGui) {
		HospitalGUI hospitalGui = new HospitalGUI(team, this, cityGui);
		hospitalGui.makeVisible();
	}
	public void openLairScreen(Team team, CityGUI cityGui) {
		LairGUI lair = new LairGUI(team, this, cityGui);
		lair.makeVisible();
	}
	public void openBattleWindow(Team team, CityGUI cityGui) {
		BattleWindowGUI battleWindowGui = new BattleWindowGUI(team, this, cityGui);
		battleWindowGui.makeVisible();
	}
	public void openGuessNumberGUI(Hero heroPlaying, BattleWindowGUI battleWindowGui, GameEnvironment gameEnvironmentInput) {
		GuessNumberGUI guessNumberGui = new GuessNumberGUI(heroPlaying, battleWindowGui, gameEnvironmentInput);
		guessNumberGui.makeVisible();
	}
	
	public void openPaperScissorsRockGUI(Hero heroPlaying, BattleWindowGUI battleWindowGui, GameEnvironment gameEnvironmentInput) {
		PaperScissorsRockGUI paperScissorsRockGui = new PaperScissorsRockGUI(heroPlaying, battleWindowGui, gameEnvironmentInput);
		paperScissorsRockGui.makeVisible();
	}
	
	public void openDiceGameGUI(Hero heroPlaying, BattleWindowGUI battleWindowGui, GameEnvironment gameEnvironmentInput) {
		DiceGameGUI diceGameGui = new DiceGameGUI(heroPlaying, battleWindowGui, gameEnvironmentInput);
		diceGameGui.makeVisible();
	}
	public void gameWon() {
		GameWonGUI gameWonGui = new GameWonGUI(this);
		gameWonGui.makeVisible();
	}
	public void gameLost() {
		GameLostGUI gameLostGui = new GameLostGUI();
		gameLostGui.makeVisible();
	}
	
	
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
	public boolean finalCity() {
		if (currentCityIndex == numberCities - 1) {
			return true;
		} else {
			return false;
		}
	}
	public boolean herosLeft() {
		if (team.getHeroes().size() == 0)
			return false;
		else
			return true;
	}


	
}