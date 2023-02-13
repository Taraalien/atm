package com.software.atm.account;

import com.software.atm.common.exceptions.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountImple implements AccountService{

    private final AccountRepository accountRepository;
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        Account account1=getById(account.getId());
        account1.setBalance(account.getBalance());
        account1.setAccountType(account.getAccountType());
        return accountRepository.save(account1);
    }

    @Override
    public void delete(Long id) {

        accountRepository.deleteById(id);
    }

    @Override
    public Page<Account> paging(Integer page, Integer size) {
        return accountRepository.findAll(PageRequest.of(page,size, Sort.by("id").ascending()));
    }

    @Override
    public Account getById(Long id) {
        Optional<Account>account=accountRepository.findById(id);
        if(!account.isPresent()){
            throw new NotFound("not found id");
        }
        return account.get();
    }

    @Override
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll();
    }
}
