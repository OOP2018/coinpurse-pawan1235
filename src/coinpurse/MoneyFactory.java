package coinpurse;

/**
 * An abstract class for each country money factory to edit for their currency.
 * It contains some method that every factory must have.
 * 
 * @author Pawan Intawongsa
 */
public abstract class MoneyFactory {
	private static MoneyFactory factory = null;
	
	/**
	 * return instance of MoneyFactory.
	 * 
	 * @return instance of MoneyFactory
	 */
	public static MoneyFactory getInstance() {
		return factory;
	}

	/**
	 * Abstract class to create Valuable 
	 * @param value of the Valuable
	 * @return Valuable
	 */
	public abstract Valuable createMoney(double value);
	
	/**
	 * parse a String value to double and return it in double.
	 * 
	 * @param value
	 *            amount of money to be set
	 * @return Valuable
	 */
	public Valuable createMoney(String value) {
		double amount = 0;
		try {
			amount = Double.parseDouble(value);
		}catch (IllegalArgumentException e) {
			System.out.println("Sorry, " + value + " is not a valid amount.");
		}
		return createMoney(amount);
	}
	
	/**
	 * Set the factory to f in the parameter.
	 * 
	 * @param moneyfactory
	 *            is the MoneyFactory to set.
	 */
	public static void setMoneyFactory(MoneyFactory mf) {
		factory = mf;
	}
}
