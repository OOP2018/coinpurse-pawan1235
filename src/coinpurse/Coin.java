package coinpurse;

/**
 * A class to create coin with its value and currency.
 * 
 * @author Pawan Intawongsa
 *
 */
public class Coin implements Comparable<Coin> {
	/** value of coin */
	private double value;
	/** currency of coin */
	private String currency;

	/**
	 * Create a coin with value and currency.
	 * 
	 * @param value
	 *            is value of coin
	 * @param currency
	 *            is currency of coin
	 */
	public Coin(double value, String currency) {
		this.value = value;
		this.currency = currency;
	}

	/**
	 * Get value of coin
	 * 
	 * @return value of coin
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Get currency of coin
	 * 
	 * @return currency of coin
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Check 2 coins if its equal each other or not.
	 * 
	 * @param arg
	 *            is an object to use to check if it equal coin.
	 */
	@Override
	public boolean equals(Object arg) {
		if (arg == null)
			return false;
		if (arg.getClass() != this.getClass())
			return false;
		Coin other = (Coin) arg;
		if (this.getValue() == other.getValue() && this.getCurrency().equals(other.getCurrency())) {
			return true;
		} else
			return false;
	}

	/**
	 * Compare 2 coin if they are more or less or equal to each other.
	 * 
	 * @param coin
	 *            is another coin to use to compare
	 */
	public int compareTo(Coin coin) {
		if (this.getValue() < coin.getValue())
			return -1;
		if (this.getValue() > coin.getValue())
			return 1;
		return 0;
	}

	/**
	 * Return coin's description
	 * 
	 * @return coin's description
	 */
	@Override
	public String toString() {
		return this.value + "-" + this.currency;
	}
}
