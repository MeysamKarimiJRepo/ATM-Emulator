package com.meysam.emulator.card.exception;

public class CashWithdrawFailureException extends Exception{
    public CashWithdrawFailureException() {
    }

    public CashWithdrawFailureException(String message) {
        super(message);
    }
}
