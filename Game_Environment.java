import java.util.ArrayList;
import java.util.Scanner;

public class Game_Environment {
	private String teamName;
	private int numberHeroes;
	private int numberCities;	
	private ArrayList<Villain> list_villains = new ArrayList<Villain>();

	
	private void getTeamName() {
		System.out.println("Enter your team name: ");
		Scanner scanner = new Scanner(System.in);
		String teamName = scanner.nextLine();
		System.out.println("Your team is called " + teamName);
	}
	
	
	private void getNumberCities() {
		
	}
	
	private void getNumberHeroes() {
		
	}
	
	private void initTeam(String teamName, int numberHeroes) {
		
		//maybs  a for loops asking for here names
		
	}
	
	
	
	
	
	
}
