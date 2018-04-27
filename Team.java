import java.util.ArrayList;

public class Team {
	
	public String teamName;
	private int numberHeroes;	
	public static double money = 100.00; //Some random amount later
	private ArrayList<Hero>	list_of_heroes = new ArrayList<Hero>();
	private ArrayList<Map> list_of_maps = new ArrayList<Map>();
	private ArrayList<PowerUp> list_powerUps = new ArrayList<PowerUp>();
	private ArrayList<HealingItem> list_healingItems = new ArrayList<HealingItem>();
	
	
	private void Team(String name, int number_of_heroes) {
		teamName = name;
		numberHeroes = number_of_heroes;
		
		
		
	}
	

	public static void decreaseMoneyBy(int cost) {
		money -= cost;
		System.out.println("money left = ");
		System.out,println(money);
		
	}
	
	
	
	
}
