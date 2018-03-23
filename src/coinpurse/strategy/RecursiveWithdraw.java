package coinpurse.strategy;

import java.util.ArrayList;
import java.util.List;

import coinpurse.Money;
import coinpurse.Valuable;

public class RecursiveWithdraw implements WithdrawStrategy {

	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
		if (amount.getValue() == 0) {
			return new ArrayList<>();
		}
		if (money.size() == 0) {
			return null;
		}
		Valuable first = money.get(0);
		if (amount.getCurrency().equals(first.getCurrency())) {

			Valuable remaining = new Money(amount.getValue() - first.getValue(), amount.getCurrency());
			List<Valuable> result = withdraw(remaining, money.subList(1, money.size()));
			if (result != null) {
				result.add(first);
				return result;
			}	
			if ((result = withdraw(amount, money.subList(1, money.size()))) != null) {
				return result;
			}
		}

		return null;
	}

}
