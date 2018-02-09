package coinpurse;

/**
 * A class to create coin with its value and currency.
 * 
 * @author Pawan Intawongsa
 *
 */
public class Coin extends Money {

	/**
	 * Create a coin with value and currency.
	 * 
	 * @param value
	 *            is value of coin
	 * @param currency
	 *            is currency of coin
	 */
	public Coin(double value, String currency) {
		super(value, currency);
	}

	/**
	 * Return coin's description
	 * 
	 * @return coin's description
	 */
	@Override
	public String toString() {
		return super.getValue() + "-" + super.getCurrency();
	}
	public static void main(String[] args) {
		Coin a = new Coin(5,"bath");
		Coin b = new Coin(5,"rupi");
		System.out.println(a.equals(b));
	}
}
