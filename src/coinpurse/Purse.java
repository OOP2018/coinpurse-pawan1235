package coinpurse;

import coinpurse.strategy.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A coin purse contains valuables. You can insert valuables, withdraw money,
 * check the balance, and check if the purse is full.
 * 
 * @author Pawan Intawongsa
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Valuable> money = new ArrayList<Valuable>();

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set when
	 * the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Comparator if first object has the same currency it will compare by value
	 * else it will compare by alphabet order.
	 */
	private Comparator<Valuable> comp = new ValueComparator();

	private WithdrawStrategy strategy;

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of valuables you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
		strategy = new GreedyWithdraw();
	}

	/**
	 * Count and return the number of valuables in the purse. This is the number of
	 * valuables, not their value.
	 * 
	 * @return the number of valuables in the purse
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
		for (Valuable val : this.money) {
			sum += val.getValue();
		}
		return sum;
	}

	/**
	 * Return the capacity of the valuable purse.
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
	 * Insert a valuable into the purse. The valuable is only inserted if the purse
	 * has space for it and the coin has positive value. No worthless valuables!
	 * 
	 * @param val
	 *            is a Valuable object to insert into purse
	 * @return true if valuable inserted, false if can't insert
	 */
	public boolean insert(Valuable val) {
		if (this.isFull()) {
			return false;
		} else {
			if (val.getValue() <= 0) {
				return false;
			} else {
				this.money.add(val);
				return true;
			}
		}
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Valuables
	 * withdrawn from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Valuable objects for money withdraw
	 */
	public Valuable[] withdraw(double amount) {
		if (amount < 0 || amount > this.getBalance())
			return null;
		Valuable a = new Money(amount, "Baht");
		Valuable[] temp = withdraw(a);

		return temp;
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Valuables
	 * withdrawn from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the Valuable to withdraw
	 * @return array of Valuable objects for money withdraw
	 */
	public Valuable[] withdraw(Valuable amount) {
		if (amount.getValue() < 0 || amount.getValue() > this.getBalance())
			return null;

		List<Valuable> temp = strategy.withdraw(amount, this.money);

		if (temp != null) {
			for (Valuable val : temp) {
				this.money.remove(val);
			}
			Valuable[] vals = new Valuable[temp.size()];
			temp.toArray(vals);
			return vals;
		}
		return null;
	}

	/**
	 * toString returns a string description of the purse contents. It can return
	 * whatever is a useful description.
	 */
	public String toString() {
		return this.count() + " items with value " + this.getBalance();
	}
}
