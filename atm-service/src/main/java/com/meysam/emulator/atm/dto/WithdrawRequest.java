package com.meysam.emulator.atm.dto;

import java.math.BigDecimal;

public class WithdrawRequest {
    private String pan;
    private BigDecimal amount;

    public WithdrawRequest(String pan, BigDecimal amount) {
        this.pan = pan;
        this.amount = amount;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

