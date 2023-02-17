package com.meysam.emulator.card.repository;


import com.meysam.emulator.card.model.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction,Long> {
}
