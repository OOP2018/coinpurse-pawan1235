package coinpurse.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coinpurse.*;

public class WithdrawTest {
	private WithdrawStrategy strategy;
	private final String CURRENCY = "Baht";
	private List<Valuable> moneys = new ArrayList<>();
	private List<Valuable> wallets = new ArrayList<>();

	@Before
	public void setUp() {
		strategy = new RecursiveWithdraw();
	}

	@Test
	public void testEasyWithdraw() {
		Valuable five = new Coin(5, CURRENCY);
		moneys.add(five);
		wallets.add(five);
		assertEquals(strategy.withdraw(five, moneys), wallets);
		wallets.remove(five);
		Valuable twenty = new BankNote(20, CURRENCY);
		moneys.add(twenty);
		wallets.add(twenty);
		assertEquals(strategy.withdraw(twenty, moneys), wallets);
	}

	@Test
	public void testWithdrawImpossible() {
		moneys = Arrays.asList(new Coin(5, CURRENCY), new Coin(1, CURRENCY));
		assertNull(strategy.withdraw(new Money(3, CURRENCY), moneys));
		assertNull(strategy.withdraw(new Money(7, CURRENCY), moneys));
		assertNull(strategy.withdraw(new BankNote(10, CURRENCY), moneys));
	}

	@Test
	public void testWithdrawNotChange() {
		moneys = Arrays.asList(new BankNote(20, "Baht"), new BankNote(50, "Baht"), new BankNote(100, "Baht"),
				new BankNote(500, "Baht"), new BankNote(1000, "Baht"));
		wallets = Arrays.asList(new BankNote(20, "Baht"), new BankNote(50, "Baht"), new BankNote(100, "Baht"),
				new BankNote(500, "Baht"), new BankNote(1000, "Baht"));
		Valuable twenty = new BankNote(20, CURRENCY);
		Valuable temp = strategy.withdraw(twenty, moneys).get(0);
		// Test that it return something not NULL
		assertEquals(strategy.withdraw(twenty, moneys).get(0), wallets.get(0));
		// Now if withdraw is not NULL the List should still the same
		assertEquals(moneys, wallets);
	}

	@Test
	public void testWithdrawOnlyRecursive() {
		moneys = Arrays.asList(new Coin(5, CURRENCY), new Coin(2, CURRENCY), new Coin(2, CURRENCY),
				new Coin(2, CURRENCY));
		GreedyWithdraw gWithdraw = new GreedyWithdraw();
		// Should be null
		assertNull(gWithdraw.withdraw(new Money(6, CURRENCY), moneys));
		// For Recursive withdraw should not null
		assertNotNull(strategy.withdraw(new Money(6, CURRENCY), moneys));

	}

	@Test
	public void testWithdrawFromEmpty() {
		// should return not thing
		assertNull(strategy.withdraw(new Money(50, CURRENCY), moneys));
		assertNull(strategy.withdraw(new Money(40, CURRENCY), moneys));
		assertNull(strategy.withdraw(new Money(30, CURRENCY), moneys));
		assertNull(strategy.withdraw(new Money(200, CURRENCY), moneys));
		moneys = Arrays.asList(new Coin(5, CURRENCY), new Coin(2, CURRENCY), new Coin(2, CURRENCY),
				new Coin(2, CURRENCY));
		assertNotNull(strategy.withdraw(new Money(6, CURRENCY), moneys));

	}
	
	@Test
	public void testWithdrawDifferentCurreny() {
		moneys = Arrays.asList(new Coin(5, CURRENCY), new Coin(2, CURRENCY), new Coin(2, "Ringgit"),
				new Coin(2, "Riggit"));
		assertNotEquals(new Money(5, CURRENCY), strategy.withdraw(new Money(5, CURRENCY), moneys));
		assertNotEquals(new Money(2, "Ringgit"), strategy.withdraw(new Money(2, "Ringgit"), moneys));
		assertNotEquals(new Money(2, "Baht"), strategy.withdraw(new Money(2, "Ringgit"), moneys));
	}

}
