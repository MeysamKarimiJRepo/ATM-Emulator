package ir.mygroup.atmamulator.atm.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Card")
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String pan;
    @Column(nullable = false)
    private String pin;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId")
    private FinancialAccount financialAccount;
    @Column(nullable = false)
    private String preferredAuthentication;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
