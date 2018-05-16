import java.util.ArrayList;
import java.util.Scanner;

public class GameEnvironment {
	private static String teamName;
	private static int numberCities;
	private static ArrayList<Villain> villains = new ArrayList<Villain>();
	private static int currentCity = 0;
	
	public static ArrayList<Villain> getVillains() {
		return villains;
	}
	
	public static void setTeamName(String inputName) {
		teamName = inputName;
	}
	public static void setNumberCities(int inputNumberCities) {
		numberCities = inputNumberCities;
	}
	public static String getTeamName() {
		return teamName;
	}
	public static int getCurrentCity() {
		return currentCity;
	}
	
	public GameEnvironment() {
		
		ArrayList<String> games1 = new ArrayList<String>();
		ArrayList<String> games2 = new ArrayList<String>();
		ArrayList<String> games3 = new ArrayList<String>();
		ArrayList<String> games4 = new ArrayList<String>();
		ArrayList<String> games5 = new ArrayList<String>();
		ArrayList<String> games6 = new ArrayList<String>();
		//GuessNumber guessNumberGame = new GuessNumber();
		//games1.add(guessNumberGame);
		games1.add("paper scissors rock");
		games1.add("guess a number");
		games2.add("guess a number");
		games2.add("paper scissors rock");
		games2.add("guess a number");
		Villain villain1 = new Villain("come here boa", games1, "villain one", 100);
		//Villain villain2 = new Villain("oy m8 you wont", games2, "villain two", 50);
		//Villain villain3 = new Villain("you a bitch", "paper scissors rock", "villain three", 70);
		//Villain villain4 = new Villain("come here boy", "rock paper scissors", "villain one", 100);
		//Villain villain5 = new Villain("oy m8 you wont", "guess a number", "villain two", 50);
		//Villain villain6 = new Villain("you a bitch", "paper scissors rock", "villain three", 70);
		villains.add(villain1);
		//villains.add(villain2);
		//villains.add(villain3);
		//villains.add(villain4);
		//villains.add(villain5);
		//villains.add(villain6);
	}
	
}