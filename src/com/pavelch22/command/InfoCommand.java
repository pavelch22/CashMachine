package com.pavelch22.command;

import com.pavelch22.ConsoleHelper;
import com.pavelch22.CurrencyManipulator;
import com.pavelch22.CurrencyManipulatorFactory;
import com.pavelch22.exception.InterruptOperationException;

class InfoCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        boolean isEmpty = true;
        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manipulator.hasMoney()) {
                ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                isEmpty = false;
            }
        }
        if (isEmpty) {
            ConsoleHelper.writeMessage("No money");
        }
    }
}
