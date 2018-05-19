import java.util.ArrayList;

public class Team {
	
	public String teamName;
	private static int numberHeroes;	
	private static int money = 300; //Some random amount later
	private static int maps = 0;
	private static ArrayList<Hero> heroes = new ArrayList<Hero>();
	private static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private static ArrayList<HealingItem> healingItems = new ArrayList<HealingItem>();
	private static boolean medicInTeam = false;
	private static boolean gamblerInTeam = false;
	private static boolean diplomatInTeam = false;
	private static boolean explorerInTeam = false;
	private static boolean luckyInTeam = false;
	
	public static boolean teamHasMedic() {
		return medicInTeam;
	}

	public static void setTeamHasMedic(boolean teamHasMedic) {
		Team.medicInTeam = teamHasMedic;
	}

	public static boolean teamHasGambler() {
		return gamblerInTeam;
	}

	public static void setTeamHasGambler(boolean teamHasGambler) {
		Team.gamblerInTeam = teamHasGambler;
	}

	public static boolean teamHasDiplomat() {
		return diplomatInTeam;
	}

	public static void setTeamHasDiplomat(boolean teamHasDiplomat) {
		Team.diplomatInTeam = teamHasDiplomat;
	}

	public static boolean teamHasExplorer() {
		return explorerInTeam;
	}

	public static void setTeamHasExplorer(boolean teamHasExplorer) {
		Team.explorerInTeam = teamHasExplorer;
	}

	public static boolean teamHasLucky() {
		return luckyInTeam;
	}

	public static void setTeamHasLucky(boolean teamHasLucky) {
		Team.luckyInTeam = teamHasLucky;
	}
	
	
	public Team(String name, int number_of_heroes) {
		teamName = name;
		numberHeroes = number_of_heroes;
	}

	public static void decreaseMoneyBy(int cost) {
		money -= cost;
	}
	public static void increaseMoneyBy(int reward) {
		money += reward;
	}
	
	public static void usePowerUp(int i) {
		powerUps.remove(i);
	}
	
	public static void addHealingItem(HealingItem healingItem) {
		healingItems.add(healingItem);
	}
	
	public static void addPowerUp(PowerUp powerUp) {
		powerUps.add(powerUp);
	}
	
	public static int getMoney() {
		return money;
	}
	public static void addMap() {
		maps++;
	}
	public static void removeMap() {
		maps--;
	}
	public static int getNumberMaps() {
		return maps;
	}
			
	public static int getNumberHeroes() {
		return heroes.size();
	}
	
	public static String[] getHeroNames() {
		String[] heroNames = new String[heroes.size()];
		for (int i = 0; i < heroes.size(); i++) {
			heroNames[i] = heroes.get(i).getName();
			//System.out.println(heroNames[i]);
		}
		
		return heroNames;
	}
	public static ArrayList<String> getHeroTypes() {
		ArrayList<String> heroTypes = new ArrayList<String>();
		for (int i = 0; i < heroes.size(); i++) {
			heroTypes.add(heroes.get(i).getType());
		}
		return heroTypes;
	}
	public static String[] getHealingItemNames() {
		String[] healingItemNames = new String[healingItems.size()];
		for (int i = 0; i < healingItems.size(); i++ ) {
			healingItemNames[i] = healingItems.get(i).getName();
		}
		return healingItemNames;
	}
	public static String[] getPowerUpNames() {
		String[] powerUpNames = new String[powerUps.size()];
		for (int i = 0; i < powerUps.size(); i++ ) {
			powerUpNames[i] = powerUps.get(i).getName();
		}
		return powerUpNames;
	}
	
	public static ArrayList<HealingItem> getHealingItems() {
		return healingItems;
	}
	
	public static ArrayList<Hero> getHeroes() {
		return heroes;
	}
	public static void addHero(Hero hero) {
		heroes.add(hero);
	}
	
	public static ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}
	public static void killHero(Hero hero) {
		heroes.remove(hero);
		if (heroes.size() == 0) {
			GameEnvironment.endGame();
		}
	}
			
	public static void main(String[] args) {
		Team newTeam = new Team("trht", 2);
	}

	
	
	
	
}
