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
		if(super.getCurrency().equals("Riggit") && super.getValue() < 1) {
			return String.format("%.0f-sen", super.getValue()*100);
		}
		return super.getValue() + "-" + super.getCurrency()+" coin";
	}
}
