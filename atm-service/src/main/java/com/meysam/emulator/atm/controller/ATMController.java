package com.meysam.emulator.atm.controller;


import com.meysam.emulator.atm.dto.CardVerificationResponse;
import com.meysam.emulator.atm.dto.CheckingCardResult;
import com.meysam.emulator.atm.dto.DepositResponse;
import com.meysam.emulator.atm.dto.WithdrawResponse;
import com.meysam.emulator.atm.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/")
public class ATMController {
    @Autowired
    private CardService cardService;


    @RequestMapping({"/checkCard"})
    public ResponseEntity<CheckingCardResult> checkCard(@RequestBody String pan) {
        CheckingCardResult checkingCardResult = cardService.checkCard(pan);
        return new ResponseEntity<>(checkingCardResult, HttpStatus.OK);
    }

    @RequestMapping({"/cashDeposit"})
    public ResponseEntity<DepositResponse> cashDeposit(@RequestBody String pan, BigDecimal amount) {
        DepositResponse depositResponse = cardService.cashDeposit(pan, amount);
        return new ResponseEntity<>(depositResponse, HttpStatus.OK);
    }

    @RequestMapping({"/cashWithdraw"})
    public ResponseEntity<WithdrawResponse> cashWithdraw(@RequestBody String pan, BigDecimal amount) {
        WithdrawResponse withdrawResponse = cardService.cashWithdraw(pan, amount);
        return new ResponseEntity<>(withdrawResponse, HttpStatus.OK);
    }


    /**
     * Fallback method for checkCard
     * @param pan
     * @return
     */
    public ResponseEntity<CardVerificationResponse> defaultCheckCard(
            @RequestBody String pan) {
        return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
    }

    /**
     * Fallback method for cashDeposit
     * @param pan
     * @param amount
     * @return
     */
    public ResponseEntity<DepositResponse> defaultCashDeposit(@RequestBody String pan, BigDecimal amount) {
        return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
    }

    /**
     *  Fallback method for cashWithdraw
     * @param pan
     * @param amount
     * @return
     */
    public ResponseEntity<WithdrawResponse> defaultCashWithdraw(@RequestBody String pan, BigDecimal amount) {
        WithdrawResponse withdrawResponse = cardService.cashWithdraw(pan, amount);
        return new ResponseEntity<>(withdrawResponse, HttpStatus.OK);
    }

}
