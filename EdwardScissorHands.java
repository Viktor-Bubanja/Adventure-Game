import java.util.ArrayList;

public class EdwardScissorHands extends Villain {
	/**
	 * Calls super class (Villain) constructor.
	 * sets the villains taunt, possible games, name, damage
	 * @param games ArrayList
	 */
	public EdwardScissorHands(ArrayList<String> games) {
	
		super("I'm gonna cut you up", games, "Edward Scissor Hands", 70);
	}
}
