package com.software.atm.credit_card;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CardService {


    Card save(Card card);

    Card update(Card card);

    void delete(Long id);

    Page<Card> paging(Integer page, Integer size);

    Card getById(Long id);


    List<Card> getAll();


}
