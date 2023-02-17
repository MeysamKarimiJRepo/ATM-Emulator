package com.meysam.emulator.card.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CardDTO implements Serializable {
    private String pan;
    private String preferredAuthentication;
    private BigDecimal amount;
    private String ownerName;


    public CardDTO(String pan, String preferredAuthentication, BigDecimal amount, String ownerName) {
        this.pan = pan;
        this.preferredAuthentication = preferredAuthentication;
        this.amount = amount;
        this.ownerName = ownerName;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPreferredAuthentication() {
        return preferredAuthentication;
    }

    public void setPreferredAuthentication(String preferredAuthentication) {
        this.preferredAuthentication = preferredAuthentication;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
