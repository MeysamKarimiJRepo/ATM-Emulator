package com.meysam.emulator.atm.dto;

public class CheckingCardResponse {
    private String pan;
    private String ownerName;
    private String preferredAuthentication;

    public CheckingCardResponse(String pan, String ownerName, String preferredAuthentication) {
        this.pan = pan;
        this.ownerName = ownerName;
        this.preferredAuthentication = preferredAuthentication;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPreferredAuthentication() {
        return preferredAuthentication;
    }

    public void setPreferredAuthentication(String preferredAuthentication) {
        this.preferredAuthentication = preferredAuthentication;
    }
}
