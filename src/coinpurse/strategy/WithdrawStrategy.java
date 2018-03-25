package coinpurse.strategy;

import java.util.List;

import coinpurse.Valuable;

/**
 * A Interface for a withdraw strategy
 * @author Pawan Intawongsa
 *
 */
public interface WithdrawStrategy {
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money);
}
