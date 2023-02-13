package com.software.atm.credit_card;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card,Long> {

    Page<Card>findAll(Pageable pageable);

    Boolean existsCardById(Long id);

    Boolean existsCardByCardNumber( String s);
    Boolean existsCardByPin( String s);
    Boolean existsCardByStatus( Status s);
    Boolean existsCardByExpireDate( Date s);


}
