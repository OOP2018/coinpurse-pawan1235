package coinpurse;

/**
 * A MoneyFactory for Thai money. Used to create the Baht coin, and
 * Baht bank note.
 * 
 * @author Pawan Intawongsa
 */
public class ThaiMoneyFactory extends MoneyFactory {
	
	/**
	 * Create Coin and Bank note if the input value is in the list. else throw
	 * IllegalArgumentException.
	 */
	private long nextSerialNumber = 1000000;
	@Override
	public Valuable createMoney(double value) {
		if (value == 5 || value == 10 || value == 1 || value == 2) {
			return new Coin(value, "Baht");
		}
		if (value == 20 || value == 50 || value == 100 || value == 500 || value == 1000) {
			return new BankNote(value, "Baht",nextSerialNumber++);
		} else
			throw new IllegalArgumentException("Sorry, " + value + " is not a valid amount.");
	}
}
