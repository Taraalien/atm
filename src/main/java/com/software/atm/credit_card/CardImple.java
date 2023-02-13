package com.software.atm.credit_card;

import com.software.atm.common.exceptions.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CardImple implements CardService{

    private final CardRepository cardRepository;

    @Override
    public Card save(Card card) {

        return cardRepository.save(card);
    }

    @Override
    public Card update(Card card) {
        Card card1=getById(card.getId());
        card1.setCardNumber(card.getCardNumber());
        card1.setPin(card.getPin());
        card1.setStatus(card.getStatus());
        card1.setExpireDate(card.getExpireDate());
        return cardRepository.save(card1);
    }

    @Override
    public void delete(Long id) {

        if(cardRepository.existsCardById(id).equals(null))
        {
            throw new NotFound("Not found Id");
        }
        cardRepository.deleteById(id);

    }

    @Override
    public Page<Card> paging(Integer page, Integer size) {
        return cardRepository.findAll(PageRequest.of(page,size, Sort.by("id").ascending()));
    }

    @Override
    public Card getById(Long id) {
        Optional<Card>optionalCard=cardRepository.findById(id);
        if(!optionalCard.isPresent()){
            throw new NotFound("id not found");
        }
        return optionalCard.get();
    }

    @Override
    public List<Card> getAll() {
        return (List<Card>) cardRepository.findAll();
    }
}
