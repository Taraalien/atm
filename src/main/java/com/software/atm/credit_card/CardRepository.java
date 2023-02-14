package com.software.atm.credit_card;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card,Long> {

    Page<Card>findAll(Pageable pageable);

    List<Card> findByUserId(Long  id);





}
