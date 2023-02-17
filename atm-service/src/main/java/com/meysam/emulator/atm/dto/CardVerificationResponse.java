package com.meysam.emulator.atm.dto;

public class CardVerificationResponse {
    private String owner;
    private String pan;

    public CardVerificationResponse(String owner, String pan) {
        this.owner = owner;
        this.pan = pan;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
