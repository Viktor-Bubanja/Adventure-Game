import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

enum District {
	NORTH, EAST, SOUTH, WEST, HOMEBASE;
	
	public static District parseType(int position) {
		District district = null;
		switch (position) {
		case 0: district =  HOMEBASE;
				break;
		case 1: district =  NORTH;
				break;
		case 2: district =  EAST;
				break;
		case 3: district = SOUTH;
				break;
		case 4: district = WEST;
				break;
		}
		return district;
	}
}

public class City {
	
	public static void main(String[] args) {
		Villain villain; //maybe villains lair


		int currentPosition = 0;
		List<String> positions = new ArrayList<String>();

		positions.add("SHOP");
		positions.add("POWERUPDEN");
		positions.add("HOSPITAL");
		positions.add("VILLAINSLAIR");
		Collections.shuffle(positions);
		positions.add(0,"HOMEBASE");
		System.out.println(positions);
		System.out.println("Where do you wanna go? ");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		System.out.println(positions.get(input));
		

		
		
	}
}
