package coinpurse;

/**
 * A MoneyFactory for Malay money. Used to create the  Sen coin,
 * and Ringgint bank note.
 * 
 * @author Pawan Intawongsa
 */
public class MalayMoneyFactory extends MoneyFactory {
	/**
	 * Create Coin and Banknote if the input value is in the list. else throw
	 * IllegalArgumentException.
	 */
	private long nextSerialNumber = 1000000;
	@Override
	public Valuable createMoney(double value) {
		if (value == 0.05 || value == 0.1 || value == 0.2 || value == 0.5) {
			return new Coin(value, "Riggit");
		}
		if (value == 1 || value == 2 || value == 5 || value == 10 || value == 20 || value == 50 || value == 100) {
			return new BankNote(value, "Riggit",nextSerialNumber++);
		} else
			throw new IllegalArgumentException("Sorry, " + value + " is not a valid amount.");
	}
}
