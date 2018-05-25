import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Hero {
	/**
	 * Attributes:
	 * The name of the hero
	 * the type of your hero
	 * The maximum health your hero can have
	 * your heros current health
	 * how fast the hero can heal. Higher number the better
	 * whether or not the hero has the paper scissors rock clue power up
	 * whether or not the hero has the extra guess power up
	 * whether or not the hero has the golden die power up
	 */
	private String name;
	private String type;
	private int maxHealth;
	private int currentHealth;
	private int recoveryRate;
	private boolean hasPaperScissorsRockPowerUp = false;
	private boolean hasGuessNumberPowerUp = false;
	private boolean hasDiceGamePowerUp = false;
	/**
	 * initializes
	 * makes you start off with your current health full (not including medic)
	 * @param inputName String
	 * @param inputType String
	 * @param maxHealthInput int
	 * @param recoveryRateInput int
	 */
	public Hero(String inputName, String inputType, int maxHealthInput, int recoveryRateInput) {
		name = inputName;
		type = inputType;
		maxHealth = maxHealthInput;
		currentHealth = maxHealth;
		recoveryRate = recoveryRateInput;
	}
	/**
	 * @param currentHealthInput int sets the currentHealth (mainly for testing)
	 */
	public void setCurrentHealth(int currentHealthInput) {
		currentHealth = currentHealthInput;
	}
	/**
	 * @return int gets a heroes recoveryRate
	 */
	public int getRecoveryRate() {
		return recoveryRate;
	}
	/**
	 * increases your max health but does not heal you up to that health
	 * @param increaseBy int
	 */
	public void increaseMaxHealth(int increaseBy) {
		maxHealth += increaseBy;
	}
	/**
	 * decreases your max health (when medic dies) and sets your current health to that maxHealth if you had higher health
	 * @param decreaseBy int  
	 */
	public void decreaseMaxHealth(int decreaseBy) {
		maxHealth -= decreaseBy;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}
	/**
	 * heals you up until the maxHealth
	 * @param healingInput double  
	 */
	public void heal(double healingInput) {
		int toHeal = (int) (healingInput * maxHealth);
		currentHealth += toHeal;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}
	/**
	 * @return int Heroes currentHealth
	 */
	public int getHealth() {
		return currentHealth;
	}
	/**
	 * @return int heroes maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * @return String  The string representation of a hero
	 */
	public String toString() {
		String toReturn;
		toReturn = "Name: " + name + ". Herotype: " + type + ". Health: " + Integer.toString(currentHealth) + "/" + maxHealth +"\n";
		return toReturn;
	}
	/**
	 * @return String Heroes name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return String representation of the heroes type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Do damage to the hero
	 * kills the hero if he's below 1 health
	 * @param damage int
	 * @param team Team
	 * @param battleWindowGui BattleWindowGUI
	 */
	public void doDamage(int damage, Team team, BattleWindowGUI battleWindowGui) {
		currentHealth -= damage;
		if (currentHealth <= 0) {
			if (battleWindowGui != null) {
				team.killHero(this);
				battleWindowGui.removeDeadHeroFromComboBox(this);
			}			
		}
	}
	/**
	 * sets your hero to have the Golden Die PowerUp
	 */
	public void addDiceGamePowerUp() {
		hasDiceGamePowerUp = true;
	}
	/**
	 * Sets your hero to have the Paper Scissors Rock Clue PowerUp
	 */
	public void addPaperScissorsRockPowerUp() {
		hasPaperScissorsRockPowerUp = true;
	}
	/**
	 * sets your hero to have the Extra Guess PowerUp
	 */
	public void  addGuessNumberPowerUp() {
		hasGuessNumberPowerUp = true;
	}
	
	
	/**
	 * @return boolean whether or not it has the paper scissors rock clue power Up
	 */
	public boolean getHasPaperScissorsRockPowerUp() {
		return hasPaperScissorsRockPowerUp;
	}
	/**
	 * @return boolean whether or not it has the Extra Guess power Up
	 */
	public boolean getHasGuessNumberPowerUp() {
		return hasGuessNumberPowerUp;
	}
	/**
	 * @return boolean whether or not the hero has the Golden Die power Up
	 */
	public boolean getHasDiceGamePowerUp() {
		return hasDiceGamePowerUp;
	}
	/**
	 * Sets the hero to either have the Paper Scissors Rock Clue power up or not
	 * @param hasPaperScissorsRockPowerUp boolean
	 */
	public void setHasPaperScissorsRockPowerUp(boolean hasPaperScissorsRockPowerUp) {
		this.hasPaperScissorsRockPowerUp = hasPaperScissorsRockPowerUp;
	}
	/**
	 * Sets the hero to either have the Extra Guess power up or not
	 * @param hasGuessNumberPowerUp boolean
	 */
	public void setHasGuessNumberPowerUp(boolean hasGuessNumberPowerUp) {
		this.hasGuessNumberPowerUp = hasGuessNumberPowerUp;
	}
	/**
	 * Sets the hero to either have the Golden Die power up or not
	 * @param hasDiceGamePowerUp boolean
	 */
	public void setHasDiceGamePowerUp(boolean hasDiceGamePowerUp) {
		this.hasDiceGamePowerUp = hasDiceGamePowerUp;
	}
	
}
	
