package com.pavelch22.command;

import com.pavelch22.Operation;
import com.pavelch22.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that works with commands.
 */
public class CommandExecutor {
    private static Map<Operation, Command> commands = new HashMap();

    static {
        commands.put(Operation.LOGIN, new LoginCommand());
        commands.put(Operation.INFO, new InfoCommand());
        commands.put(Operation.DEPOSIT, new DepositCommand());
        commands.put(Operation.WITHDRAW, new WithdrawCommand());
        commands.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {

    }

    public static void execute(Operation operation) throws InterruptOperationException {
        commands.get(operation).execute();
    }
}
