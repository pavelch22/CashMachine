package com.pavelch22.command;

import com.pavelch22.ConsoleHelper;
import com.pavelch22.CurrencyManipulator;
import com.pavelch22.CurrencyManipulatorFactory;
import com.pavelch22.exception.InterruptOperationException;
import com.pavelch22.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.TreeMap;

class WithdrawCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int amount;
        try {
            while (true) {
                ConsoleHelper.writeMessage("Please, enter amount.");
                String input = ConsoleHelper.readString();
                boolean isValidInput = input.matches("\\d+") && Integer.parseInt(input) > 0;
                if (isValidInput) {
                    amount = Integer.parseInt(input);
                    if (manipulator.isAmountAvailable(amount)) {
                        Map<Integer, Integer> denominations = manipulator.withdrawAmount(amount);
                        TreeMap<Integer, Integer> sortedDenominations = new TreeMap<>(Collections.reverseOrder());
                        sortedDenominations.putAll(denominations);
                        for (Map.Entry<Integer, Integer> d : sortedDenominations.entrySet()) {
                            ConsoleHelper.writeMessage("\t" + d.getKey() + " - " + d.getValue());
                        }
                        ConsoleHelper.writeMessage("Success " + amount);
                        break;
                    } else {
                        ConsoleHelper.writeMessage("Not enough money");
                        break;
                    }
                } else {
                    ConsoleHelper.writeMessage("Enter correct amount and try again.");
                }
            }
        } catch (NotEnoughMoneyException | ConcurrentModificationException e) {
            ConsoleHelper.writeMessage("Something gone wrong. Try later.");
        }
    }
}
