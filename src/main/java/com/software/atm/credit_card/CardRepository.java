package com.software.atm.credit_card;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card,Long> {

    Page<Card>findAll(Pageable pageable);

    Boolean existsCardById(Long id);

    Boolean existsCardByCardNumber(@NotNull String s);
    Boolean existsCardByPin(@NotNull String s);
    Boolean existsCardByStatus(@NotNull Status s);
    Boolean existsCardByExpireDate(@NotNull Date s);


}
