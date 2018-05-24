import java.util.ArrayList;

public class TheMasterMind extends Villain {
	/**
	 * Calls super class (Villain) constructor.
	 * Sets the taunt, possible games to play, name, and damage.
	 */
	public TheMasterMind(ArrayList<String> games) {
		super("You can try...", games, "The Master Mind", 70);
	}
}
