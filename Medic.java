
public class Medic extends Hero{
	/**
	 * Calls super class (Hero) constructor.
	 * Sets the hero's name, type, health, and recovery rate.
	 * @param inputName String
	 */
	public Medic(String inputName) {
		super(inputName, "Medic", 200, 40);
	}
}
