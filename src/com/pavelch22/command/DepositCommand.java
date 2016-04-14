package com.pavelch22.command;

import com.pavelch22.CashMachine;
import com.pavelch22.ConsoleHelper;
import com.pavelch22.CurrencyManipulator;
import com.pavelch22.CurrencyManipulatorFactory;
import com.pavelch22.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle resource = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit", CashMachine.locale);

    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] digits = ConsoleHelper.getValidTwoDigits();
        int denomination = Integer.parseInt(digits[0]);
        int quantity = Integer.parseInt(digits[1]);
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        currencyManipulator.addAmount(denomination, quantity);
        ConsoleHelper.writeMessage(String.format(resource.getString("success.format"), denomination, quantity));
    }
}
