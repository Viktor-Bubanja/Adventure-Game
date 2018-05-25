
public class PowerUp {
	/**
	 * Attributes: 
	 * Cost of the power up.
	 * Name of the power up.
	 */
	private int cost;
	private String name;
	
	/**
	 * @param costInput int 
	 * @param nameInput String 
	 */
	public PowerUp (int costInput, String nameInput) {
		cost = costInput;
		name = nameInput;
	}
	/**
	 * @return cost int 
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * @return name String 
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param reduceBy int 
	 */
	public void reduceCost(int reduceBy) {
		cost -= reduceBy;
	}
}
