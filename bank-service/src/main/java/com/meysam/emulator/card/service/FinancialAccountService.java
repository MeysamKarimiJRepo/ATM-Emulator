package com.meysam.emulator.card.service;

import com.meysam.emulator.card.exception.AccountBusinessException;
import com.meysam.emulator.card.exception.FinancialAccountServiceException;
import com.meysam.emulator.card.model.FinancialTransaction;
import com.meysam.emulator.card.model.FinancialAccount;
import com.meysam.emulator.card.model.TransactionStatus;
import com.meysam.emulator.card.model.TransactionType;
import com.meysam.emulator.card.repository.FinancialTransactionRepository;
import com.meysam.emulator.card.repository.FinancialAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class FinancialAccountService {

    private final FinancialAccountRepository financialAccountRepository;

    private final FinancialTransactionRepository financialTransactionRepository;


    public FinancialAccountService(FinancialAccountRepository financialAccountRepository, FinancialTransactionRepository financialTransactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.financialTransactionRepository = financialTransactionRepository;
    }


    public void cashWithdraw(BigDecimal amount, FinancialAccount financialAccount) throws AccountBusinessException, FinancialAccountServiceException {
        validateAmount(amount);
        validateAccount(financialAccount);
        try {
            if (financialAccount.getBalance().compareTo(amount) < 0) {
                throw new AccountBusinessException("Withdrawal amount exceeded account balance");
            }
            BigDecimal currentBalance = financialAccount.getBalance();
            BigDecimal newBalance = currentBalance.subtract(amount);
            financialAccount.setBalance(newBalance);
            FinancialTransaction financialTransaction = addAccountTransactionToAccount(LocalDateTime.now(), amount,
                    TransactionType.WITHDRAW.toString(), TransactionStatus.COMPLETE.toString());
            addTransactionToAccountTransactions(financialAccount, financialTransaction);
            financialAccountRepository.save(financialAccount);
        } catch (Exception e) {
            throw new FinancialAccountServiceException(e.getMessage());
        }
    }


    public void cashDeposit(BigDecimal amount, FinancialAccount financialAccount) throws AccountBusinessException, FinancialAccountServiceException {
        validateAmount(amount);
        validateAccount(financialAccount);
        try {
            BigDecimal currentBalance = financialAccount.getBalance();
            BigDecimal newBalance = currentBalance.add(amount);
            financialAccount.setBalance(newBalance);
            FinancialTransaction financialTransaction = addAccountTransactionToAccount(LocalDateTime.now(), amount,
                    TransactionType.DEPOSIT.toString(), TransactionStatus.COMPLETE.toString());
            addTransactionToAccountTransactions(financialAccount, financialTransaction);
            financialAccountRepository.save(financialAccount);
        } catch (Exception e) {
            throw new FinancialAccountServiceException(e.getMessage());
        }
    }

    private void addTransactionToAccountTransactions(FinancialAccount financialAccount, FinancialTransaction financialTransaction) {
        //todo load accountTransactionsList
        if (financialAccount.getCardTransactions() != null && !financialAccount.getCardTransactions().isEmpty()) {
            financialAccount.getCardTransactions().add(financialTransaction);
        } else {
            Set<FinancialTransaction> financialTransactions = new HashSet<>();
            financialTransactions.add(financialTransaction);
            financialAccount.setCardTransactions(financialTransactions);
        }
    }


    private FinancialTransaction addAccountTransactionToAccount(LocalDateTime date, BigDecimal amount, String type, String status) {
        FinancialTransaction financialTransaction = new FinancialTransaction(date, amount, type, status);
        financialTransactionRepository.save(financialTransaction);
        return financialTransaction;
    }

    private void validateAccount(FinancialAccount financialAccount) throws AccountBusinessException {
        if (financialAccount == null) {
            throw new AccountBusinessException("account not found exception");
        }
    }

    private void validateAmount(BigDecimal amount) throws AccountBusinessException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountBusinessException("mount must be greater than zero");
        }
    }
}

