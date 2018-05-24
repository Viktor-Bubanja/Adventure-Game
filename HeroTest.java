import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeroTest {
	private Hero hero;
	private Team team;
	BattleWindowGUI battleWindowGui = null;
	//private BattleWindowGUI battleWindowGui;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		hero = new Hero("name input", "Gambler");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testIncreaseMaxHealth() {
		int increaseMaxHealthAmount = 10;
		int initialMaxHealth = hero.getMaxHealth();
		hero.increaseMaxHealth(increaseMaxHealthAmount);
		int finalMaxHealth = hero.getMaxHealth();
		assertEquals(initialMaxHealth + increaseMaxHealthAmount, finalMaxHealth);
	}

	@Test
	final void testDecreaseMaxHealth() {
		int decreaseHealthAmount = 10;
		int initialMaxHealth = hero.getMaxHealth();
		hero.decreaseMaxHealth(decreaseHealthAmount);
		int finalMaxHealth = hero.getMaxHealth();
		assertEquals(initialMaxHealth - decreaseHealthAmount, finalMaxHealth);
	}

	@Test
	final void testAddPaperScissorsRockPowerUp() {
		hero.addPaperScissorsRockPowerUp();
		boolean hasPaperScissorsRockPowerUp = hero.getHasPaperScissorsRockPowerUp();
		assertEquals(true, hasPaperScissorsRockPowerUp);
	}

	@Test
	final void testAddGuessNumberPowerUp() {
		hero.addGuessNumberPowerUp();
		boolean hasGuessNumberPowerUp = hero.getHasGuessNumberPowerUp();
		assertEquals(true, hasGuessNumberPowerUp);
	}
	
	@Test
	final void testAddDiceGamePowerUp() {
		hero.addDiceGamePowerUp();
		boolean hasDiceGamePowerUp = hero.getHasDiceGamePowerUp();
		assertEquals(true, hasDiceGamePowerUp);
	}

	@Test
	final void testHealNotFullHealth() {
		double healAmount = 0.25;
		int damage = 100;
		hero.doDamage(damage, null, null);
		int healthBeforeHeal = hero.getHealth();
		hero.heal(healAmount);
		int healthAfterHeal = hero.getHealth();
		assertEquals((int)(healthBeforeHeal + healAmount * hero.getMaxHealth()), healthAfterHeal);
	}
	
	@Test
	final void testHealToFullHealth() {
		double healAmount = 0.25;
		int damage = 1;
		hero.setCurrentHealth(hero.getHealth() - damage);
		hero.heal(healAmount);
		int healthAfterHeal = hero.getHealth();
		assertEquals(hero.getMaxHealth(), healthAfterHeal);
	}


	@Test
	final void testDoDamage() {
		int damage = 10;
		int initialHealth = hero.getHealth();
		hero.doDamage(damage, null, null);
		int finalHealth = hero.getHealth();
		assertEquals(initialHealth - damage, finalHealth);
	}



}
