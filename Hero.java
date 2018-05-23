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
			maxHealth = 150;
			recoveryRate = 8;
		} else if (type == "Medic") {
			maxHealth = 200;
			recoveryRate = 15;
		} else if (type == "Diplomat") {
			maxHealth = 200;
			recoveryRate = 8;
		} else if (type == "Tank") {
			maxHealth = 400;
			recoveryRate = 10;
		} else if (type == "Explorer") {
			maxHealth = 200;
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
	public void increaseMaxHealth(int increaseBy) {
		maxHealth += increaseBy;
	}
	public void decreaseMaxHealth(int decreaseBy) {
		maxHealth -= decreaseBy;
	}
	public void addPaperScissorsRockPowerUp() {
		hasPaperScissorsRockPowerUp = true;
	}
	public void  addGuessNumberPowerUp() {
		hasGuessNumberPowerUp = true;
	}

	public void heal(double healingInput) {
		int toHeal = (int) (healingInput * maxHealth);
		currentHealth += toHeal;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}
	public int getHealth() {
		return currentHealth;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public String toString() {
		String toReturn;
		toReturn = "Heroes name: " + name + ". Herotype: " + type + ". He has " + Integer.toString(currentHealth) + " health.\n";
		return toReturn;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public void doDamage(int damage, Team team, BattleWindow battleWindow) {
		currentHealth -= damage;
		if (currentHealth <= 0) {
			team.killHero(this);
			battleWindow.removeDeadHeroFromComboBox(this);
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
	done
}

public class Gambler {
	private int health = 200;
	private int recovery_rate = 8;
	private String ability_description = "Gambler has an extra dice roll and an extra guess at the guess the number game.");
	done
	
}
public class Explorer {
	private int health = 300;
	private int recovery_rate = 8;
	private String ability_description = "Explorer knows the layout of every city.";
	done
}
public class Medic{
	private int health = 150;
	private int recovery_rate = 15;
	private String ability_description = "Medic increases everyones health by 20";
	
}
public class Diplomat {
	private int health = 200;
	private int recovery_rate = 8;
	private String ability_description = "Diplomat decreases the cost of items in the store";
	done
	
}
public class Lucky {
	private int health = 250;
	private int recovery_rate = 9;
	private String ability_description = "Lucky gets more money from winning each battle.";
}

**/ 

