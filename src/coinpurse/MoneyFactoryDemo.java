package coinpurse;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is a demo test to show that MoneyFactory is a singleton and show its methods is working fine
 * @author Pawan Intawongsa
 *
 */
public class MoneyFactoryDemo {
	public static void main(String[] args) {

		MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
		MoneyFactory factory = MoneyFactory.getInstance();

		MoneyFactory factory2 = MoneyFactory.getInstance();
		System.out.println(factory == factory2);// Test if factory and factory2 is the same object
		System.out.println("------------------");
		List<Valuable> p = new ArrayList<Valuable>();
		p.add(factory.createMoney(5));// create Money by double in parameter
		p.add(factory.createMoney(10));// create Money by double in parameter
		p.add(factory.createMoney("20"));// create Money by String in parameter
		p.add(factory.createMoney("1000"));// create Money by String in parameter
		p.add(factory.createMoney(5.0));// create Money by double in parameter
		MoneyUtil.printVals(p);
	}
}
