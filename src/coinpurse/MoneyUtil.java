package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that contain a useful method for List of Coin it can return List of
 * coin that all same currency, Sort list of Coin.
 * 
 * @author Pawan Intawongsa
 *
 */
public class MoneyUtil {
	/**
	 * Return a List of Class that extends Valuable that contains only the coins from coins (the
	 * parameter) that have same currency as the currency parameter.
	 * 
	 * @param coins
	 *            is List of Coin to be filtered
	 * @param currency
	 *            is Currency that we want to be return
	 * @return A List of Class that extends Valuable that contains only the coins from coins (the
	 *         parameter) that have same currency as the currency parameter.
	 */
	// public static List<Valuable> filterByCurrency(List<Valuable> vals, String
	// currency) {
	public static <E extends Valuable> List<E> filterByCurrency(List<E> vals, String currency) {
		List<E> temp = new ArrayList<E>();
		for (E val : vals) {
			if (val.getCurrency().equals(currency)) {
				temp.add(val);
			}
		}
		return temp;
	}

	/**
	 * Sort a list of class that extends Valuable and print the result on the console. Write a separate
	 * method to print the list.
	 * 
	 * @param vals
	 *            is List of class that extends Valuable to be sort
	 */
	public static void sortMoney(List<? extends Valuable> vals) {
		Collections.sort(vals);
	}

	/**
	 * Print all valuables description in List of valuable in parameter.
	 * 
	 * @param vals
	 *            is List of valuable we want to print
	 */
	public static void printVals(List<Valuable> vals) {
		for (Valuable val : vals) {
			System.out.println(val.toString());
		}
	}

	/**
	 * Return the larger argument, based on sort order, using the objects' own
	 * compareTo method for comparing.
	 * 
	 * @param args
	 *            one or more Comparable objects to compare.
	 * @return the argument that would be last if sorted the elements.
	 * @throws IllegalArgumentException
	 *             if no arguments given.
	 */
	public static <E extends Comparable<? super E>> E max(E... args) {
		if (args == null) {
			throw new IllegalArgumentException();
		}
		E max = args[0];
		for (E e : args) {
			if (e.compareTo(max) > 0) {
				max = e;
			}
		}
		return max;
	}
}
