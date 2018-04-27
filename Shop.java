import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
	
	public Shop() {
		Map map = new Map();
		HealingItem smallPotion = new HealingItem(10.00, 1, 20, "Small Potion");
		HealingItem quickPotion = new HealingItem(25.00, 1, 5, "Quick Potion");
		HealingItem bigPotion = new HealingItem(40.00, 2, 10, "Big Potion");
		List<HealingItem> healingItems = new ArrayList<HealingItem>();
		healingItems.add(smallPotion);
		healingItems.add(quickPotion);
		healingItems.add(bigPotion);
		
		List<PowerUp> powerUps = new ArrayList<PowerUp>(); 
		

		//Add all item names
		
		int numberHealingItems = healingItems.size();
		int numberPowerUps = powerUps.size();
		
		System.out.println("what do you want to buy?");
		
		for (int i = 0; i < numberHealingItems; i++) {
			System.out.print(i);
			System.out.println(". A " + healingItems.get(i) + "? It costs $"); //Add the cost
			//System.out.print(numberHealingItems.get(i).getCost());
		}
		for (int i = 4; i < numberPowerUps + 4; i++) {
			System.out.print(i);
			System.out.println(". A " + powerUps.get(i) + "? It costs $");
			//System.out.print(numberPowerUps.get(i).getCost());
		}
		System.out.print(7);
		System.out.println(". A Map? It costs $20");
		

		Scanner scanner = new Scanner(System.in);
		int toBuy = scanner.nextInt();
		if toBuy < 4;
		
		else if toBuy < 7
		
		else if toBuy < 8
		
		else picAnother
		Team.decreaseMoneyBy(20);
		
		
		
		
		
		
	}
	
	
	
}
