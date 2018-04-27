import java.util.Scanner;

public class Setup {
	public static String getTeamName() {
		String teamName = null;
		Boolean incorrectInput = true;
		while(incorrectInput) {
			System.out.println("Enter your team name: ");
			Scanner scanner = new Scanner(System.in);
			teamName = scanner.nextLine();
			
			if (teamName.length() >= 2 && teamName.length() <= 10) {
				incorrectInput = false;
				System.out.println("Your team is called " + teamName);
			}
			else {
				System.out.println("Name must be between 2 and 10 characters long.");
			}
		}	
		return teamName;
	}	
	public static int getNumberCities() {
		Boolean incorrectInput = true;
		int numberCities = 0;
		while(incorrectInput) {
			System.out.println("Enter the number of cities your heroes need to explore: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			numberCities = (Integer.parseInt(input));
			if (numberCities <= 6 && numberCities >= 3) {
				incorrectInput = false;
				System.out.println("Num Cities to explore " + numberCities);
			} else {
				System.out.println("Choose between 3 and 6 cities.");
			}
		}
		return numberCities;
	}
	
	public static int getNumberHeroes() {
		Boolean incorrectInput = true;
		int numberHeroes = 0;
		while(incorrectInput) {
			System.out.println("Enter however many Heroes you want on your team: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			numberHeroes = (Integer.parseInt(input));
			if (numberHeroes <= 3 && numberHeroes >= 1) {
				incorrectInput = false;
				System.out.println("You have chosen to take " + numberHeroes + " Heroes on this adventure with you");
			} else {
				System.out.println("You can only have between 1 and 3 Heroes");
			}
		}
		return numberHeroes;
	}
}
