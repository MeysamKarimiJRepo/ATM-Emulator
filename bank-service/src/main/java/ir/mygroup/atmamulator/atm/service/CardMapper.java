package ir.mygroup.atmamulator.atm.service;

import ir.mygroup.atmamulator.atm.dto.CardDTO;
import ir.mygroup.atmamulator.atm.model.Card;

public class CardMapper implements Mapper<Card, CardDTO> {

    @Override
    public CardDTO mapEntityToDTO(Card card) {
        CardDTO cardDTO = new CardDTO(card.getPin(), card.getPreferredAuthentication(), card.getAccount().getBalance(), card.getAccount().getOwner().getFirstName() + "" + card.getAccount().getOwner().getLastName());
        return cardDTO;
    }

    @Override
    public Card mapDTOToEntity(CardDTO cardDTO) {
        Card card = new Card();
        card.setPan(cardDTO.getPan());
        card.setPreferredAuthentication(cardDTO.getPreferredAuthentication());
        return card;
    }

}
