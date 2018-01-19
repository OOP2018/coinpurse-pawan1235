package coinpurse;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Coin implements Comparable<Coin> {
	private double value;
	private String currency;

	public Coin(double value, String currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public String getCurrency() {
		return currency;
	}

	@Override
	public boolean equals(Object arg) {
		if (arg == null)
			return false;
		if (arg.getClass() != this.getClass())
			return false;
		Coin other = (Coin) arg;
		if (this.getValue() == other.getValue() && this.getCurrency() == other.getCurrency()) {
			return true;
		} else
			return false;
	}

	public int compareTo(Coin coin) {
		if (this.getValue() < coin.getValue())
			return 1;
		if (this.getValue() > coin.getValue())
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return this.value + "-" + this.currency;
	}

	public static void main(String[] args) {
		List<Coin> coins = new ArrayList<Coin>();
		List<Coin> temp = new ArrayList<Coin>();
		coins.add(new Coin(10.0, "Baht"));
		coins.add(new Coin(0.5, "Baht"));
		coins.add(new Coin(2.0, "Baht")); // the most hated coin
		coins.add(new Coin(1.0, "Baht"));
		coins.add(new Coin(50, "Rupe"));
		coins.add(new Coin(50, "Baht"));
		printCoins(coins);
		// This static method sorts any list of Comparable objects
		System.out.println("---------------------------------");
		Collections.sort(coins);
		temp = coins;
		printCoins(temp); // the coins should be sorted by value now

	}

	private static void printCoins(List<Coin> coins) {
		for (Coin coin : coins) {
			System.out.println(coin.getValue() + "-" + coin.getCurrency());
		}
	}
}
