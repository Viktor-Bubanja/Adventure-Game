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
		Villain villain1 = new Villain("come here boy", "rock paper scissors", "villain one", 100);
		Villain villain2 = new Villain("oy m8 you wont", "guess a number", "villain two", 50);
		Villain villain3 = new Villain("you a bitch", "paper scissors rock", "villan three", 70);
		Villain villain4 = new Villain("come here boy", "rock paper scissors", "villain one", 100);
		Villain villain5 = new Villain("oy m8 you wont", "guess a number", "villain two", 50);
		Villain villain6 = new Villain("you a bitch", "paper scissors rock", "villan three", 70);
		villains.add(villain1);
		villains.add(villain2);
		villains.add(villain3);
		villains.add(villain4);
		villains.add(villain5);
		villains.add(villain6);
	}
	
}