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
		return currentCityIndex;
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
	
	private void makeVillains() {
		ArrayList<String> games1 = new ArrayList<String>();
		ArrayList<String> games2 = new ArrayList<String>();
		ArrayList<String> games3 = new ArrayList<String>();
		ArrayList<String> games4 = new ArrayList<String>();
		ArrayList<String> games5 = new ArrayList<String>();
		ArrayList<String> games6 = new ArrayList<String>();
		games1.add("paper scissors rock");
		games1.add("guess a number");
		games1.add("Dice game");
		games2.add("guess a number");
		games2.add("paper scissors rock");
		games2.add("guess a number");
		Villain villain1 = new Villain("come here boa", games1, "villain one", 100);
		Villain villain2 = new Villain("oy m8 you wont", games1, "villain two", 50);
		Villain villain3 = new Villain("you a bitch", games1, "villain three", 70);
		Villain villain4 = new Villain("ohh whats that", games1, "villain four", 70);
		Villain villain5 = new Villain("im da bomd you not", games1, "villain five", 70);
		Villain superVillain = new Villain("you a big fat nob", games1, "Super Villain", 150);
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
		BattleWindow battleWindow = new BattleWindow(team, this, cityGui);
		battleWindow.makeVisible();
	}
	public void openGuessNumberGUI(Hero heroPlaying, BattleWindow battleWindow, GameEnvironment gameEnvironmentInput) {
		GuessNumberGUI guessNumberGui = new GuessNumberGUI(heroPlaying, battleWindow, gameEnvironmentInput);
		guessNumberGui.makeVisible();
	}
	
	public void openPaperScissorsRockGUI(Hero heroPlaying, BattleWindow battleWindow, GameEnvironment gameEnvironmentInput) {
		PaperScissorsRockGUI paperScissorsRockGui = new PaperScissorsRockGUI(heroPlaying, battleWindow, gameEnvironmentInput);
		paperScissorsRockGui.makeVisible();
	}
	
	public void openDiceGameGUI(Hero heroPlaying, BattleWindow battleWindow, GameEnvironment gameEnvironmentInput) {
		DiceGameGUI diceGameGui = new DiceGameGUI(heroPlaying, battleWindow, gameEnvironmentInput);
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
		HealingItem smallPotion = new HealingItem(10, 10, 5, "Small Potion");
		HealingItem quickPotion = new HealingItem(25, 10, 2, "Quick Potion");
		HealingItem bigPotion = new HealingItem(40, 20, 10, "Big Potion");
		healingItems.add(smallPotion);
		healingItems.add(quickPotion);
		healingItems.add(bigPotion);
		return healingItems;
	}
	public List<PowerUp> getPowerUpsList() {
		List<PowerUp> powerUps = new ArrayList<PowerUp>();
		PowerUp extraRoll = new PowerUp(30, "Extra Roll");
		PowerUp extraGuess = new PowerUp(50, "Extra Guess");
		PowerUp paperScissorsRockClue = new PowerUp(50, "Clue for Paper Scissors Rock");
		powerUps.add(paperScissorsRockClue);
		powerUps.add(extraGuess);
		powerUps.add(extraRoll);
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