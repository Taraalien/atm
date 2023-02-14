package com.software.atm.credit_card;

import com.software.atm.account.Account;
import com.software.atm.account.AccountService;
import com.software.atm.common.exceptions.ConflictException;
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
    private final AccountService accountService;

    @Override
    public Card save(Card card) {

        var card1=(List<Card>)cardRepository.findAll();

        for(Card card2 : card1){

            if (card2.getCardNumber().equals(card.getCardNumber())){

                throw new ConflictException("duplicated card number");
            }

        }

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
