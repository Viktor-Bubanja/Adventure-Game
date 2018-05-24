import java.util.ArrayList;

public class SuperVillain extends Villain {
	/**
	 * Calls super class (Villain) constructor.
	 * Sets the taunt, possible games to play, name, and damage.
	 */
	public SuperVillain(ArrayList<String> games) {
		super("I am the almighty!", games, "Super Villain", 100);
	}

}
