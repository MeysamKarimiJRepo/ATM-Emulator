package com.meysam.emulator.card.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "card")
public class Card implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "card_sequence",
            sequenceName = "card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "card_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(nullable = false)
    private String pan;
    @Column(nullable = false)
    private String pin;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId")
    private FinancialAccount financialAccount;
    @Column(nullable = false)
    private String preferredAuthentication;

    public Card() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public FinancialAccount getAccount() {
        return financialAccount;
    }

    public void setAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    public String getPreferredAuthentication() {
        return preferredAuthentication;
    }

    public void setPreferredAuthentication(String preferredAuthentication) {
        this.preferredAuthentication = preferredAuthentication;
    }
}
