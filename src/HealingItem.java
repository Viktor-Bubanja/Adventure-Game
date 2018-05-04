
public class HealingItem {

	private int applicationTime;
	private double cost;
	private int healingAmount;
	private String name;
	
	
	public HealingItem (double costInput, int healingAmountInput, int applicationTimeInput, String nameInput) {
		cost = costInput;
		healingAmount = healingAmountInput;
		applicationTime = applicationTimeInput;
		name = nameInput;
	}
	public double getCost() {
		return cost;
	}
	public int applicationTime() {
		return applicationTime;
	}
	public int healingAmount() {
		return healingAmount;
	}
	public String toString() {
		return name;
	}
}
