package com.pavelch22.command;

import com.pavelch22.CashMachine;
import com.pavelch22.ConsoleHelper;
import com.pavelch22.CurrencyManipulator;
import com.pavelch22.CurrencyManipulatorFactory;
import com.pavelch22.exception.InterruptOperationException;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle resource = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info", CashMachine.LOCALE);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(resource.getString("info"));
        boolean isEmpty = true;
        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manipulator.hasMoney()) {
                ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                isEmpty = false;
            }
        }
        if (isEmpty) {
            ConsoleHelper.writeMessage(resource.getString("no.money"));
        }
    }
}
