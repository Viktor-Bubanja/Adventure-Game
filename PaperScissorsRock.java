import java.util.Random;
import java.util.Scanner;

enum HandSign {
	PAPER, SCISSORS, ROCK;
	
	public static HandSign parseType(char value) {
		value = Character.toUpperCase(value);
		if (value == 'R') 
			return ROCK;
		else if (value == 'S')
			return SCISSORS;
		else if (value == 'P') 
			return PAPER;
		else 
			return null;
	}
}

public class PaperScissorsRock {
	public static char returnMove(int value) {
		

		if (value == 0) 
			return 'R';
		else if (value == 1)
			return 'S';
		else if (value == 2)
			return 'P';
		else
			return ' ';
	}
	public static int returnRoundResult(HandSign playerMove, HandSign computerMove) {
		int loss = 0;
		int win = 1;
		int tie = 2;
		int result = 0;
		//System.out.println(computerMove == HandSign.ROCK);
		//System.out.println(playerMove == HandSign.SCISSORS);
		System.out.print("Hero: ");
		System.out.print(playerMove);
		System.out.println();
		System.out.print("Villain: ");
		System.out.print(computerMove);
		System.out.println();
        if (computerMove == playerMove) {
            System.out.println("Tie!");
            return tie;
        } else if (computerMove == HandSign.SCISSORS && playerMove == HandSign.PAPER) {
            System.out.println("Scissor cuts paper, you lose!");
          
            return loss;
        } else if (computerMove == HandSign.PAPER && playerMove == HandSign.ROCK) {
            System.out.println("Paper wraps rock, you lose!");
           
            return loss;
        } else if (computerMove == HandSign.ROCK && playerMove == HandSign.SCISSORS) {
            System.out.println("Rock breaks scissor, you lose!");

            return loss;
        } else {
            System.out.println("You won!");

            return win;
        }
	}

	
	public static void main(String[] args) {
		Random random = new Random();
		boolean gameOver = false;
		HandSign playerMove = null;
		HandSign computerMove = null;
		int numTrials = 0;
		int numComputerWon = 0;
		int numPlayerWon = 0;
		int round = 0;
		
		int moveNum = 0;
		boolean validInput = false;
		Scanner scan = new Scanner(System.in);
		
		HandSign player = null;
		
		System.out.println("P for paper, S for scissors, R for rock");
		while (!gameOver) {
			playerMove = null;
			while (playerMove == null) {
				System.out.println("Enter your move: ");
				playerMove = HandSign.parseType(scan.next().charAt(0));
			}
			
			int aRandomNumber = random.nextInt(3);
			
			computerMove = HandSign.parseType(returnMove(aRandomNumber));

			round = returnRoundResult(playerMove, computerMove);
			if (round == 0) 
				numComputerWon++;
			else if (round == 1)
				numPlayerWon++;
			if (numComputerWon == 3 || numPlayerWon == 3) {
				gameOver = true;
			}

			
		}
	}
}