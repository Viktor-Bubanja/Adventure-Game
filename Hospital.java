import java.util.Scanner;

public class Hospital {
	
	
	
	public Hospital() {
		healHero();
	}
	
	
	public static void healHero() {
		
	
	
		System.out.println("Select a hero to heal");
		for (int i = 0; i < Team.numberHeroes; i++) {
			System.out.println(Team.heroes.get(i));
			
		}
				
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		Team.heroes.get(input);
		
		
		
		
		System.out.println("Select a potion to use on ");

	}
	
	
}
