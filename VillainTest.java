import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VillainTest {
	private Villain villain;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		int damage = 10;
		villain = new Villain("Taunt input", null, "Name input", damage);
	}
	
	

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testLoseLife() {
		int initialLives = villain.getLives();
		villain.loseLife();
		int finalLives = villain.getLives();
		assertEquals(initialLives - 1, finalLives);
	}

}
