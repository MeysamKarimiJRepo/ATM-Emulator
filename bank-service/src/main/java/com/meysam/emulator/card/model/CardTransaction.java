package com.meysam.emulator.card.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "card_transaction")
public class CardTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "card_transaction_sequence",
            sequenceName = "card_transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "card_transaction_sequence"
    )
    @Column(name = "id", updatable = false)
    private int id;
    @Column(name = "date")

    private LocalDateTime date;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private String status;

    public CardTransaction() {
    }

    public CardTransaction(LocalDateTime date, BigDecimal amount, String type, String status) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
