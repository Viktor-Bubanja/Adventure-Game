
public class HealingItem {
	/**
	 * Attributes:
	 * how long the specific healing item takes to apply to the hero
	 * Cost of buying the item in the shop
	 * the amount the healing item heals the hero by
	 * the name of the healing item
	 */
	private int applicationTime;
	private int cost;
	private double healingAmount;
	private String name;
	
	/**
	 * @param costInput int
	 * @param healingAmountInput double
	 * @param applicationTimeInput int
	 * @param nameInput String
	 */
	public HealingItem (int costInput, double healingAmountInput, int applicationTimeInput, String nameInput) {
		cost = costInput;
		healingAmount = healingAmountInput;
		applicationTime = applicationTimeInput;
		name = nameInput;
	}
	/**
	 * @return int gets the cost of the healing item
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * @param reduceBy int reduces the cost of the healing item by this amount
	 */
	public void reduceCost(int reduceBy) {
		cost -= reduceBy;
	}
	/**
	 * @return int gets the application time of the healing item
	 */
	public int getApplicationTime() {
		return applicationTime;
	}
	/**
	 * @return double gets the amount to heal the hero by
	 */
	public double getHealingAmount() {
		return healingAmount;
	}
	/**
	 * @return String gets the name of the healing item
	 */
	public String getName() {
		return name;
	}
}
