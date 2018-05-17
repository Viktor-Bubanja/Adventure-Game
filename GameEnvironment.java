import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameEnvironment {
	private static String teamName;
	private static int numberCities;
	private static ArrayList<Villain> villains = new ArrayList<Villain>();
	private static ArrayList<CityGUI> cities = new ArrayList<CityGUI>();
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
		if (currentCity == numberCities - 1)
			return - 1; //We now at the last city with the supervillain
		else
			return currentCity;
	}
	
	public static String[] getVillainNames() {
		String[] heroNames = new String[villains.size()];
		for (int i = 0; i < villains.size(); i++) {
			heroNames[i] = villains.get(i).getName();
		}
		return heroNames;
	}
	
	private static void makeCities() {
		CityGUI city1 = new CityGUI();
		CityGUI city2 = new CityGUI();
		CityGUI city3 = new CityGUI();
		CityGUI city4 = new CityGUI();
		CityGUI city5 = new CityGUI();
		CityGUI finalCity = new CityGUI();
		cities.add(city1);
		cities.add(city2);
		cities.add(city3);
		cities.add(city4);
		cities.add(city5);
		cities.add(finalCity);
	}
	
	private static void makeVillains() {
		ArrayList<String> games1 = new ArrayList<String>();
		ArrayList<String> games2 = new ArrayList<String>();
		ArrayList<String> games3 = new ArrayList<String>();
		ArrayList<String> games4 = new ArrayList<String>();
		ArrayList<String> games5 = new ArrayList<String>();
		ArrayList<String> games6 = new ArrayList<String>();
		games1.add("paper scissors rock");
		games1.add("guess a number");
		games1.add("Dice game");
		games2.add("guess a number");
		games2.add("paper scissors rock");
		games2.add("guess a number");
		Villain villain1 = new Villain("come here boa", games1, "villain one", 100);
		Villain villain2 = new Villain("oy m8 you wont", games1, "villain two", 50);
		Villain villain3 = new Villain("you a bitch", games1, "villain three", 70);
		Villain villain4 = new Villain("ohh whats that", games1, "villain four", 70);
		Villain villain5 = new Villain("im da bomd you not", games1, "villain five", 70);
		Villain superVillain = new Villain("you a big fat nob", games1, "Super", 70);
		villains.add(villain1);
		villains.add(villain2);
		villains.add(villain3);
		villains.add(villain4);
		villains.add(villain5);
		Collections.shuffle(villains);
		villains.add(superVillain);
	}
	
	public static void main(String[] args) {
		makeVillains();
		makeCities();
		System.out.println(villains.get(0).getName());
		System.out.println(villains.get(1).getName());
		System.out.println(villains.get(2).getName());
		System.out.println(villains.get(3).getName());
		System.out.println(villains.get(4).getName());
		System.out.println(villains.get(5).getName());
		GameSetupGUI.NewScreen(); //start the game already
		
	}
	
	public static void moveToNewCity() {
		// TODO Auto-generated method stub
		cities.get(currentCity).NewScreen();
		
		
		currentCity++;
		
	}
	
}