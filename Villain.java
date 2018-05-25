import java.util.ArrayList;

public class Villain {
	/**
	 * 
	 * Attributes:
	 * Taunt of the Villain
	 * ArrayList containing possible games the Villain might play.
	 * Villains name
	 * Villains damage
	 * Number of lives left. Initially three if the Villain is not a Super Villain, otherwise five.
	 */
	private String taunt;
	private ArrayList<String> games;
	private String name;
	private int damage;
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
	 * @return String Name of Villain
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @return ArrayList List of possible games Villain might play.
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
	 * @return int Number of lives Villain has remaining.
	 */
	public int getLives() {
		return lives;
	}
	/**
	 * 
	 * @return int Damage that the Villain deals.
	 */
	public int getDamage() {
		return damage;
	}
	/** Constructor for the Villain class.
	 *  The Super Villain has five lives, all other Villains have three.
	 * @param inputTaunt String  Taunt that the Villain says in the BattleScreen.
	 * @param inputGames ArrayList List of possible games the Villain might play.
	 * @param inputName String  Villains name
	 * @param inputDamage int  Damage that the Villain deals.
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
