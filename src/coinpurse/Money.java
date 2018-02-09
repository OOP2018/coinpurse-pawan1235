package coinpurse;

/**
 * A class to create a money with values and currency
 * 
 * @author Pawan Intawongsa
 *
 */
public class Money implements Valuable {
	private double values;
	private String currency;

	/**
	 * Create a Money with value and currency
	 * 
	 * @param values
	 *            is value of this Money
	 * @param currency
	 *            is currency of this Money
	 */
	public Money(double values, String currency) {
		this.values = values;
		this.currency = currency;
	}

	/**
	 * Compare 2 Moneys. If they have same currency it will order by value else
	 * order by currency.
	 * 
	 * @return If they have same currency return 1 if this value more than arg0
	 *         value. 0 if they have same value -1 .If this value less than arg0
	 *         value If they have different currency will return a alphabet order.
	 */
	@Override
	public int compareTo(Valuable arg0) {
		if (!this.getCurrency().equals(arg0.getCurrency())) {
			return this.getCurrency().compareTo(arg0.getCurrency());
		}
		return Double.compare(this.getValue(), arg0.getValue());
	}

	/**
	 * Get value of this money
	 * 
	 * @return value of this money
	 */
	@Override
	public double getValue() {
		return this.values;
	}

	/**
	 * Get currency of this money
	 * 
	 * @return currency of this money
	 */
	@Override
	public String getCurrency() {
		return this.currency;
	}

	/**
	 * check 2 moneys if they equal or not.
	 * 
	 * @return If they have same value and currency return true ,else return false
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Money other = (Money) obj;
		if (this.getValue() == other.getValue() && this.getCurrency().equals(other.getCurrency())) {
			return true;
		} else
			return false;
	}
}
