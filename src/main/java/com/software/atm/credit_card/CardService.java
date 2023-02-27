package com.software.atm.credit_card;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface CardService {


    Card save(Card card);

    Card update(Card card);

    void delete(Long id);

    Page<Card> paging(Integer page, Integer size);

    Card getById(Long id);

    List<Card> getAll();

    List<Card>getByUser(Long id);

    List<Card>getByUserNationalCode(String code);
    Card getByUserNationalCodeAndAccountNumber(String code,String accountNumber);

    List<Card>getByStatus(Status status);

    BigDecimal getAmount(String s);

    Card changePassword(String cardNumber,String pin);

    BigDecimal withdrawal(String cardNumber,BigDecimal amount);

    BigDecimal deposit(String cardNumber, BigDecimal amount);

    Card getByCardNumber(String  cardNum);




}
