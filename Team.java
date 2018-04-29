import java.util.ArrayList;

public class Team {
	
	public String teamName;
	private int numberHeroes;	
	public static double money = 100.00; //Some random amount later
	private static ArrayList<Hero>	heroes = new ArrayList<Hero>();
	private ArrayList<Map> maps = new ArrayList<Map>();
	private static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private static ArrayList<HealingItem> healingItems = new ArrayList<HealingItem>();
	
	
	private void Team(String name, int number_of_heroes) {
		teamName = name;
		numberHeroes = number_of_heroes;

	}
	

	public static void decreaseMoneyBy(double cost) {
		money -= cost;
		System.out.println("money left = ");
		System.out.println(money);
	}
	
	public static void usePowerUp(int i) {
		powerUps.remove(i);
	}
	
	
	
	
}
