package com.software.atm.account;


import com.software.atm.bank.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {

    Page<Account> findAll(Pageable pageable);

    List<Account> findAllByBank(Bank bank);

    List<Account> findByBankCode(Long code);

    List<Account>findByUserNationalCode(String s);
   Account findByAccountNumber(String s);


}
