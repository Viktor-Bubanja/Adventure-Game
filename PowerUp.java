
public class PowerUp {
	private int cost;
	private String name;
	
	
	public PowerUp (int costInput, String nameInput) {
		cost = costInput;
		name = nameInput;
	}
	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}
	public void reduceCost(int reduceBy) {
		cost -= reduceBy;
	}
}
