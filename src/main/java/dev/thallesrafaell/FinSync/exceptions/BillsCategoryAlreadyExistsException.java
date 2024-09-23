package dev.thallesrafaell.FinSync.exceptions;

public class BillsCategoryAlreadyExistsException extends RuntimeException {
    public BillsCategoryAlreadyExistsException(String message) {
        super(message);
    }
}
