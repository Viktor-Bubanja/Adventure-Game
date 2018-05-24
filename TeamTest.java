import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamTest {
	private Team team;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		GameEnvironment gameEnvironment = new GameEnvironment();
		Team team = new Team(gameEnvironment);
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
		fail("Not yet implemented");
	}

	@Test
	final void testRemovePowerUp() {
		fail("Not yet implemented");
	}

	@Test
	final void testAddHealingItem() {
		fail("Not yet implemented");
	}

	@Test
	final void testAddMap() {
		fail("Not yet implemented");
	}

	@Test
	final void testRemoveMap() {
		fail("Not yet implemented");
	}

	@Test
	final void testAddPowerUp() {
		fail("Not yet implemented");
	}

	@Test
	final void testAddHero() {
		fail("Not yet implemented");
	}

	@Test
	final void testRemoveHealingItem() {
		fail("Not yet implemented");
	}

	@Test
	final void testKillHero() {
		fail("Not yet implemented");
	}

}
