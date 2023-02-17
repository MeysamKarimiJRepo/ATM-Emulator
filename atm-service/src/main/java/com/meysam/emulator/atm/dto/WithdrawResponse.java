package com.meysam.emulator.atm.dto;

import java.math.BigDecimal;

public class WithdrawResponse {

    private BigDecimal balance;
    private String pan;

    public WithdrawResponse(BigDecimal balance, String pan) {
        this.balance = balance;
        this.pan = pan;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
