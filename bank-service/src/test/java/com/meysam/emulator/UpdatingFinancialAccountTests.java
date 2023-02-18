package com.meysam.emulator;

import com.meysam.emulator.card.model.Customer;
import com.meysam.emulator.card.model.FinancialAccount;
import com.meysam.emulator.card.repository.CustomerRepository;
import com.meysam.emulator.card.repository.FinancialAccountRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;

@SpringBootTest
public class UpdatingFinancialAccountTests {

    @Autowired
    FinancialAccountRepository financialAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeAll
    public void saveFinancialAccount() {
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccount.setAccountNumber("23423423");
        financialAccount.setBalance(new BigDecimal(0));
        financialAccount.setCardTransactions(null);
        financialAccount.setModificationDate(null);
        financialAccount.setCreationDate(Calendar.getInstance().getTime());
        financialAccountRepository.save(financialAccount);

        Customer customer = new Customer();
        customer.setFatherName("fatherName");
        customer.setFingerPrint("myFin");
        customer.setLastName("myLast");
        customer.setFirstName("firstName");
        customer.setNationalCode("55845");
        customerRepository.save(customer);
    }

    @Test
    public void updateFinancialAccount() {
        Optional<FinancialAccount> financialAccountOptional = financialAccountRepository.findById(1l);
        FinancialAccount financialAccount = null;
        if (financialAccountOptional.isPresent()) {
            financialAccount = financialAccountOptional.get();
        }

        Assert.isTrue(financialAccount != null);
        Optional<Customer> customerOptional = customerRepository.findById(1l);
        Assert.isTrue(customerOptional.isPresent());
        financialAccount.setOwner(customerOptional.get());
        financialAccount = financialAccountRepository.save(financialAccount);
        Assert.isTrue(financialAccount.getOwner() != null);
    }
}
