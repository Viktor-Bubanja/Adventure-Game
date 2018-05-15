import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
	private String name;
	
	private static int getPlayersGuess() {
		Scanner scan = new Scanner(System.in);
		
		int playersGuess = 0;
		System.out.println("Guess a number between 1 and 10.");
		boolean validInput = false;
		while (!validInput) {
			while (!scan.hasNextInt()) {
				System.out.println("That's not a number!");
				scan.next();
			}
			playersGuess = scan.nextInt();
			if (playersGuess > 10 || playersGuess < 1) {
				System.out.println("Your number must be between 1 and 10.");
			} else {
				validInput = true;
			}
		}
		return playersGuess;
	
	}
	public String getName() {
		return name;
	}
	
	private static boolean playRoundOne(int playersGuess, int villainsNumber) {
		if (playersGuess == villainsNumber) {
			System.out.println("You guessed it right!");
			return true;
		} else if (playersGuess < villainsNumber) {
			System.out.println("Too low");
			return false;
		} else {
			System.out.println("Too high");
			return false;
		}
	}
	
	private static boolean playRoundTwo(int playersGuess, int villainsNumber) {
		if (playersGuess == villainsNumber) {
			System.out.println("You guessed it right!");
			return true;
		} else {
			System.out.println("Wrong, you lose");
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		Random random = new Random();
		
		int aRandomNumber = random.nextInt(10);
		aRandomNumber++;
		int playersGuess1 = 0;
		int playersGuess2 = 0;
		playersGuess1 = getPlayersGuess();
		int guesses = 0;
		boolean wonRoundOne = playRoundOne(playersGuess1, aRandomNumber);
		if (!wonRoundOne) {
			System.out.println("You have one more try.");
			playersGuess2 = getPlayersGuess();
			boolean wonRoundTwo = playRoundTwo(playersGuess2, aRandomNumber);
		}
		
		
	}
}
