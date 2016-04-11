package com.pavelch22.command;

import com.pavelch22.ConsoleHelper;
import com.pavelch22.exception.InterruptOperationException;

class ExitCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        //Confirmation (y/n)
        ConsoleHelper.writeMessage("Good bye.");
    }
}
