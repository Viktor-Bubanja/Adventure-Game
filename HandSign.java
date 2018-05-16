public enum HandSign {
	PAPER, SCISSORS, ROCK;
	
	public static HandSign parseType(int value) {
		value = Character.toUpperCase(value);
		if (value == 0) 
			return PAPER;
		else if (value == 1)
			return SCISSORS;
		else if (value == 2) 
			return ROCK;
		else 
			return null;
	}
	
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
