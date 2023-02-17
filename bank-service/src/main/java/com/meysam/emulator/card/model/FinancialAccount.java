package com.meysam.emulator.card.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "financial_account")
public class FinancialAccount implements Serializable {
    @Id
    @SequenceGenerator(
            name = "financial_account_sequence",
            sequenceName = "financial_account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "financial_account_sequence"
    )
    @Column(name = "id", updatable = false)
    private int id;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "account_number", unique = true)
    private String AccountNumber;
    @Column(name = "creation_date")

    private Date creationDate;

    @Column(name = "modification_date")
    private Date modificationDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    private Customer owner;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CardTransaction> cardTransactions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Set<CardTransaction> getTransactions() {
        return cardTransactions;
    }

    public void setTransactions(Set<CardTransaction> cardTransactions) {
        this.cardTransactions = cardTransactions;
    }
}
