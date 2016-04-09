import exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Class for work with currencies and denominations.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    /**
     * Constructs an empty currency manipulator.
     *
     * @param currencyCode the name of the manipulator
     */
    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    /**
     * Returns currency code.
     *
     * @return currency code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Adds the amount of money to the current manipulator.
     *
     * @param denomination denomination value
     * @param count        count of denominations
     */
    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    /**
     * Returns total amount of money in the current manipulator.
     *
     * @return total amount of money in the manipulator
     */
    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> e : denominations.entrySet()) {
            totalAmount += e.getKey() * e.getValue();
        }
        return totalAmount;
    }

    /**
     * Checks if the manipulator has money.
     *
     * @return true if the manipulator has money
     */
    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }


    /**
     * Returns true if the manipulator has expected amount of money.
     *
     * @param expectedAmount the value for compare with total amount of money in the manipulator
     * @return true if the manipulator has expected amount
     */
    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    /**
     * Returns map of denominations. This method uses "Greedy algorithm".
     *
     * @param expectedAmount withdrawal amount of money
     * @return map of denominations
     * @throws NotEnoughMoneyException if the manipulator doesn't have enough money or proper denominations
     */
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        Map<Integer, Integer> denominationsCopy = new HashMap<>(denominations);
        TreeSet<Integer> bills = new TreeSet<>(Collections.reverseOrder());
        bills.addAll(denominationsCopy.keySet());
        int rest = expectedAmount;
        for (Integer bill : bills) {
            while (rest - bill >= 0) {
                if (denominationsCopy.get(bill) > 0) {
                    if (result.containsKey(bill)) {
                        result.put(bill, result.get(bill) + 1);
                    } else {
                        result.put(bill, 1);
                    }
                    rest -= bill;
                    denominationsCopy.put(bill, denominationsCopy.get(bill) - 1);
                } else {
                    break;
                }
            }
        }

        if (rest != 0) {
            throw new NotEnoughMoneyException();
        }

        denominations = denominationsCopy;

        return result;
    }
}
