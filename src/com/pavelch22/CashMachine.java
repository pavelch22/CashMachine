package com.pavelch22;

import com.pavelch22.command.CommandExecutor;
import com.pavelch22.exception.InterruptOperationException;

/**
 * Main class
 */
public class CashMachine {
    public static String RESOURCE_PATH = "com.pavelch22.resources.";

    public static void main(String[] args) {
        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation = null;
            while (operation != Operation.EXIT) {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("Good bye.");
        }
    }
}
