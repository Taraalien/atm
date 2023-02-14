package com.software.atm.credit_card;

import com.software.atm.common.exceptions.ConflictException;
import com.software.atm.common.exceptions.NotFound;
import com.software.atm.user.User;
import com.software.atm.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CardImple implements CardService{

    private final CardRepository cardRepository;
    private final UserService userService;

    @Transactional
    @Override
    public Card save(Card card) {

        Long userId=card.getUser().getId();
        User user=userService.getById(userId);

        if(!(card.getPin().length() == 4)){

            throw new ConflictException("pin is not true");
        }
        var card1=(List<Card>)cardRepository.findAll();

        for(Card card2 : card1){

            if (card2.getCardNumber().equals(card.getCardNumber())){

                throw new ConflictException("duplicated card number");
            }

        }

        card.setUser(user);
        return cardRepository.save(card);
    }

    @Transactional
    @Override
    public Card update(Card card) {
        Card card1=getById(card.getId());
        card1.setCardNumber(card.getCardNumber());
        card1.setPin(card.getPin());
        card1.setStatus(card.getStatus());
        card1.setExpireDate(card.getExpireDate());
        Long userId=card.getUser().getId();
        User user=userService.getById(userId);
        card1.setUser(user);
        return cardRepository.save(card1);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        cardRepository.deleteById(id);

    }

    @Transactional
    @Override
    public Page<Card> paging(Integer page, Integer size) {
        return cardRepository.findAll(PageRequest.of(page,size, Sort.by("id").ascending()));
    }

    @Transactional
    @Override
    public Card getById(Long id) {
        Optional<Card>optionalCard=cardRepository.findById(id);
        if(!optionalCard.isPresent()){
            throw new NotFound("id not found");
        }
        return optionalCard.get();
    }

    @Transactional
    @Override
    public List<Card> getAll() {
        return (List<Card>) cardRepository.findAll();
    }

    @Override
    public List<Card> getByUser(Long id) {
        User user=userService.getById(id);
        List<Card>cards=cardRepository.findByUserId(user.getId());
        return cards;
    }


}
