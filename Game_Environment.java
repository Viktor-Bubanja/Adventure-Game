import java.util.ArrayList;
import java.util.Scanner;

public class Game_Environment {
	private static String teamName;
	private static int numberHeroes;
	private static int numberCities;	
	private static int currentCity = 0;
	
	
	
	private ArrayList<Villain> list_villains = new ArrayList<Villain>();

	
	private void initTeam(String teamName, int numberHeroes) {
		
		//maybs  a for loops asking for here names
		
	}
	

	
	private void move() {
		
	}
	
	
	public static void main(String[] args) {
		
		teamName = Setup.getTeamName();
		numberCities = Setup.getNumberCities();
		numberHeroes = Setup.getNumberHeroes();
	}
	
	
	
}