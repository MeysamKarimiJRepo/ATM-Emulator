package ir.mygroup.atmamulator.atm.repository;

import ir.mygroup.atmamulator.atm.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
