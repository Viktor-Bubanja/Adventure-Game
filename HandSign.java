/**
 * 
 * Enumerator HandSign
 * Used for converting the villain's and hero's turns between different forms.
 *
 */
public enum HandSign {
	PAPER, SCISSORS, ROCK;
	/**
	 * 
	 * @param int value. Integer representing paper, scissors, or rock.
	 * @return HandSign. Returns either PAPER, SCISSORS, or ROCK.
	 */
	public static HandSign parseType(int value) {
		if (value == 0) 
			return PAPER;
		else if (value == 1)
			return SCISSORS;
		else if (value == 2) 
			return ROCK;
		else 
			return null;
	}
	/**
	 * 
	 * @param HandSign handSign. 
	 * @return String stringValue. Returns either Paper, Scissors, or Rock to display in the Paper Scissors Rock GUI.
	 */
	public static String toString(HandSign handSign) {
		String stringValue;
		if (handSign == PAPER) {
			stringValue = "Paper";
		} else if (handSign == SCISSORS) {
			stringValue = "Scissors"; 
		} else {
			stringValue = "Rock";
		}
		return stringValue;
	}
}
