package coinpurse;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoneyFactoryTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	MoneyFactory mf;

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
		mf = MoneyFactory.getInstance();
	}

	/**
	 * Test if MoneyFactory is singleton or not
	 */
	@Test
	public void testSingleton() {
		MoneyFactory mf2 = MoneyFactory.getInstance();
		assertTrue(mf == mf2);
	}

	/**
	 * Test create valuable by createMoney method
	 */
	@Test
	public void testCreate() {
		Purse p = new Purse(5);
		assertTrue(p.insert(mf.createMoney(5)));
		assertTrue(p.insert(mf.createMoney("20")));
		assertTrue(p.insert(mf.createMoney(1)));
		assertTrue(p.insert(mf.createMoney("50")));
		assertTrue(p.insert(mf.createMoney(1000)));
		assertEquals(5, p.count());
	}

	/**
	 * test toString of Valuable that create from createMoney method
	 */
	@Test(timeout = 1000)
	public void testValtoString() {
		Valuable v1 = mf.createMoney(5);
		assertEquals("5.0-Baht coin", v1.toString());
		Valuable v2 = mf.createMoney("20");
		assertEquals("20.00-Baht note [1000000]", v2.toString());
	}

	/**
	 * test serial number of bank note that create by create money method
	 */
	@Test(timeout = 1000)
	public void testSerialNumber() {
		Valuable v1 = mf.createMoney(20);
		Valuable v2 = mf.createMoney(20);
		Valuable v3 = mf.createMoney(20);
		Valuable v4 = mf.createMoney(20);

		assertNotEquals(v1.toString(), v2.toString());
		assertNotEquals(v2.toString(), v3.toString());
		assertNotEquals(v3.toString(), v4.toString());
		assertNotEquals(v2.toString(), v4.toString());
	}
}
