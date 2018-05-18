import java.util.ArrayList;

public class Team {
	
	public String teamName;
	private static int numberHeroes;	
	private static double money = 300.00; //Some random amount later
	private static int maps = 0;
	private static ArrayList<Hero> heroes = new ArrayList<Hero>();
	private static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private static ArrayList<HealingItem> healingItems = new ArrayList<HealingItem>();
	
	
	public Team(String name, int number_of_heroes) {
		teamName = name;
		numberHeroes = number_of_heroes;
	}

	public static void decreaseMoneyBy(double cost) {
		money -= cost;
	}
	
	public static void usePowerUp(int i) {
		powerUps.remove(i);
	}
	
	public static void addHealingItem(HealingItem healingItem) {
		healingItems.add(healingItem);
	}
	
	public static void addPowerUp(PowerUp powerUp) {
		powerUps.add(powerUp);
	}
	
	public static double getMoney() {
		return money;
	}
	public static void addMap() {
		maps++;
	}
			
	public static int getNumberHeroes() {
		return heroes.size();
	}
	
	public static String[] getHeroNames() {
		String[] heroNames = new String[heroes.size()];
		for (int i = 0; i < heroes.size(); i++) {
			heroNames[i] = heroes.get(i).getName();
		}
		return heroNames;
	}
	public static String[] getHealingItemNames() {
		String[] healingItemNames = new String[healingItems.size()];
		for (int i = 0; i < healingItems.size(); i++ ) {
			healingItemNames[i] = healingItems.get(i).getName();
		}
		return healingItemNames;
	}
	public static String[] getPowerUpNames() {
		String[] powerUpNames = new String[powerUps.size()];
		for (int i = 0; i < powerUps.size(); i++ ) {
			powerUpNames[i] = powerUps.get(i).getName();
		}
		return powerUpNames;
	}
	
	public static ArrayList<HealingItem> getHealingItems() {
		return healingItems;
	}
	
	public static ArrayList<Hero> getHeroes() {
		return heroes;
	}
	public static void addHero(Hero hero) {
		heroes.add(hero);
	}
	
	public static ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}
	public static void killHero(Hero hero) {
		heroes.remove(hero);
		if (heroes.size() == 0) {
			GameEnvironment.endGame();
		}
	}
			
	public static void main(String[] args) {
		Team newTeam = new Team("trht", 2);
	}

	
	
	
	
}
