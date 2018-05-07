import java.util.ArrayList;

public class Team {
	
	public String teamName;
	public static int numberHeroes;	
	private static double money = 100.00; //Some random amount later
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	private static int maps = 0;
	private static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private static ArrayList<HealingItem> healingItems = new ArrayList<HealingItem>();
	
	
	public Team(String name, int number_of_heroes) {
		teamName = name;
		numberHeroes = number_of_heroes;
	}
	public static void setNumberHeroes(int num) {
		numberHeroes = num;
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
			
			
			
	public static void main(String[] args) {
		Team neewTeam = new Team("trht", 2);
	}

	
	
	
	
}
