package com.software.atm.credit_card;

import com.software.atm.account.Account;
import com.software.atm.account.AccountService;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CardImple implements CardService{

    private final CardRepository cardRepository;
    private final UserService userService;

    private final AccountService accountService;

    @Transactional
    @Override
    public Card save(Card card) {

        Long userId=card.getUser().getId();
        User user=userService.getById(userId);
        card.setUser(user);
        Long accountId=card.getAccount().getId();
        Account  account=accountService.getById(accountId);
        card.setAccount(account);

        if(!(card.getPin().length() == 4)){

            throw new ConflictException("pin length should be 4 .");
        }
        var card1=(List<Card>)cardRepository.findAll();

        for(Card card2 : card1){

            if (card2.getCardNumber().equals(card.getCardNumber())){

                throw new ConflictException("duplicated card number");
            }

        }

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
        List<Card>cards=cardRepository.findAllByUser(user);
        return cards;
    }

    @Override
    public List<Card> getByUserNationalCode(String code){
        return cardRepository.findAllByUserNationalCode(code);
    }

    @Override
    public Card getByUserNationalCodeAndAccountNumber(String code, String accountNumber) {
        return cardRepository.findAllByUserNationalCodeAndAccount_AccountNumber(code,accountNumber);
    }

    @Override
    public List<Card> getByStatus(Status status) {
        return cardRepository.findAllByStatus(status);
    }

    @Override
    public BigDecimal getAmount(String s) {
        Card card=cardRepository.findByCardNumber(s);
        return card.getAccount().getBalance();
    }

    @Override
    public Card changePassword(String  cardNumber,String pin) {
        Card  card=cardRepository.findByCardNumber(cardNumber);
        card.setPin(pin);
        return cardRepository.save(card);
    }


}
