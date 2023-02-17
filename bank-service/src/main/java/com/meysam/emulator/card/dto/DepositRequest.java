package com.meysam.emulator.card.dto;

import java.math.BigDecimal;

public class DepositRequest {
    private BigDecimal amount;
    private String pan;

    public DepositRequest(BigDecimal amount, String pan) {
        this.amount = amount;
        this.pan = pan;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
