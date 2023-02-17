package com.meysam.emulator.atm.service;

import com.meysam.emulator.atm.dto.*;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@Service

public class CardService {
    private static final String CARD_SERVICE = "CARD_SERVICE";
    private Logger logger = LoggerFactory.getLogger(CardService.class);
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = CARD_SERVICE, fallbackMethod = "checkCardFallBack")
    @Bulkhead(name = CARD_SERVICE)
    @Retry(name = CARD_SERVICE)
    public CheckingCardResult checkCard(String pan) {
        String url = "/checkCardNumber/" + pan;
        logger.trace(String.format("checking card number for %s", pan));
        ResponseEntity<CheckingCardResult> result = restTemplate.getForEntity(url, CheckingCardResult.class);
        return result.getBody();
    }
    @CircuitBreaker(name = CARD_SERVICE, fallbackMethod = "checkBalanceFallBack")
    @Bulkhead(name = CARD_SERVICE)
    @Retry(name = CARD_SERVICE)
    public BigDecimal checkBalance(String pan) {
        String url = "/getBalance/" + pan;
        logger.trace(String.format("checking balance for %s", pan));
        ResponseEntity<BigDecimal> result = restTemplate.getForEntity(url, BigDecimal.class);
        return result.getBody();
    }
    @CircuitBreaker(name = CARD_SERVICE, fallbackMethod = "cashDepositFallBack")
    @Bulkhead(name = CARD_SERVICE)
    @Retry(name = CARD_SERVICE)
    public DepositResponse cashDeposit(String pan, BigDecimal amount) {
        String url = "/cashDeposit";
        logger.trace(String.format("deposit for card %s amount %f", pan, amount));
        ResponseEntity<DepositResponse> result = restTemplate.postForEntity(url, new DepositRequest(amount, pan), DepositResponse.class);
        return result.getBody();
    }
    @CircuitBreaker(name = CARD_SERVICE, fallbackMethod = "cashWithdrawFallBack")
    @Bulkhead(name = CARD_SERVICE)
    @Retry(name = CARD_SERVICE)
    public WithdrawResponse cashWithdraw(String pan, BigDecimal amount) {
        String url = "/cashWithdraw";
        logger.trace(String.format("withdraw for card %s amount %f", pan, amount));
        ResponseEntity<WithdrawResponse> result = restTemplate.postForEntity(url, new WithdrawRequest(pan, amount), WithdrawResponse.class);
        return result.getBody();
    }

    private CheckingCardResult checkCardFallBack(){
        return null;
    }

    private BigDecimal checkBalanceFallBack(){
        return null;
    }

    private DepositResponse cashDepositFallBack(){
        return null;
    }

    private WithdrawResponse cashWithdrawFallBack(){
        return null;
    }
}
