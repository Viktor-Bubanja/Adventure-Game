import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Hero {
	private String name;
	private String type;
	private int maxHealth;
	private int currentHealth;
	private int recoveryRate;
	private boolean hasPaperScissorsRockPowerUp = false;
	private boolean hasGuessNumberPowerUp = false;
	private boolean hasDiceGamePowerUp = false;
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	
	public Hero(String inputName, String inputType) {
		name = inputName;
		type = inputType;
		if (type == "Gambler") {
			maxHealth = 200;
			recoveryRate = 8;
		} else if (type == "Medic") {
			maxHealth = 150;
			recoveryRate = 15;
		} else if (type == "Diplomat") {
			maxHealth = 200;
			recoveryRate = 8;
		} else if (type == "Tank") {
			maxHealth = 400;
			recoveryRate = 10;
		} else if (type == "Explorer") {
			maxHealth = 300;
			recoveryRate = 8;
		} else if (type == "Lucky") {
			maxHealth = 250;
			recoveryRate = 9;
		}
		currentHealth = maxHealth;
	}
	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}
	public void addPaperScissorsRockPowerUp() {
		hasPaperScissorsRockPowerUp = true;
	}
	public void  addGuessNumberPowerUp() {
		hasGuessNumberPowerUp = true;
	}

	
	public void heal(int amountToHeal) {
		currentHealth += amountToHeal;
	}
	public int getHealth() {
		return currentHealth;
	}
	
	public String toString() {
		String toReturn;
		toReturn = "Heroes name: " + name + ". \n Herotype: " + type + ". \nHe has " + Integer.toString(currentHealth) + " health.";
		return toReturn;
	}
	
	public String getName() {
		return name;
	}
	public void doDamage(int damage) {
		currentHealth -= damage;
		if (currentHealth <= 0) {
			Team.killHero(this);
			BattleWindow.updateHeroComboBox(this);
			
		}
	}
	public void addDiceGamePowerUp() {
		hasDiceGamePowerUp = true;
		
	}
	public boolean getHasPaperScissorsRockPowerUp() {
		return hasPaperScissorsRockPowerUp;
	}
	public void setHasPaperScissorsRockPowerUp(boolean hasPaperScissorsRockPowerUp) {
		this.hasPaperScissorsRockPowerUp = hasPaperScissorsRockPowerUp;
	}
	public boolean getHasGuessNumberPowerUp() {
		return hasGuessNumberPowerUp;
	}
	public void setHasGuessNumberPowerUp(boolean hasGuessNumberPowerUp) {
		this.hasGuessNumberPowerUp = hasGuessNumberPowerUp;
	}
	public boolean getHasDiceGamePowerUp() {
		return hasDiceGamePowerUp;
	}
	public void setHasDiceGamePowerUp(boolean hasDiceGamePowerUp) {
		this.hasDiceGamePowerUp = hasDiceGamePowerUp;
	}
	
}
	
/**
public class Tank {
	private int health = 400;
	private int recovery_rate = 10;
	private String ability_description = "Tank has higher health and recovery rate.";
}

public class Gambler {
	private int health = 200;
	private int recovery_rate = 8;
	private String ability_description = "Gambler has an extra dice roll and an extra guess at the guess the number game.");
	
}
public class Explorer {
	private int health = 300;
	private int recovery_rate = 8;
	private String ability_description = "Explorer knows the layout of every city.";
}
public class Medic{
	private int health = 150;
	private int recovery_rate = 15;
	private String ability_description = "Medic increases everyones recovery rate by 5, and healing items increase health by more.";
	
}
public class Diplomat {
	private int health = 200;
	private int recovery_rate = 8;
	private String ability_description = "Diplomat decreases the cost of items in the store, and can skip one Villain per game (not including super vilain).";
	
}
public class Lucky {
	private int health = 250;
	private int recovery_rate = 9;
	private String ability_description = "Lucky gets more money from winning each battle.";
}

**/ 

