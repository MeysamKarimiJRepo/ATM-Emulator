package com.meysam.emulator.card.dto;

public class CardAuthenticationRequest {
    private String pan;
    private String pin;
    private String fingerPrint;

    public CardAuthenticationRequest(String pan, String pin, String fingerPrint) {
        this.pan = pan;
        this.pin = pin;
        this.fingerPrint = fingerPrint;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }
}
