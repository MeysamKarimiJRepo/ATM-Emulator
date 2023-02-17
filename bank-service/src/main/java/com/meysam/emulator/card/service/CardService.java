package com.meysam.emulator.card.service;
import com.meysam.emulator.card.model.*;
import com.meysam.emulator.card.repository.*;
import com.meysam.emulator.card.model.Card;
import com.meysam.emulator.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;


    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card findCardByPan(String pin) {
        return cardRepository.findCardByPan(pin);
    }

    public String getPreferredAuthentication(String pan){
      return cardRepository.findCardByPan(pan).getPreferredAuthentication();
    }
}
