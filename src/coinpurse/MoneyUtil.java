package coinpurse;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contain a useful method for List of Coin it can return List of
 * coin that all same currency, Sort list of Coin
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
	public static List<Coin> filterByCurrency(List<Coin> coins, String currency) {
		List<Coin> temp = new ArrayList<Coin>();
		for (Coin coin : coins) {
			if (coin.getCurrency() == currency) {
				temp.add(coin);
			}
		}
		return temp;
	}

	/**
	 * Sort a list of coins and print the result on the console. Write a separate
	 * method to print the list
	 * 
	 * @param coins
	 *            is List of coin to be sort
	 */
	public static void sortCoins(List<Coin> coins) {
		java.util.Collections.sort(coins);
	}

}
