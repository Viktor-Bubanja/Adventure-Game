import java.util.ArrayList;
import java.util.Scanner;

public class Game_Environment {
	private String teamName;
	private int numberHeroes;
	private static int numberCities;	
	
	private ArrayList<Villain> list_villains = new ArrayList<Villain>();

	
	private static void getTeamName() {
		Boolean incorrectInput = true;
		
		while(incorrectInput) {
			System.out.println("Enter your team name: ");
			Scanner scanner = new Scanner(System.in);
			String teamName = scanner.nextLine();
			
			if (teamName.length() >= 2 && teamName.length() <= 10) {
				incorrectInput = false;
				System.out.println("Your team is called " + teamName);
			}
			else {
				System.out.println("Name must be between 2 and 10 characters long.");
			}
		}
		
	}
	
	
	private static void getNumberCities() {
		Boolean incorrectInput = true;
		
		while(incorrectInput) {
			System.out.println("Enter the number of cities your heroes need to explore: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			int numberCities = (Integer.parseInt(input));
			if (numberCities <= 6 && numberCities >= 3) {
				incorrectInput = false;
				System.out.println("Num Cities to explore " + numberCities);
			}
			else {
				System.out.println("Choose between 3 and 6 cities.");
			}
		}
	}
	
	private void getNumberHeroes() {
		
	}
	
	private void initTeam(String teamName, int numberHeroes) {
		
		//maybs  a for loops asking for here names
		
	}
	
	
	public static void main(String[] args) {
		getNumberCities();
		
	}
	
	
	
}
