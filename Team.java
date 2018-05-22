import java.util.ArrayList;

public class Team {
	
	public String teamName;
	private int numberHeroes;	
	private int money = 300; //Some random amount later
	private int maps = 0;
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private ArrayList<HealingItem> healingItems = new ArrayList<HealingItem>();
	private boolean medicInTeam = false;
	private boolean gamblerInTeam = false;
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

	public boolean teamHasGambler() {
		return gamblerInTeam;
	}

	public void setTeamHasGambler(boolean teamHasGambler) {
		gamblerInTeam = teamHasGambler;
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

	public void setTeamHasExplorer(boolean teamHasExplorer) {
		explorerInTeam = teamHasExplorer;
	}

	public boolean teamHasLucky() {
		return luckyInTeam;
	}

	public void setTeamHasLucky(boolean teamHasLucky) {
		luckyInTeam = teamHasLucky;
	}

	public void setTeamName(String teamNameInput, int numberHeroesInput ) {
		teamName = teamNameInput;
		numberHeroes = numberHeroesInput;
	}

	public void decreaseMoneyBy(int cost) {
		money -= cost;
	}
	public void increaseMoneyBy(int reward) {
		money += reward;
	}
	
	public void removePowerUp(PowerUp powerUp) {
		System.out.println(powerUps);
		powerUps.remove(powerUp);
		System.out.println(powerUps);
	}
	
	public void addHealingItem(HealingItem healingItem) {
		healingItems.add(healingItem);
	}

	public void addMap() {
		maps++;
	}
	public void removeMap() {
		maps -= 1;
	}
	public int getNumberMaps() {
		return maps;
	}
	
	public int getMoney() {
		return money;
	}
	public void addPowerUp(PowerUp powerUp) {
		powerUps.add(powerUp);
	}
			
	public int getNumberHeroes() {
		return heroes.size();
	}
	
	public String[] getHeroNames() {
		String[] heroNames = new String[heroes.size()];
		for (int i = 0; i < heroes.size(); i++) {
			heroNames[i] = heroes.get(i).getName();
			//System.out.println(heroNames[i]);
		}
		return heroNames;
	}
	public ArrayList<String> getHeroTypes() {
		ArrayList<String> heroTypes = new ArrayList<String>();
		for (int i = 0; i < heroes.size(); i++) {
			heroTypes.add(heroes.get(i).getType());
		}
		return heroTypes;
	}
	public String[] getHealingItemNames() {
		String[] healingItemNames = new String[healingItems.size()];
		for (int i = 0; i < healingItems.size(); i++ ) {
			healingItemNames[i] = healingItems.get(i).getName();
		}
		return healingItemNames;
	}
	public String[] getPowerUpNames() {
		String[] powerUpNames = new String[powerUps.size()];
		for (int i = 0; i < powerUps.size(); i++ ) {
			powerUpNames[i] = powerUps.get(i).getName();
		}
		return powerUpNames;
	}
	
	public ArrayList<HealingItem> getHealingItems() {
		return healingItems;
	}
	
	public ArrayList<Hero> getHeroes() {
		return heroes;
	}
	public void addHero(Hero hero) {
		heroes.add(hero);
	}
	
	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}
	public void removeHealingItem(int index) {
		healingItems.remove(index);
		//ShopGUI.updateHealingItemsLabel();
	}
	public void killHero(Hero hero) {
		heroes.remove(hero);
		if (heroes.size() == 0) {
			gameEnvironment.endGame();
		}
	}
}
