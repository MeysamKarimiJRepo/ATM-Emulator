package com.meysam.emulator.card.exception;

public class FinancialAccountServiceException extends Exception{
    public FinancialAccountServiceException() {
    }

    public FinancialAccountServiceException(String message) {
        super(message);
    }

    public FinancialAccountServiceException(Throwable cause) {
        super(cause);
    }
}
