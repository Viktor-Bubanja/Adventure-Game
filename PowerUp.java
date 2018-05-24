
public class PowerUp {
	/**
	 * Attributes: 
	 * Cost of the power up.
	 * Name of the power up.
	 */
	private int cost;
	private String name;
	
	/**
	 * @param int costInput
	 * @param String nameInput
	 */
	public PowerUp (int costInput, String nameInput) {
		cost = costInput;
		name = nameInput;
	}
	/**
	 * @return int cost
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param int reduceBy
	 */
	public void reduceCost(int reduceBy) {
		cost -= reduceBy;
	}
}
