package ir.mygroup.atmamulator.atm.repository;

import org.springframework.data.repository.CrudRepository;

import ir.mygroup.atmamulator.atm.model.CardTransaction;

public interface CardTransactionRepository extends CrudRepository<CardTransaction,Long> {
}
