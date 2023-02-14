package com.software.atm.bank;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BankService {

    Bank save(Bank bank);

    Bank update(Bank bank);

    void delete(Long id);

    Page<Bank> paging(Integer page, Integer size);

    Bank getById(Long id);

    List<Bank> getAll();
}
