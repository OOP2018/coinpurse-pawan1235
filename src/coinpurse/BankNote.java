package coinpurse;

/**
 * A class to create bank note with its value and currency with unique
 * serial number.
 * 
 * @author Pawan Intawongsa
 *
 */
public class BankNote implements Valuable {
	/** value of bank note*/
	private double value;
	/** Next unique serial number of bank note */
	private static long nextSerialNumber = 1000000;
	/** Currency of bank note*/
	private String currency;
	/** unique serial number of bank note */
	private long serialNumber;
	
	
    /**
     * Create a bank note with value and currency and unique serial number.
     * 
     * @param value
     * 				is value of bank note
     * @param currency
     * 				is currency of bank note
     */
	public BankNote(double value, String currency) {
		this.value = value;
		this.currency = currency;
		this.serialNumber = nextSerialNumber++;
	}
	
	/**
	 * Get value of bank note
	 * 
	 * @return value of bank note
	 */
	public double getValue() {
		return value;
	}
	/**
	 * Get currency of bank note
	 * 
	 * @return currency of bank note
	 */
	public String getCurrency() {
		return currency;
	}
	
	/**
	 * Get unique serial number of bank note
	 * 
	 * @return unique serial number of bank note
	 */
	public static long getNextSerialNumber() {
		return nextSerialNumber;
	}
	
	/**
	 * Check 2 bank notes if its equal each other or not.
	 * 
	 * @param obj
	 *            is an object to use to check if it equal coin.
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		BankNote other = (BankNote) obj;
		if (this.getValue() == other.getValue() && this.getCurrency().equals(other.getCurrency())) {
			return true;
		} else
			return false;
	}

	/**
	 * Return bank note description
	 * 
	 * @return bank note description
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s note [%d]", this.value, this.currency, this.serialNumber);
	}

	/**
	 * Compare between 2 bank notes
	 */
	@Override
	public int compareTo(Valuable banknote) {
		if (this.value < banknote.getValue()) {
			return -1;
		} else if (this.value > banknote.getValue()) {
			return 1;
		} else {
			return 0;
		}
	}
}
