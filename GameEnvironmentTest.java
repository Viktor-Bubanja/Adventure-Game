import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameEnvironmentTest {
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
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testFinalCity() {
		gameEnvironment.setNumberCities(0);
		boolean inFinalCityBool;
		inFinalCityBool = gameEnvironment.finalCity();
		assertTrue(inFinalCityBool);
	}
}
