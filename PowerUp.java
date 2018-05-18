
public class PowerUp {
	private double cost;
	private String name;
	
	
	public PowerUp (double costInput, String nameInput) {
		cost = costInput;
		name = nameInput;
	}
	public double getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}
}
