import java.util.ArrayList;

public class KingFusion extends Villain {
	/**
	 * Calls super class (Villain) constructor.
	 * Sets the taunt, possible games to play, name, and damage.
	 * @param games ArrayList
	 */
	public KingFusion(ArrayList<String> games) {
		super("You spoony bard!", games, "King Fusion", 50);
	}
}
