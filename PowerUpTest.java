import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PowerUpTest {
	private PowerUp powerUp;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		int cost = 40;
		powerUp = new PowerUp(cost, "Power Up");
	}
	

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testReduceCost() {
		int initialCost = powerUp.getCost();
		int reduceCostAmount = 20;
		powerUp.reduceCost(reduceCostAmount);
		int finalCost = powerUp.getCost();
		assertEquals(initialCost - reduceCostAmount, finalCost);
	}

}
