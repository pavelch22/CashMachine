/**
 * Shows the list of all supported operations.
 */
public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    /**
     * Returns operation by index.
     *
     * @param i index of operation
     * @return operation by index
     */
    public static Operation getAllowableOperationByOrdinal(int i) {
        if (i < 1 || i >= Operation.values().length) {
            throw new IllegalArgumentException();
        }
        return Operation.values()[i];
    }
}
