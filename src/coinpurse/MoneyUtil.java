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
	 * Return a List of Coins that contains only the coins from coins (the
	 * parameter) that have same currency as the currency parameter.
	 * 
	 * @param coins
	 *            is List of Coin to be filtered
	 * @param currency
	 *            is Currency that we want to be return
	 * @return A List of Coins that contains only the coins from coins (the
	 *         parameter) that have same currency as the currency parameter.
	 */
	public static List<Valuable> filterByCurrency(List<Valuable> vals, String currency) {
		List<Valuable> temp = new ArrayList<Valuable>();
		for (Valuable val : vals) {
			if (val.getCurrency().equals(currency)) {
				temp.add(val);
			}
		}
		return temp;
	}

	/**
	 * Sort a list of coins and print the result on the console. Write a separate
	 * method to print the list.
	 * 
	 * @param vals
	 *            is List of coin to be sort
	 */
	public static void sortCoins(List<Valuable> vals) {
		Collections.sort(vals);;
	}
	
	/**
	 * Print all valuables description in List of valuable in parameter.
	 * @param vals
	 * 				is List of valuable we want to print
	 */
	public static void printVals(List<Valuable> vals) {
		for (Valuable val : vals) {
			System.out.println(val.getValue() + "-" + val.getCurrency());
		}
	}
}
