package com.meysam.emulator;

import com.meysam.emulator.card.model.AuthenticationMethod;
import com.meysam.emulator.card.model.Card;
import com.meysam.emulator.card.repository.CardRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SavingCardTests {
    public static final String RAW_PASSWORD = "56112";
    @Autowired
    CardRepository cardRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void saveCard(){
        Card card = new Card();
        card.setAccount(null);
        card.setPan("5552-5566-2119-5201");
        String encodedPassowrd = passwordEncoder.encode(RAW_PASSWORD);
        card.setPin(encodedPassowrd);
        card.setPreferredAuthentication(AuthenticationMethod.PIN.name());
        card = cardRepository.save(card);
        Assert.isTrue(card.getId() != null);
        Assert.isTrue(passwordEncoder.matches(RAW_PASSWORD, encodedPassowrd));
    }

    @Test
    public void saveCard_redundantPin_failes(){
        Card card = new Card();
        card.setAccount(null);
        card.setPan("5552-5566-2119-5201");
        String encodedPassowrd = passwordEncoder.encode(RAW_PASSWORD);
        card.setPin(encodedPassowrd);
        card.setPreferredAuthentication(AuthenticationMethod.PIN.name());
        card = cardRepository.save(card);
        Assert.isTrue(card.getId() != null);
        Assert.isTrue(passwordEncoder.matches(RAW_PASSWORD, encodedPassowrd));
    }

    @Test
    public void saveCard_InvalidMatch_failes(){
        Card card = new Card();
        card.setAccount(null);
        card.setPan("5552-5566-2119-5563");
        String encodedPassowrd = passwordEncoder.encode(RAW_PASSWORD);
        card.setPin(encodedPassowrd);
        card.setPreferredAuthentication(AuthenticationMethod.PIN.name());
        card = cardRepository.save(card);
        Assert.isTrue(card.getId() != null);
        Assert.isTrue(passwordEncoder.matches("asas", encodedPassowrd));
    }
}
