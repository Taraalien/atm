package com.software.atm.credit_card;

import com.software.atm.account.Account;
import com.software.atm.account.AccountService;
import com.software.atm.common.exceptions.ConflictException;
import com.software.atm.common.exceptions.NotFound;
import com.software.atm.user.User;
import com.software.atm.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

            log.error("pin length should be 4");
            throw new ConflictException("pin length should be 4 .");
        }
        var card1=(List<Card>)cardRepository.findAll();

        for(Card card2 : card1){

            if (card2.getCardNumber().equals(card.getCardNumber())){

                log.error("duplicated card number");
                throw new ConflictException("duplicated card number");
            }

        }

        log.info("save  card");
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
        log.info("update  card");
        return cardRepository.save(card1);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        log.info("delete  card");
        cardRepository.deleteById(id);

    }

    @Transactional
    @Override
    public Page<Card> paging(Integer page, Integer size) {
        log.info("get all  card");
        return cardRepository.findAll(PageRequest.of(page,size, Sort.by("id").ascending()));
    }

    @Transactional
    @Override
    public Card getById(Long id) {
        log.info("get by card id");
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

    @Transactional
    @Override
    public List<Card> getByUser(Long id) {
        log.info("get card by user id");
        User user=userService.getById(id);
        List<Card>cards=cardRepository.findAllByUser(user);
        return cards;
    }

    @Transactional
    @Override
    public List<Card> getByUserNationalCode(String code){
        log.error("this national code did not have card or maybe did not register here");
        if(cardRepository.existsCardByUser_NationalCode(code)==false){

            throw new  NotFound("this national code did not have card or maybe did not register here");
        }
        return cardRepository.findAllByUserNationalCode(code);
    }

    @Transactional
    @Override
    public Card getByUserNationalCodeAndAccountNumber(String code, String accountNumber) {
        log.info("get card by national code and account number");
        return cardRepository.findAllByUserNationalCodeAndAccount_AccountNumber(code,accountNumber);
    }

    @Transactional
    @Override
    public List<Card> getByStatus(Status status) {
        log.info("get card by status");
        return cardRepository.findAllByStatus(status);
    }

    @Transactional
    @Override
    public BigDecimal getAmount(String s) {
        log.info("get card amount");
        Card card=cardRepository.findByCardNumber(s);

        if(cardRepository.existsCardByCardNumber(s)==false){

            throw new NotFound("not found card number");
        }
        return card.getAccount().getBalance();
    }

    @Transactional
    @Override
    public Card changePassword(String  cardNumber,String pin) {
        log.info("change password");
        Card  card=cardRepository.findByCardNumber(cardNumber);
        card.setPin(pin);
        return cardRepository.save(card);
    }

    @Transactional
    @Override
    public BigDecimal withdrawal(String cardNumber, BigDecimal amount) {

        log.info("withdrawal cash");
        Card card=cardRepository.findByCardNumber(cardNumber);
        Long accountId=card.getAccount().getId();
        BigDecimal balance=accountService.withdrawal(accountId,amount);
        return balance;
    }


    @Transactional
    @Override
    public BigDecimal deposit(String cardNumber, BigDecimal amount) {

        log.info("deposit cash");
        Card card=cardRepository.findByCardNumber(cardNumber);
        Long accountId=card.getAccount().getId();
        BigDecimal balance=accountService.deposit(accountId,amount);
        return balance;
    }


    @Transactional
    @Override
    public Card getByCardNumber(String  cardNum) {
        log.info("get card by card number");
        Card optionalCard=cardRepository.findByCardNumber(cardNum);
        return optionalCard;
    }



}
