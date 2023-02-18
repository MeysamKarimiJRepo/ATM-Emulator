package com.meysam.emulator;

import com.meysam.emulator.card.model.FinancialAccount;
import com.meysam.emulator.card.repository.FinancialAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;

@SpringBootTest
public class SavingFinancialAccountTests {

    @Autowired
    FinancialAccountRepository financialAccountRepository;

    @Test
    public void saveFinancialAccount() {
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccount.setAccountNumber("23423423");
        financialAccount.setBalance(new BigDecimal(0));
        financialAccount.setCardTransactions(null);
        financialAccount.setModificationDate(null);
        financialAccount.setCreationDate(Calendar.getInstance().getTime());
        financialAccountRepository.save(financialAccount);
    }
}
