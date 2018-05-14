
public class Villain {
	private String taunt;
	private String favouriteGame;
	private String name;
	private int damage;
	//Might need boolean for supervillain
	
	public String getTaunt() {
		return taunt;
	}
	public String getName() {
		return name;
	}
	public String getFavouriteGame() {
		return favouriteGame;
	}
	public Villain(String inputTaunt, String inputFavouriteGame, String inputName, int inputDamage) {
		taunt = inputTaunt;
		favouriteGame = inputFavouriteGame;
		name = inputName;
		damage = inputDamage;
	}
}
