import java.util.ArrayList;

public class Team {
	
	public String teamName;
	private int money = 100; //Some random amount later
	private int maps = 0;
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private ArrayList<HealingItem> healingItems = new ArrayList<HealingItem>();
	private boolean medicInTeam = false;
	private boolean diplomatInTeam = false;
	private boolean explorerInTeam = false;
	private boolean luckyInTeam = false;
	private GameEnvironment gameEnvironment;
	
	public Team(GameEnvironment gameEnvironmentInput) {
		gameEnvironment = gameEnvironmentInput;
	}

	public boolean teamHasMedic() {
		return medicInTeam;
	}

	public void setTeamHasMedic(boolean teamHasMedic) {
		medicInTeam = teamHasMedic;
	}

	public boolean teamHasDiplomat() {
		return diplomatInTeam;
	}

	public void setTeamHasDiplomat(boolean teamHasDiplomat) {
		diplomatInTeam = teamHasDiplomat;
	}

	public boolean teamHasExplorer() {
		return explorerInTeam;
	}
	/**
	 * 
	 * @param teamHasExplorer Boolean variable. True if the team contains Explorer hero.
	 */
	public void setTeamHasExplorer(boolean teamHasExplorer) {
		explorerInTeam = teamHasExplorer;
	}
	/**
	 * 
	 * @return Boolean variable luckyInTeam. True if the team contains Lucky hero.
	 */
	public boolean teamHasLucky() {
		return luckyInTeam;
	}
	/**
	 * 
	 * @param teamHasLucky Boolean variable True if the team contains Lucky hero.
	 */
	public void setTeamHasLucky(boolean teamHasLucky) {
		luckyInTeam = teamHasLucky;
	}
	/**
	 * Sets the name of the team to what the user inputs.
	 * Called during the setup of the game.
	 * @param teamNameInput Name of team
	 */
	public void setTeamName(String teamNameInput) {
		teamName = teamNameInput;
	}
	/**
	 * Decreases the amount of money the team has.
	 * Called from the ShopGUI after purchasing an item.
	 * @param cost Amount to decreases teams money by.
	 */
	public void decreaseMoneyBy(int cost) {
		money -= cost;
	}
	/**
	 * Increases the amount of money the team has by a given amount after defeating a villain.
	 * @param reward Amount of money given to the team.
	 */
	public void increaseMoneyBy(int reward) {
		money += reward;
	}
	/**
	 * Removes a power up from the teams inventory.
	 * @param powerUp Power up to be removed.
	 */
	public void removePowerUp(PowerUp powerUp) {
		powerUps.remove(powerUp);
	}
	/**
	 * Adds a healing item to the teams inventory.
	 * @param healingItem Healing item to be removed.
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
	 * @return Number of maps left in the team.
	 */
	public int getNumberMaps() {
		return maps;
	}
	/**
	 * 
	 * @return Money left in the team.
	 */
	public int getMoney() {
		return money;
	}
	/** Adds a power up to the inventory of the team.
	 * 
	 * @param powerUp
	 */
	public void addPowerUp(PowerUp powerUp) {
		powerUps.add(powerUp);
	}
	/**
	 * 		
	 * @return Number of heroes in the team.
	 */
	public int getNumberHeroes() {
		return heroes.size();
	}
	/**
	 * 
	 * @return List of the names of the heroes in the team to display in JComboBoxes throughout the game.
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
	 * @return ArrayList of hero types in the team.
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
	 * @return List of the names of the healing items to display in JComboBoxes throughout the game.
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
	 * @return List of the names of the power ups to display in JComboBoxes throughout the game.
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
	 * @return ArrayList of healing items in the teams inventory.
	 */
	public ArrayList<HealingItem> getHealingItems() {
		return healingItems;
	}
	/**
	 * 
	 * @return ArrayList of heroes in the team.
	 */
	public ArrayList<Hero> getHeroes() {
		return heroes;
	}
	/** Adds a hero to the team.
	 * 
	 * @param hero 
	 */
	public void addHero(Hero hero) {
		heroes.add(hero);
	}
	/**
	 * 
	 * @return ArrayList of the power ups in the teams inventory.
	 */
	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}
	/** Removes a healing item from the teams inventory.
	 * 
	 * @param healingItem
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
