package com.meysam.emulator;

import com.meysam.emulator.card.controller.BankController;
import com.meysam.emulator.card.dto.DepositRequest;
import com.meysam.emulator.card.dto.DepositResponse;
import com.meysam.emulator.card.exception.CashWithdrawFailureException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootTest
public class ControllerTests {

    @Autowired
    BankController bankController;

    @Test
    public void cashDeposit_validAmount() throws CashWithdrawFailureException {
        DepositRequest depositRequest = new DepositRequest(new BigDecimal(1000), "5552-5566-2119-5201");
        ResponseEntity<DepositResponse> responseEntity = bankController.cashDeposit(depositRequest);
    }
}
