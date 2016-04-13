package com.pavelch22.command;

import com.pavelch22.CashMachine;
import com.pavelch22.ConsoleHelper;
import com.pavelch22.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle resource = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login" + CashMachine.LANGUAGE);

    @Override
    public void execute() throws InterruptOperationException {
        boolean isLogged = false;
        while (!isLogged) {
            ConsoleHelper.writeMessage(resource.getString("enter.card.number"));
            String cardNumber = ConsoleHelper.readString();
            ConsoleHelper.writeMessage(resource.getString("enter.pin"));
            String pin = ConsoleHelper.readString();
            boolean isCorrectData = cardNumber.matches("\\d{12}") && pin.matches("\\d{4}");
            boolean isValidVerification = validCreditCards.containsKey(cardNumber) && validCreditCards.getObject(cardNumber).equals(pin);
            if (isCorrectData && isValidVerification) {
                ConsoleHelper.writeMessage(String.format(resource.getString("success.format"), cardNumber));
                isLogged = true;
            } else {
                ConsoleHelper.writeMessage(resource.getString("login.failed"));
            }
        }
    }
}
