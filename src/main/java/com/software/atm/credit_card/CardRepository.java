package com.software.atm.credit_card;


import com.software.atm.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card,Long> {

    Page<Card>findAll(Pageable pageable);

    List<Card> findAllByUser(User user);

    List<Card>findAllByUserNationalCode(String code);

    Card findAllByUserNationalCodeAndAccount_AccountNumber(String code,String accountNumber);

    List<Card>findAllByStatus(Status status);

    Card findByCardNumber(String number);




}
