package coinpurse;

/**
 * A class to create bank note with its value and currency with unique serial
 * number.
 * 
 * @author Pawan Intawongsa
 *
 */
public class BankNote extends Money {
	/** Next unique serial number of bank note */
	private static long nextSerialNumber = 1000000;
	/** unique serial number of bank note */
	private long serialNumber;

	public BankNote(double value, String currency) {
		super(value,currency);
		this.serialNumber = nextSerialNumber++;
	}
	
	/**
	 * Create a bank note with value and currency and unique serial number.
	 * 
	 * @param value
	 *            is value of bank note
	 * @param currency
	 *            is currency of bank note
	 */
	public BankNote(double value, String currency,long serialNumber) {
		super(value, currency);
		this.serialNumber = serialNumber;
	}
	

	/**
	 * Return serial number of bank note
	 * 
	 * @return serial number of bank note
	 */
	public long getSerial() {
		return this.serialNumber;
	}


	/**
	 * Return bank note description
	 * 
	 * @return bank note description
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s note [%d]", super.getValue(), super.getCurrency(), this.serialNumber);
	}
}
