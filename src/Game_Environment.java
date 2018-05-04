import java.util.ArrayList;
import java.util.Scanner;

public class Game_Environment {
	private static String teamName;
	private static int numberHeroes; 
	private static int numberCities;	
	/**
	//private static ArrayList<City> cities = new ArrayList<City>();
	//private static City currentCity = cities.get(0);
	
	
	private ArrayList<Villain> list_villains = new ArrayList<Villain>();

	
	private void initTeam(String teamName, int numberHeroes) {
		
		//maybs  a for loops asking for here names
		
	}
	**/
	public static void setTeamName(String name) {
		teamName = name;
		
	}
	public static String getTeamName() {
		return teamName;
	}
	
	public static void setNumberCities(int inputNumberCities) {
		numberCities = inputNumberCities;
	}
	
	public static void setNumberHeroes(int inputNumberHeroes) {
		numberHeroes = inputNumberHeroes;
	}
	
	public static void main(String[] args) {
		
		teamName = Setup.getTeamName();
		numberCities = Setup.getNumberCities();
		numberHeroes = Setup.getNumberHeroes();
	}
	
	
	
}