package com.meysam.emulator.card.service;

import com.meysam.emulator.card.dto.CardDTO;
import com.meysam.emulator.card.dto.DepositResponse;
import com.meysam.emulator.card.dto.WithdrawResponse;
import com.meysam.emulator.card.exception.AccountBusinessException;
import com.meysam.emulator.card.exception.CashWithdrawFailureException;
import com.meysam.emulator.card.exception.FinancialAccountServiceException;
import com.meysam.emulator.card.model.AuthenticationMethod;
import com.meysam.emulator.card.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankService {

    @Autowired
    private FinancialAccountService financialAccountService;

    @Autowired
    private CardService cardService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public BankService(FinancialAccountService financialAccountService, CardService cardService) {
        this.financialAccountService = financialAccountService;
        this.cardService = cardService;
    }

    public WithdrawResponse cashWithdraw(BigDecimal amount, String pan) throws CashWithdrawFailureException {
        Card card = cardService.findCardByPan(pan);
        if (card == null) {
            throw new CashWithdrawFailureException("card with pin=" + pan + "not found Exception");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new CashWithdrawFailureException("amount must be greater than zero");
        }

        try {
            financialAccountService.cashWithdraw(amount, card.getAccount());
        } catch (AccountBusinessException | FinancialAccountServiceException e) {
            throw new CashWithdrawFailureException(e.getMessage());
        }
        return new WithdrawResponse(card.getAccount().getBalance(), card.getPan());
    }

    public DepositResponse cashDeposit(BigDecimal amount, String pin) throws CashWithdrawFailureException {
        Card card = cardService.findCardByPan(pin);
        try {
            if (card == null) {
                throw new CashWithdrawFailureException("card with pin=" + pin + "not found Exception");
            }

            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                throw new CashWithdrawFailureException("amount must be greater than zero");
            }


            financialAccountService.cashDeposit(amount, card.getAccount());
        } catch (AccountBusinessException | FinancialAccountServiceException e) {
            throw new CashWithdrawFailureException(e.getMessage());
        }
        return new DepositResponse(card.getAccount().getBalance(), card.getPan());

    }

    public BigDecimal checkBalance(String pan) {
        Card card = cardService.findCardByPan(pan);
        if (card == null) {
            return null;
        }
        return card.getAccount().getBalance();
    }

    public boolean authenticateWithPin(String pan, String pin) {
        Card card = cardService.findCardByPan(pan);
        if (card == null) {
            return false;
        }

        return AuthenticationMethod.PIN.toString().equals(card.getPreferredAuthentication()) && passwordEncoder.matches(pin, card.getPin());
    }

    public boolean authenticateWithFingerPrint(String pan, String fingerPrint) {
        Card card = cardService.findCardByPan(pan);
        if (card == null) {
            return false;
        }

        return AuthenticationMethod.FINGERPRINT.toString().equals(card.getPreferredAuthentication()) &&
                card.getAccount().getOwner().getFingerPrint().equals(fingerPrint);
    }

    public CardDTO checkCardNumber(String pan) {
        Card card = cardService.findCardByPan(pan);
        CardMapper cardMapper = new CardMapper();
        return cardMapper.mapEntityToDTO(card);
    }

}
