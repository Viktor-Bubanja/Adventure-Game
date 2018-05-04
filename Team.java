import java.util.ArrayList;

public class Team {
	
	public String teamName;
	public static int numberHeroes;	
	public static double money = 100.00; //Some random amount later
	public static ArrayList<Object> heroes = new ArrayList<Object>();
	private ArrayList<Map> maps = new ArrayList<Map>();
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
		System.out.println("money left = ");
		System.out.println(money);
	}
	
	public static void usePowerUp(int i) {
		powerUps.remove(i);
	}
	public static void main(String[] args) {
		Team neewTeam = new Team("trht", 2);
	}
	
	
	
	
}
