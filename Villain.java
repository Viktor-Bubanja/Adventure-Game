import java.util.ArrayList;

public class Villain {
	/**
	 * Taunt of the Villain.
	 */
	private String taunt;
	/**
	 *  List of Strings which represent the possible games the Villain might play.
	 */
	private ArrayList<String> games;
	/**
	 *  Villains name
	 */
	private String name;
	/**
	 *  Damage that the Villain deals.
	 */
	private int damage;
	/**
	 *  Number of remaining lives of the Villain.
	 */
	private int lives = 3;
	/**
	 * 
	 * @return Taunt of the Villain
	 */
	public String getTaunt() {
		return taunt;
	}
	/**
	 * 
	 * @return Name of Villain
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @return List of possible games Villain might play.
	 */
	public ArrayList<String> getGames() {
		return games;
	}
	/**
	 *  Subtracts one life from the Villains remaining lives. 
	 */
	public void loseLife() {
		lives--;
	}
	/**
	 * 
	 * @return Number of lives Villain has remaining.
	 */
	public int getLives() {
		return lives;
	}
	/**
	 * 
	 * @return Damage that the Villain deals.
	 */
	public int getDamage() {
		return damage;
	}
	/** Constructor for the Villain class.
	 *  The Super Villain has five lives, all other Villains have three.
	 * @param inputTaunt Taunt that the Villain says in the BattleScreen.
	 * @param inputGames List of possible games the Villain might play.
	 * @param inputName Villains name
	 * @param inputDamage Damage that the Villain deals.
	 */
	public Villain(String inputTaunt, ArrayList<String> inputGames, String inputName, int inputDamage) {
		taunt = inputTaunt;
		games = inputGames;
		name = inputName;
		damage = inputDamage;
		if (name == "Super Villain") {
			lives = 5;
		}
	}
}
