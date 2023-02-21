package com.software.atm.account;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService  {


    Account save(Account account);

    Account update(Account account);

    void delete(Long id);

    Page<Account> paging(Integer page, Integer size);

    Account getById(Long id);


    List<Account> getAll();

    List<Account>getByBank(Long id);

    List<Account> getByBankCode(Long code);

    List<Account> getByUserNationalCode(String s);
    Account getByAccountNumber(String s);

    BigDecimal withdrawal(Long accountId,BigDecimal balance);
    BigDecimal deposit(Long accountId, BigDecimal balance);

}
