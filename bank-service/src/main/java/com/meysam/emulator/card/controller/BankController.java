package com.meysam.emulator.card.controller;

import com.meysam.emulator.card.dto.*;
import com.meysam.emulator.card.exception.CashWithdrawFailureException;
import com.meysam.emulator.card.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/bank")
public class BankController {
    protected Logger logger = Logger.getLogger(BankController.class.getName());

    private BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/cashWithdraw")
    public ResponseEntity<WithdrawResponse> cashWithDraw(@RequestBody WithdrawRequest withdrawRequest) throws CashWithdrawFailureException {
        WithdrawResponse result = null;
        try {
            result = bankService.cashWithdraw(withdrawRequest.getAmount(), withdrawRequest.getPan());
            return new ResponseEntity<WithdrawResponse>(result, HttpStatus.OK);

        } catch (CashWithdrawFailureException e) {
            logger.log(Level.WARNING, "Exception raised cashWithdraw REST Call " + e);
            return new ResponseEntity<WithdrawResponse>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception raised cashWithdraw REST Call " + e);
            return new ResponseEntity<WithdrawResponse>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @PostMapping("/cashDeposit")
    public ResponseEntity<DepositResponse> cashDeposit(@RequestBody DepositRequest depositRequest) throws CashWithdrawFailureException {
        DepositResponse result;
        try {
            result = bankService.cashDeposit(depositRequest.getAmount(), depositRequest.getPan());
            return new ResponseEntity<DepositResponse>(result, HttpStatus.OK);

        } catch (CashWithdrawFailureException e) {
            logger.log(Level.WARNING, "Exception raised cashDeposit REST Call " + e);
            return new ResponseEntity<DepositResponse>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception raised cashDeposit REST Call " + e);
            return new ResponseEntity<DepositResponse>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/getBalance")
    ResponseEntity<BigDecimal> getBalance(@RequestBody String pan) throws CashWithdrawFailureException {
        try {
            BigDecimal balance = bankService.checkBalance(pan);
            if (balance != null) {
                return new ResponseEntity<BigDecimal>(balance, HttpStatus.OK);
            } else {
                return new ResponseEntity<BigDecimal>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception raised getBalance REST Call", e);
            return new ResponseEntity<BigDecimal>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/checkCardNumber/{pan}")
    ResponseEntity<CardVerificationResponse> checkCardNumber(@PathVariable("pan")  String pan) throws CashWithdrawFailureException {
        try {
            CardDTO card = bankService.checkCardNumber(pan);
            if (card != null) {
                return new ResponseEntity<CardVerificationResponse>(new CardVerificationResponse(card.getPan(), card.getOwnerName(), card.getPreferredAuthentication()), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception raised checkCardNumber REST Call", e);
            return new ResponseEntity<CardVerificationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/authenticateWithPin")
    public ResponseEntity<AuthenticationResponse> authenticateWithPin(@RequestBody CardAuthenticationRequest cardAuthenticationRequest) throws CashWithdrawFailureException {
        boolean result = false;
        try {
            result = bankService.authenticateWithPin(cardAuthenticationRequest.getPan(), cardAuthenticationRequest.getPin());
            if (result) {
                return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(cardAuthenticationRequest.getPan(), true), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pan or pin is not correct!");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception raised authenticateWithPin REST Call", e);
            return new ResponseEntity<AuthenticationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/authenticateWithFingerPrint")
    public ResponseEntity<AuthenticationResponse> authenticateWithFingerPrint(@RequestBody CardAuthenticationRequest cardAuthenticationRequest) throws CashWithdrawFailureException {
        boolean result = false;
        try {
            result = bankService.authenticateWithFingerPrint(cardAuthenticationRequest.getPan(), cardAuthenticationRequest.getFingerPrint());

            if (result) {
                return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(cardAuthenticationRequest.getPan(), true), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pin or pan is not correct");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception raised authenticateWithFingerPrint REST Call", e);
            return new ResponseEntity<AuthenticationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Fallback method for checkCardNumber()
     *
     * @param pan
     * @return
     */
    public ResponseEntity<CardVerificationResponse> defaultCard(
            @PathVariable String pan) {
        return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
    }
}

