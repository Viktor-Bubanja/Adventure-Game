import java.util.ArrayList;

public class Mysterio extends Villain {
	/**
	 * Calls super class (Villain) constructor.
	 * Sets the taunt, possible games to play, name, and damage.
	 * @param games ArrayList
	 */
	public Mysterio(ArrayList<String> games) {
		super("I am so Mysterious", games, "Mysterio", 70);
	}
}
