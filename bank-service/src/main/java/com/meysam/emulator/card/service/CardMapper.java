package com.meysam.emulator.card.service;


import com.meysam.emulator.card.dto.CardDTO;
import com.meysam.emulator.card.model.Card;

public class CardMapper implements Mapper<Card, CardDTO> {

    @Override
    public CardDTO mapEntityToDTO(Card card) {
        return new CardDTO(card.getPin(), card.getPreferredAuthentication(), card.getAccount().getBalance(), card.getAccount().getOwner().getFirstName() + "" + card.getAccount().getOwner().getLastName());
    }

    @Override
    public Card mapDTOToEntity(CardDTO cardDTO) {
        Card card = new Card();
        card.setPan(cardDTO.getPan());
        card.setPreferredAuthentication(cardDTO.getPreferredAuthentication());
        return card;
    }

}
