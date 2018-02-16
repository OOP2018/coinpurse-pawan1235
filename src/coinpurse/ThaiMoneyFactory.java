package coinpurse;

public class ThaiMoneyFactory extends MoneyFactory {

	@Override
	public Valuable createMoney(double value) {
		if (value == 5 || value == 10 || value == 1 || value == 2) {
			return new Coin(value, "Baht");
		}
		if (value == 20 || value == 50 || value == 100 || value == 500 || value == 1000) {
			return new BankNote(value, "Baht");
		} else
			throw new IllegalArgumentException();
	}
	public static void main(String[] args) {
		MoneyFactory m = MoneyFactory.getInstance();
		Valuable a = m.createMoney(5);
		System.out.println(a);
		Valuable b = m.createMoney(20);
		System.out.println(b);
	}
}
