import java.util.ArrayList;
import java.util.Scanner;

public class Team {
	
	public String teamName;
	public static int numberHeroes;	
	public static double money = 100.00; //Some random amount later
	public static ArrayList<Hero>	heroes = new ArrayList<Hero>();
	//private ArrayList<Map> maps = new ArrayList<Map>();
	private static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private static ArrayList<HealingItem> healingItems = new ArrayList<HealingItem>();
	
	
	public Team(String name, int number_of_heroes) {
		teamName = name;
		numberHeroes = number_of_heroes;
		
		/*
		for (int i= 0; i < numberHeroes; i++) {
			
			System.out.println("Enter the name of your hero");
			Scanner scanner = new Scanner(System.in);
			String heroName = scanner.nextLine();
			Hero hero = new Hero(heroName);
		}
		*/
		Hero hero0 = new Hero("Ryan", "Gambler");
		Hero hero1 = new Hero("Viktor", "Tank");
		Hero hero2 = new Hero("Bob", "Medic");
		heroes.add(hero0);
		heroes.add(hero1);
		heroes.add(hero2);

	}
	

	public static void decreaseMoneyBy(double cost) {
		money -= cost;
		System.out.println("money left = ");
		System.out.println(money);
	}
	
	public static void usePowerUp(int i) {
		powerUps.remove(i);
	}
	
	public static void useHealingItem() {
		
		
		
	}
	
	
}
