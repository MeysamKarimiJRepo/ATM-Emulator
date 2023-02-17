package com.meysam.emulator.card.repository;

import com.meysam.emulator.card.model.*;
import com.meysam.emulator.card.model.FinancialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {
}
