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
	
	public static int moveBackToHome () {
		return 0;
	}
	
	public static int getMove() {
		System.out.println("Where do you wanna go? ");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		
		return input;
	}
	
	public static void enterDistrict(int currentPosition, List<String> positions) {
		if (positions.get(currentPosition) == "SHOP") {
			Shop shop = new Shop();
		} else if (positions.get(currentPosition) == "POWERUPDEN") {
			//call enter shop
			
		} else if (positions.get(currentPosition) == "HOSPITAL") {
			
		} else if (positions.get(currentPosition) == "VILLAINSLAIR") {
			
		} else if (positions.get(currentPosition) == "HOMEBASE") {

			
		}
		moveBackToHome();
	}
	
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
		//RANDOM EVENT
		currentPosition = getMove();
		System.out.println(currentPosition);
		System.out.println(positions.get(currentPosition));
		enterDistrict(currentPosition, positions);
		
		

		
		
	}
}
