package com.pavelch22.command;

import com.pavelch22.CashMachine;
import com.pavelch22.ConsoleHelper;
import com.pavelch22.CurrencyManipulator;
import com.pavelch22.CurrencyManipulatorFactory;
import com.pavelch22.exception.InterruptOperationException;
import com.pavelch22.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command {
    private ResourceBundle resource = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw", CashMachine.LOCALE);

    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        try {
            while (true) {
                ConsoleHelper.writeMessage(resource.getString("enter.amount"));
                String input = ConsoleHelper.readString();
                boolean isValidInput = input.matches("\\d+") && Integer.parseInt(input) > 0;
                if (isValidInput) {
                    int amount = Integer.parseInt(input);
                    if (manipulator.isAmountAvailable(amount)) {
                        Map<Integer, Integer> denominations = manipulator.withdrawAmount(amount);
                        TreeMap<Integer, Integer> sortedDenominations = new TreeMap<>(Collections.reverseOrder());
                        sortedDenominations.putAll(denominations);
                        for (Map.Entry<Integer, Integer> d : sortedDenominations.entrySet()) {
                            ConsoleHelper.writeMessage("\t" + d.getKey() + " - " + d.getValue());
                        }
                        ConsoleHelper.writeMessage(String.format(resource.getString("success.format"), amount));
                        break;
                    } else {
                        ConsoleHelper.writeMessage(resource.getString("not.enough.money"));
                        break;
                    }
                } else {
                    ConsoleHelper.writeMessage(resource.getString("enter.correct.data"));
                }
            }
        } catch (NotEnoughMoneyException | ConcurrentModificationException e) {
            ConsoleHelper.writeMessage(resource.getString("error"));
        }
    }
}
