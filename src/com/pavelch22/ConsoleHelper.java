package com.pavelch22;

import com.pavelch22.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class for work with console.
 */
public class ConsoleHelper {
    private static final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Writes message to console.
     *
     * @param message message to write
     */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads string from console.
     *
     * @return string from console
     * @throws InterruptOperationException
     */
    public static String readString() throws InterruptOperationException {
        String s = "";
        try {
            s = console.readLine();
        } catch (IOException ignored) {

        }
        if (s.equalsIgnoreCase("exit")) {
            throw new InterruptOperationException();
        }
        return s;
    }

    /**
     * Asks for currency code.
     *
     * @return currency code
     * @throws InterruptOperationException
     */
    public static String askCurrencyCode() throws InterruptOperationException {
        String currency = "";
        while (true) {
            writeMessage("Please, enter currency code (3 letters)");
            currency = readString();
            if (currency.length() == 3) {
                break;
            } else {
                writeMessage("You have entered incorrect data. Try again.");
            }
        }
        return currency.toUpperCase();
    }

    /**
     * Reads and returns two valid digits from console.
     *
     * @return array of digits
     * @throws InterruptOperationException
     */
    public static String[] getValidTwoDigits() throws InterruptOperationException {
        String[] result = new String[2];
        while (true) {
            writeMessage("Please, enter denomination and quantity (100 5, for example).");
            String input = readString();
            String[] strings = input.split("\\s");
            String denomination = strings[0];
            String quantity = strings[1];
            if (denomination.matches("\\d+") && (Integer.parseInt(denomination) > 0) && quantity.matches("\\d") && (Integer.parseInt(quantity) > 0)) {
                result[0] = denomination;
                result[1] = quantity;
                return result;
            } else {
                writeMessage("You have entered incorrect data. Try again.");
            }
        }
    }

    /**
     * Asks for operation.
     *
     * @return chosen operation
     * @throws InterruptOperationException
     */
    public static Operation askOperation() throws InterruptOperationException {
        Operation operation = null;
        while (operation == null) {
            try {
                writeMessage("Choose operation.");
                Operation[] operations = Operation.values();
                for (int i = 1; i < operations.length; i++) {
                    writeMessage(i + " - " + operations[i]);
                }
                int code = Integer.parseInt(readString());
                operation = Operation.getAllowableOperationByOrdinal(code);
            } catch (IllegalArgumentException e) {
                writeMessage("You have entered invalid data. Try again.");
            }
        }
        return operation;
    }
}