package com.pavelch22.command;

import com.pavelch22.CashMachine;
import com.pavelch22.ConsoleHelper;
import com.pavelch22.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private static ResourceBundle resource = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        boolean isLogged = false;
        while (!isLogged) {
            ConsoleHelper.writeMessage("Enter card number (12 digits)");
            String number = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Enter card pin (4 digits)");
            String pin = ConsoleHelper.readString();
            boolean isCorrectData = number.matches("\\d{12}") && pin.matches("\\d{4}");
            boolean isValidVerification = resource.containsKey(number) && resource.getObject(number).equals(pin);
            if (isCorrectData && isValidVerification) {
                ConsoleHelper.writeMessage("Successful verification.");
                isLogged = true;
            } else {
                ConsoleHelper.writeMessage("Login failed. Try again.");
            }
        }
    }
}
