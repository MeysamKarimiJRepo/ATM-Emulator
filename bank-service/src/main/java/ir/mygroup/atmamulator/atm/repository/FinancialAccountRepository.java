package ir.mygroup.atmamulator.atm.repository;

import ir.mygroup.atmamulator.atm.model.FinancialAccount;
import org.springframework.data.repository.CrudRepository;



public interface FinancialAccountRepository extends CrudRepository<FinancialAccount, Long> {
}
