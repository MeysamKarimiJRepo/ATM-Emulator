package com.meysam.emulator.card.exception;

public class CardAuthenticationFailureException extends Exception {
    public CardAuthenticationFailureException(String pan) {
        super(pan);
    }
}
