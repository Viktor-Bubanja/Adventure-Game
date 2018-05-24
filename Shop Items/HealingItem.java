
public class HealingItem {

	private int applicationTime;
	private int cost;
	private double healingAmount;
	private String name;
	
	
	public HealingItem (int costInput, double healingAmountInput, int applicationTimeInput, String nameInput) {
		cost = costInput;
		healingAmount = healingAmountInput;
		applicationTime = applicationTimeInput;
		name = nameInput;
	}
	public int getCost() {
		return cost;
	}
	public void reduceCost(int reduceBy) {
		cost -= reduceBy;
	}
	public int getApplicationTime() {
		return applicationTime;
	}
	public double getHealingAmount() {
		return healingAmount;
	}
	public String getName() {
		return name;
	}
}
