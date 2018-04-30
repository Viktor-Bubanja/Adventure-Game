import java.util.ArrayList;
import java.util.Scanner;

public class Game_Environment {
	private static String teamName;
	private static int numberHeroes;
	private static int numberCities;	
	
	private static ArrayList<City> cities = new ArrayList<City>();
	//private static City currentCity = cities.get(0);
	
	
	private ArrayList<Villain> list_villains = new ArrayList<Villain>();

	
	//private void initTeam(String teamName, int numberHeroes) {
		
		//Maybe  a for loops asking for here names
		
	//}
	
	private void move() {
		
	}
	
	
	public static void main(String[] args) {
		
		teamName = Setup.getTeamName();
		numberCities = Setup.getNumberCities();
		numberHeroes = Setup.getNumberHeroes();
		Team team = new Team(teamName, numberHeroes);
		City city1 = new City();
	}
	
	
	
}