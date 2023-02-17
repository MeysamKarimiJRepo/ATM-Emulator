package com.meysam.emulator.card.repository;

import com.meysam.emulator.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    @Query("SELECT card FROM Card card WHERE card.pan = :pan")
    Card findCardByPan(@Param("pan") String pan);
}
