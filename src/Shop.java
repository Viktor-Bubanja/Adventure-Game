import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
	
	private static double buyItem(List<HealingItem> healingItems, List<PowerUp> powerUps, Map map) {
		int numberHealingItems = healingItems.size();
		int numberPowerUps = powerUps.size();
		double cost = 0;
		System.out.println("what do you want to buy?");
		for (int i = 0; i < numberHealingItems; i++) {
			System.out.print(i);
			System.out.print(". A " + healingItems.get(i) + "? It costs $"); //Add the cost
			System.out.println(healingItems.get(i).getCost());
		}
		for (int i = 4; i < numberPowerUps + 4; i++) {
			System.out.print(i);
			System.out.println(". A " + powerUps.get(i) + "? It costs $");
			System.out.print(powerUps.get(i).getCost());
		}
		System.out.print(7);
		System.out.println(". A Map? It costs $20");
		

		Scanner scanner = new Scanner(System.in);
		int toBuy = scanner.nextInt();
		if (toBuy < 4) {
			System.out.println("You bought: ");
			System.out.println(healingItems.get(toBuy));
			cost = healingItems.get(toBuy).getCost();
		}
		
		else if (toBuy < 7) {
			System.out.println("You bought: ");
			System.out.println(powerUps.get(toBuy));
			cost = powerUps.get(toBuy).getCost();
		}
		
		else {
			System.out.println("You bought: ");
			System.out.println(map);
			cost = map.getCost();
		}
		
		return cost;
	}
	
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
		

		double cost = buyItem(healingItems, powerUps, map);
		System.out.println(cost);
		Team.decreaseMoneyBy(cost);
		
		
		
		
		
		
	}
	
	
	
}
