package coinpurse;

import java.util.ArrayList;
import java.util.List;

/**
 * A coin purse contains coins. You can insert coins, withdraw money, check the
 * balance, and check if the purse is full.
 * 
 * @author Pawan Intawongsa
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Coin> money = new ArrayList<Coin>();

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set when
	 * the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of coins you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Count and return the number of coins in the purse. This is the number of
	 * coins, not their value.
	 * 
	 * @return the number of coins in the purse
	 */
	public int count() {
		return this.money.size();
	}

	/**
	 * Get the total value of all items in the purse.
	 * 
	 * @return the total value of items in the purse.
	 */
	public double getBalance() {
		double sum = 0;
		for (Coin coin : this.money) {
			sum += coin.getValue();
		}
		return sum;
	}

	/**
	 * Return the capacity of the coin purse.
	 * 
	 * @return the capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Test whether the purse is full. The purse is full if number of items in purse
	 * equals or greater than the purse capacity.
	 * 
	 * @return true if purse is full.
	 */
	public boolean isFull() {
		if (this.getCapacity() > this.count()) {
			return false;
		}
		return true;
	}

	/**
	 * Insert a coin into the purse. The coin is only inserted if the purse has
	 * space for it and the coin has positive value. No worthless coins!
	 * 
	 * @param coin
	 *            is a Coin object to insert into purse
	 * @return true if coin inserted, false if can't insert
	 */
	public boolean insert(Coin coin) {
		if (this.isFull()) {
			return false;
		} else {
			if (coin.getValue() <= 0) {
				return false;
			} else {
				this.money.add(coin);
				return true;
			}
		}
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Coins withdrawn
	 * from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Coin objects for money withdrawn, or null if cannot withdraw
	 *         requested amount.
	 */
	public Coin[] withdraw(double amount) {
		List<Coin> temp = new ArrayList<Coin>();
		MoneyUtil.sortCoins(this.money);
		if (amount != 0) {
			if (amount <= this.getBalance()) {
				for (Coin coin : this.money) {
					if (amount >= coin.getValue()) {
						amount -= coin.getValue();
						temp.add(coin);
					}
				}
				if (amount == 0) {
					for (Coin coin : temp) {
						this.money.remove(coin);
					}
				} else {
					return null;
				}
				Coin[] coins = new Coin[temp.size()];
				temp.toArray(coins);
				return coins;
			}
		}
		return null;
	}

	/**
	 * toString returns a string description of the purse contents. It can return
	 * whatever is a useful description.
	 */
	public String toString() {
		return this.count() + " coins with value " + this.getBalance();
	}
}
