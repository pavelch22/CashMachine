package com.pavelch22;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for work with currency manipulators.
 */
public class CurrencyManipulatorFactory {
    private static final Map<String, CurrencyManipulator> currencyManipulators = new HashMap<>();

    private CurrencyManipulatorFactory() {

    }

    /**
     * Returns currency manipulator for currency code.
     *
     * @param currencyCode currency code
     * @return currency manipulator associated with currency code
     */
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (currencyManipulators.containsKey(currencyCode)) {
            return currencyManipulators.get(currencyCode);
        }
        currencyManipulators.put(currencyCode, new CurrencyManipulator(currencyCode));
        return currencyManipulators.get(currencyCode);
    }


    /**
     * Returns all currency manipulators.
     *
     * @return collection of currency manipulators
     */
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return currencyManipulators.values();
    }
}
