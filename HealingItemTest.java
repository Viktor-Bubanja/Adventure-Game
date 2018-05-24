import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HealingItemTest {
	private HealingItem smallPotion;
	private HealingItem quickPotion;
	private HealingItem bigPotion;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		smallPotion = new HealingItem(10, 0.25, 16, "Small Potion");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testReduceCost() {
		smallPotion.reduceCost(smallPotion.getCost());
		assertEquals(0, smallPotion.getCost());
	}

}
