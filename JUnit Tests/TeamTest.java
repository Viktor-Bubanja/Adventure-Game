import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamTest {
	private Team team;
	private GameEnvironment gameEnvironment;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		gameEnvironment = new GameEnvironment();
		team = new Team(gameEnvironment);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testDecreaseMoneyBy() {
		int cost = 10;
		int initialMoney = team.getMoney();
		team.decreaseMoneyBy(cost);
		int finalMoney = team.getMoney();
		assertEquals(initialMoney - cost, finalMoney);
	}

	@Test
	final void testIncreaseMoneyBy() {
		int initialMoney = team.getMoney();
		int reward = 10;
		team.increaseMoneyBy(reward);
		int finalMoney = team.getMoney();
		assertEquals(initialMoney + reward, finalMoney);
	}

	@Test
	final void testRemovePowerUp() {
		PowerUp powerUp = new PowerUp(0, "Name input");
		team.addPowerUp(powerUp);
		team.removePowerUp(powerUp);
		assertFalse(team.getPowerUps().contains(powerUp));
	}

	@Test
	final void testAddHealingItem() {
		HealingItem healingItem = new HealingItem(0, 0, 0, "Name input");
		team.addHealingItem(healingItem);
		assertTrue(team.getHealingItems().contains(healingItem));
	}

	@Test
	final void testAddMap() {
		Map map = new Map();
		team.addMap();
		assertTrue(team.getNumberMaps() == 1);
	}

	@Test
	final void testRemoveMap() {
		Map map = new Map();
		team.addMap();
		team.removeMap();
		assertTrue(team.getNumberMaps() == 0);
	}

	@Test
	final void testAddPowerUp() {
		PowerUp powerUp = new PowerUp(0, "Name input");
		team.addPowerUp(powerUp);
		assertTrue(team.getPowerUps().contains(powerUp));
	}

	@Test
	final void testAddHero() {
		Hero hero = new Hero("Name input", "Type input", 0, 0);
		team.addHero(hero);
		assertTrue(team.getHeroes().contains(hero));
	}

	@Test
	final void testRemoveHealingItem() {
		HealingItem healingItem = new HealingItem(0, 0, 0, "Name input");
		team.addHealingItem(healingItem);
		team.removeHealingItem(healingItem);
		assertFalse(team.getHealingItems().contains(healingItem));
	}

	@Test
	final void testKillHero() {
		Hero hero = new Hero("Name input", "Type input", 0, 0);
		team.addHero(hero);
		team.killHero(hero);
		assertFalse(team.getHeroes().contains(hero));
	}

}
