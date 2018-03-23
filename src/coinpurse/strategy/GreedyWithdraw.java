package coinpurse.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coinpurse.MoneyUtil;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

public class GreedyWithdraw implements WithdrawStrategy {

	 @Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
		money = MoneyUtil.filterByCurrency(money, amount.getCurrency());
		double amounts = amount.getValue();
		Comparator<Valuable> comp = new ValueComparator();
		Collections.sort(money, comp);
		List<Valuable> templist = new ArrayList<Valuable>();
		for (int i = money.size() - 1; i >= 0; i--) {
			if ((money.get(i).getValue() <= amounts)) {
				templist.add(money.get(i));
				amounts -= money.get(i).getValue();
			}
		}
		if (amounts != 0) {
			return null;
		}
		return templist;
	}
}
