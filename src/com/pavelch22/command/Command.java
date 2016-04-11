package com.pavelch22.command;

import com.pavelch22.exception.InterruptOperationException;

/**
 * A class can implement the <code>Command</code> interface when it
 * wants to execute some tasks.
 */
interface Command {
    /**
     * Executes some tasks.
     *
     * @throws InterruptOperationException
     */
    void execute() throws InterruptOperationException;
}
