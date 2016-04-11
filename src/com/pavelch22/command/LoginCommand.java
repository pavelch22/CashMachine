package com.pavelch22.command;

import com.pavelch22.ConsoleHelper;
import com.pavelch22.exception.InterruptOperationException;

public class LoginCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        boolean isLogged = false;
        while (!isLogged) {
            ConsoleHelper.writeMessage("Enter card number and pin");
            String number = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            //always valid for testing purposes
            boolean isValidVerification = true/*number.matches("\\d{12}") && pin.matches("\\d{4}")*/;
            if (!isValidVerification) {
                ConsoleHelper.writeMessage("Wrong number or pin. Try again.");
            } else {
                isLogged = true;
            }
        }
    }
}
