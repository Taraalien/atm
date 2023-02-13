package com.software.atm.account;

import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService  {


    Account save(Account account);

    Account update(Account account);

    void delete(Long id);

    Page<Account> paging(Integer page, Integer size);

    Account getById(Long id);


    List<Account> getAll();
}
