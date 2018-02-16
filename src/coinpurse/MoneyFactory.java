package coinpurse;

public abstract class MoneyFactory {
	private static MoneyFactory factory = null;

	public static MoneyFactory getInstance() {
		if (factory == null)
			factory = new ThaiMoneyFactory();
		return factory;
	}

	public abstract Valuable createMoney(double value);

	public Valuable createMoney(String value) {
		return createMoney(Double.parseDouble(value));
	}

	public static void setMoneyFactory(MoneyFactory mf) {
		factory = mf;
	}

	public static void main(String[] args) {
		// get the actual money factory.
		MoneyFactory factory = MoneyFactory.getInstance();
		// get a "10" Baht, Ringgit, or whatever
		Valuable b = factory.createMoney("0");
		Double a = new Double(1);
		System.out.println(b);
		
	}
}
