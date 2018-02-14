package coinpurse;

import java.util.Comparator;

/**
 * For create comparator if first object has the same currency it will compare by value
 * else it will compare by alphabet order.
 * @author Pawan Intawongsa
 *
 */
public class ValueComparator implements Comparator<Valuable> {

	/**
	 * Compare two objects that implement Valuable. First compare them by currency,
	 * so that "Baht" < "Dollar". If both objects have the same currency, order them
	 * by value.
	 */
	@Override
	public int compare(Valuable o1, Valuable o2) {
		if (o1.getCurrency().equals(o2.getCurrency())) {
			if (o1.getValue() < o2.getValue())
				return -1;
			if (o1.getValue() > o2.getValue())
				return 1;
			else
				return 0;
		}
			return o1.getCurrency().compareTo(o2.getCurrency());

	}

}
