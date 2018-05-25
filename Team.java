import java.util.ArrayList;

public class Team {
	/**
	 * Attributes:
	 * Team name
	 * Current money, initially set to 100.
	 * Number of maps, initially set to 0.
	 * ArrayList of heroes
	 * ArrayList of power ups in the teams inventory.
	 * ArrayList of healing items in the teams inventory.
	 * Boolean variables for whether the team contains a Medic, Diplomat, Explorer or Lucky hero, as their special abilities affect the whole team.
	 * Game environment
	 */
	public String teamName;
	private int money = 100; //Some random amount later
	private int maps = 0;
	private ArrayList<Hero> heroes;
	private ArrayList<PowerUp> powerUps;
	private ArrayList<HealingItem> healingItems;
	private boolean medicInTeam = false;
	private boolean diplomatInTeam = false;
	private boolean explorerInTeam = false;
	private boolean luckyInTeam = false;
	private GameEnvironment gameEnvironment;
	/**
	 * 
	 * @param gameEnvironmentInput GameEnvironment
	 */
	public Team(GameEnvironment gameEnvironmentInput) {
		heroes = new ArrayList<Hero>();
		powerUps = new ArrayList<PowerUp>();
		healingItems = new ArrayList<HealingItem>();
		gameEnvironment = gameEnvironmentInput;
	}
	/**
	 * 
	 * @return boolean medicInTeam. True if the team contains Medic hero.
	 */
	public boolean teamHasMedic() {
		return medicInTeam;
	}
	/**
	 * 
	 * @param teamHasMedic boolean. True if the team contains Medic hero.
	 */
	public void setTeamHasMedic(boolean teamHasMedic) {
		medicInTeam = teamHasMedic;
	}
	/**
	 * 
	 * @return boolean diplomatInTeam. True if the team contains Diplomat hero.
	 */
	public boolean teamHasDiplomat() {
		return diplomatInTeam;
	}
	/**
	 * 
	 * @param teamHasDiplomat boolean. True if the team contains Diplomat hero.
	 */
	public void setTeamHasDiplomat(boolean teamHasDiplomat) {
		diplomatInTeam = teamHasDiplomat;
	}
	/**
	 * 
	 * @return boolean explorerInTeam. True if the team contains Explorer hero.
	 */
	public boolean teamHasExplorer() {
		return explorerInTeam;
	}
	/**
	 * 
	 * @param teamHasExplorer boolean. True if the team contains Explorer hero.
	 */
	public void setTeamHasExplorer(boolean teamHasExplorer) {
		explorerInTeam = teamHasExplorer;
	}
	/**
	 * 
	 * @return boolean luckyInTeam. True if the team contains Lucky hero.
	 */
	public boolean teamHasLucky() {
		return luckyInTeam;
	}
	/**
	 * 
	 * @param teamHasLucky boolean. True if the team contains Lucky hero.
	 */
	public void setTeamHasLucky(boolean teamHasLucky) {
		luckyInTeam = teamHasLucky;
	}
	/**
	 * Sets the name of the team to what the user inputs.
	 * Called during the setup of the game.
	 * @param teamNameInput String Name of team
	 */
	public void setTeamName(String teamNameInput) {
		teamName = teamNameInput;
	}
	/**
	 * Decreases the amount of money the team has.
	 * Called from the ShopGUI after purchasing an item.
	 * @param cost int Amount to decreases teams money by.
	 */
	public void decreaseMoneyBy(int cost) {
		money -= cost;
	}
	/**
	 * Increases the amount of money the team has by a given amount after defeating a villain.
	 * @param reward int Amount of money given to the team.
	 */
	public void increaseMoneyBy(int reward) {
		money += reward;
	}
	/**
	 * Removes a power up from the teams inventory.
	 * @param powerUp PowerUp. Power up to be removed.
	 */
	public void removePowerUp(PowerUp powerUp) {
		powerUps.remove(powerUp);
	}
	/**
	 * Adds a healing item to the teams inventory.
	 * @param healingItem HealingItem. Healing item to be removed.
	 */
	public void addHealingItem(HealingItem healingItem) {
		healingItems.add(healingItem);
	}
	/**
	 *  Adds one map to the team by incrementing the number of maps the team has.
	 */
	public void addMap() {
		maps++;
	}
	/** 
	 *  Removes one map from the team by decrementing the number of maps the team has.
	 */
	public void removeMap() {
		maps -= 1;
	}
	/**
	 * 
	 * @return int Number of maps left in the team.
	 */
	public int getNumberMaps() {
		return maps;
	}
	/**
	 * 
	 * @return int Money left in the team.
	 */
	public int getMoney() {
		return money;
	}
	/** Adds a power up to the inventory of the team.
	 * 
	 * @param powerUp PowerUp
	 */
	public void addPowerUp(PowerUp powerUp) {
		powerUps.add(powerUp);
	}
	/**
	 * 		
	 * @return int Number of heroes in the team.
	 */
	public int getNumberHeroes() {
		return heroes.size();
	}
	/**
	 * 
	 * @return String[] List of the names of the heroes in the team to display in JComboBoxes throughout the game.
	 */
	public String[] getHeroNames() {
		String[] heroNames = new String[heroes.size()];
		for (int i = 0; i < heroes.size(); i++) {
			heroNames[i] = heroes.get(i).getName();
		}
		return heroNames;
	}
	/**
	 * 
	 * @return ArrayList List of hero types in the team.
	 */
	public ArrayList<String> getHeroTypes() {
		ArrayList<String> heroTypes = new ArrayList<String>();
		for (int i = 0; i < heroes.size(); i++) {
			heroTypes.add(heroes.get(i).getType());
		}
		return heroTypes;
	}
	/**
	 * 
	 * @return String[] List of the names of the healing items to display in JComboBoxes throughout the game.
	 */
	public String[] getHealingItemNames() {
		String[] healingItemNames = new String[healingItems.size()];
		for (int i = 0; i < healingItems.size(); i++ ) {
			healingItemNames[i] = healingItems.get(i).getName();
		}
		return healingItemNames;
	}
	/**
	 * 
	 * @return String[] List of the names of the power ups to display in JComboBoxes throughout the game.
	 */
	public String[] getPowerUpNames() {
		String[] powerUpNames = new String[powerUps.size()];
		for (int i = 0; i < powerUps.size(); i++ ) {
			powerUpNames[i] = powerUps.get(i).getName();
		}
		return powerUpNames;
	}
	/**
	 * 
	 * @return ArrayList List of healing items in the teams inventory.
	 */
	public ArrayList<HealingItem> getHealingItems() {
		return healingItems;
	}
	/**
	 * 
	 * @return ArrayListList of heroes in the team.
	 */
	public ArrayList<Hero> getHeroes() {
		return heroes;
	}
	/** Adds a hero to the team.
	 * 
	 * @param hero Hero
	 */
	public void addHero(Hero hero) {
		heroes.add(hero);
	}
	/**
	 * 
	 * @return ArrayList List of the power ups in the teams inventory.
	 */
	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}
	/** Removes a healing item from the teams inventory.
	 * 
	 * @param healingItem HealingItem
	 */
	public void removeHealingItem(HealingItem healingItem) {
		healingItems.remove(healingItem);
	}
	/**
	 * Kills a hero
	 * If the hero's special ability applies to the entire team, it is removed.
	 * If there are no heroes left, ends the game.
	 * 
	 * @param hero Hero which was killed.
	 */
	public void killHero(Hero hero) {
		heroes.remove(hero);
		switch (hero.getType()) {
		case "Medic":		setTeamHasMedic(false);
							for (Hero heroItem: this.getHeroes()) {
								heroItem.decreaseMaxHealth(75);
							}
							break;
		case "Explorer":	setTeamHasExplorer(false);
							break;
		case "Lucky":		setTeamHasLucky(false);
							break;
		case "Diplomat":	setTeamHasDiplomat(false);
							break;
		}
		if (heroes.size() == 0) {
			gameEnvironment.gameLost();
		}
	}
}
