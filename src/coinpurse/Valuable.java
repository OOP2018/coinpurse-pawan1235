package coinpurse;
/**
 *  An interface for objects having a monetary value and currency.
 * 
 * @author Pawan Intawongsa
 *
 */
public interface Valuable extends Comparable<Valuable>{
	/**
	 * Get value of Valuable
	 * @return value of Valuable
	 */
	public double getValue();

	/**
	 * Get currency of Valuable
	 * @return currency of Valuable
	 */
	public String getCurrency();
}
