package coinpurse;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Purse using JUnit. This is a JUnit 4 test suite.
 * 
 * IDEs (Eclipse, Netbeans, IntelliJ, BlueJ) include JUnit 4, but you have to
 * tell the IDE to add it to your project as a "Library". To run these tests,
 * right click on this file (in Project panel) and choose Run As -> JUnit test
 * 
 * @author Resident Evil
 * @version 2018.01.19
 */
public class PurseTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	private static final String CURRENCY = "BTC";

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		// nothing to initialize
	}

	/** Make a coin with the default currency. To save typing "new Coin(...)" */
	private Coin makeCoin(double value) {
		return new Coin(value, CURRENCY);
	}

	/** Easy test that the Purse constructor is working. */
	@Test
	public void testConstructor() {
		Purse purse = new Purse(3);
		assertEquals(3, purse.getCapacity());
		assertEquals(false, purse.isFull());
		assertEquals(0, purse.count());
	}

	/** Insert some coins. Easy test. */
	@Test
	public void testInsert() {
		Purse purse = new Purse(6);
		Coin coin1 = makeCoin(5);
		Coin coin2 = makeCoin(10);
		Coin coin3 = makeCoin(1);
		BankNote bank1 = new BankNote(20, "bath");
		BankNote bank2 = new BankNote(100, "bath");
		BankNote bank3 = new BankNote(50, "bath");

		assertTrue(purse.insert(bank1));
		assertTrue(purse.insert(bank2));
		assertTrue(purse.insert(bank3));
		assertEquals(3, purse.count());

		assertTrue(purse.insert(coin1));
		assertTrue(purse.insert(coin3));
		assertTrue(purse.insert(coin2));
		assertEquals(6, purse.count());
		// purse is full so insert should fail
		assertFalse(purse.insert(makeCoin(1)));
	}

	/** Insert should reject coin with no value. */
	@Test
	public void testInsertNoValue() {
		Purse purse = new Purse(3);
		Coin fakeCoin = new Coin(0, CURRENCY);
		BankNote fakeBank = new BankNote(0, CURRENCY);
		assertFalse(purse.insert(fakeBank));
		assertFalse(purse.insert(fakeCoin));
	}

	@Test(timeout = 1000)
	public void testIsFull() { // borderline case (capacity 1)
		Purse purse = new Purse(1);
		assertFalse(purse.isFull());
		purse.insert(makeCoin(1));
		assertTrue(purse.isFull());
		// real test
		int capacity = 4;
		purse = new Purse(capacity);
		for (int k = 1; k <= capacity; k++) {
			assertFalse(purse.isFull());
			purse.insert(makeCoin(k));
		}
		// should be full now
		assertTrue(purse.isFull());
		assertFalse(purse.insert(makeCoin(5)));
	}

	/**
	 * Should be able to insert same coin many times, since spec doesn't say
	 * anything about this.
	 */
	@Test(timeout = 1000)
	public void testInsertSameCoin() {
		int capacity = 10;
		double value = 10.0;
		double bankVal = 20;
		Purse purse = new Purse(capacity);
		Coin coin = new Coin(value, "THB");
		BankNote bank = new BankNote(bankVal, "THB");
		assertTrue(purse.insert(bank));
		assertTrue(purse.insert(bank));
		assertTrue(purse.insert(bank));
		assertTrue(purse.insert(bank));
		assertTrue(purse.insert(bank));

		assertTrue(purse.insert(coin));
		assertTrue(purse.insert(coin)); // should be allowed
		assertTrue(purse.insert(coin)); // should be allowed
		assertTrue(purse.insert(coin)); // should be allowed
		assertTrue(purse.insert(coin)); // should be allowed
		assertEquals(purse.getBalance(), 150, TOL);
	}

	/** Add one coin and remove it. */
	@Test(timeout = 1000)
	public void testEasyWithdraw() {
		Purse purse = new Purse(10);
		double[] values = { 1, 20, 0.5, 10 }; // values of coins we will insert

		for (double value : values) {
			Coin coin = makeCoin(value);
			assertTrue(purse.insert(coin));
			assertEquals(value, purse.getBalance(), TOL);
			Valuable[] result = purse.withdraw(value);
			assertTrue(result != null);
			assertEquals(1, result.length);
			assertSame(coin, result[0]); // should be same object
			assertEquals(0, purse.getBalance(), TOL);
		}
		for (double value : values) {
			BankNote bank = new BankNote(value, "THB");
			assertTrue(purse.insert(bank));
			assertEquals(value, purse.getBalance(), TOL);
			Valuable[] result = purse.withdraw(value);
			assertTrue(result != null);
			assertEquals(1, result.length);
			assertSame(bank, result[0]); // should be same object
			assertEquals(0, purse.getBalance(), TOL);
		}
	}

	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout = 1000)
	public void testMultiWithdraw() {
		Purse purse = new Purse(10);
		Coin[] coins = { makeCoin(5.0), makeCoin(10.0), makeCoin(1.0), makeCoin(5.0) };
		// insert them all
		for (Coin coin : coins)
			assertTrue(purse.insert(coin));

		double amount1 = coins[1].getValue() + coins[3].getValue();
		double amount2 = coins[0].getValue() + coins[2].getValue();
		assertEquals(amount1 + amount2, purse.getBalance(), TOL);

		Valuable[] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL);

		assertEquals(amount2, purse.getBalance(), TOL);
		Valuable[] wd2 = purse.withdraw(amount2);

		// should be empty now
		assertEquals(0, purse.getBalance(), TOL);

		Purse bankPurse = new Purse(5);
		BankNote[] banks = { new BankNote(20, "THB"), new BankNote(50, "THB"), new BankNote(100, "THB"),
				new BankNote(500, "THB"), new BankNote(1000, "THB") };
		for (BankNote bank : banks)
			assertTrue(bankPurse.insert(bank));

		double amount3 = banks[1].getValue() + banks[3].getValue();
		double amount4 = banks[0].getValue() + banks[2].getValue() + banks[4].getValue();
		assertEquals(amount3 + amount4, bankPurse.getBalance(), TOL);

		Valuable[] wd3 = bankPurse.withdraw(amount3);
		assertEquals(amount3, sum(wd3), TOL);

		assertEquals(amount4, bankPurse.getBalance(), TOL);
		Valuable[] wd4 = bankPurse.withdraw(amount4);

		assertEquals(0, bankPurse.getBalance(), TOL);

	}

	/** Withdraw full amount in purse, using varying numbers of objects. */
	@Test(timeout = 1000)
	public void testWithdrawEverything() {
		Purse purse = new Purse(10);
		// Coins we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
		List<Coin> coins = Arrays.asList(makeCoin(1.0), makeCoin(0.5), makeCoin(10.0), makeCoin(0.25), makeCoin(5.0));
		// num = number of coins to insert and then withdraw
		for (int num = 1; num <= coins.size(); num++) {
			double amount = 0.0;
			List<Coin> subList = coins.subList(0, num);
			for (Coin c : subList) {
				purse.insert(c);
				amount += c.getValue();
			}
			// balance should be exactly what we just inserted
			assertEquals(amount, purse.getBalance(), TOL);
			// can we withdraw it all?
			Valuable[] result = purse.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s", amount,
					Arrays.toString(subList.toArray()));
			assertNotNull(errmsg, result);
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals(0.0, purse.getBalance(), TOL);
		}

		Purse purse2 = new Purse(10);
		// Banknotes we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
		List<BankNote> banks = Arrays.asList(new BankNote(20, "THB"), new BankNote(50, "THB"), new BankNote(100, "THB"),
				new BankNote(500, "THB"), new BankNote(1000, "THB"));
		// num = number of banknotes to insert and then withdraw
		for (int num = 1; num <= banks.size(); num++) {
			double amount = 0.0;
			List<BankNote> subList = banks.subList(0, num);
			for (BankNote c : subList) {
				purse2.insert(c);
				amount += c.getValue();
			}
			// balance should be exactly what we just inserted
			assertEquals(amount, purse2.getBalance(), TOL);
			// can we withdraw it all?
			Valuable[] result = purse2.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s", amount,
					Arrays.toString(subList.toArray()));
			assertNotNull(errmsg, result);
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals(0.0, purse2.getBalance(), TOL);
		}
	}

	@Test(timeout = 1000)
	public void testImpossibleWithdraw() {
		Purse purse = new Purse(10);
		assertNull(purse.withdraw(1));
		purse.insert(makeCoin(20));
		assertNull(purse.withdraw(1));
		assertNull(purse.withdraw(19));
		assertNull(purse.withdraw(21));
		purse.insert(makeCoin(20)); // now it has 20 + 20
		assertNull(purse.withdraw(30));
		purse.insert(new BankNote(100, "THB"));
		assertNull(purse.withdraw(50));
		assertNull(purse.withdraw(111));
		assertNull(purse.withdraw(123));

	}

	/**
	 * Sum the value of some coins.
	 * 
	 * @param wd1
	 *            array of coins
	 * @return sum of values of the coins
	 */
	private double sum(Valuable[] wd1) {
		if (wd1 == null)
			return 0.0;
		double sum = 0;
		for (Valuable c : wd1)
			if (c != null)
				sum += c.getValue();
		return sum;
	}
}
