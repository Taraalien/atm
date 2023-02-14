package com.software.atm.account;

import com.software.atm.bank.Bank;
import com.software.atm.bank.BankService;
import com.software.atm.common.exceptions.ConflictException;
import com.software.atm.common.exceptions.NotFound;
import com.software.atm.user.User;
import com.software.atm.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountImple implements AccountService{

    private final AccountRepository accountRepository;
    private final BankService bankService;
    private final UserService userService;

    @Transactional
    @Override
    public Account save(Account account) {

        if(!(account.getAccountNumber().length()==10 ||
                account.getAccountNumber().length()==15)){

            throw new ConflictException("format not correct.");

        }
        Long bankId=account.getBank().getId();
        Bank bank=bankService.getById(bankId);
        account.setBank(bank);
        Long userId=account.getUser().getId();
        User user=userService.getById(userId);
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public Account update(Account account) {
        Account account1=getById(account.getId());
        account1.setBalance(account.getBalance());
        account1.setAccountType(account.getAccountType());
        account1.setAccountNumber(account.getAccountNumber());
        Long bankId=account.getBank().getId();
        Bank bank=bankService.getById(bankId);
        Long userId=account.getUser().getId();
        User user=userService.getById(userId);
        account1.setUser(user);
        account1.setBank(bank);
        return accountRepository.save(account1);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        accountRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<Account> paging(Integer page, Integer size) {
        return accountRepository.findAll(PageRequest.of(page,size, Sort.by("id").ascending()));
    }

    @Transactional
    @Override
    public Account getById(Long id) {
        Optional<Account>account=accountRepository.findById(id);
        if(!account.isPresent()){
            throw new NotFound("not found id");
        }
        return account.get();
    }

    @Transactional
    @Override
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll();
    }

    @Transactional
    @Override
    public List<Account> getByBank(Long id) {
        Bank bank=bankService.getById(id);
         List<Account>accounts=accountRepository.findAllByBank(bank);
        return accounts;
    }

    @Transactional
    @Override
    public List<Account> getByBankCode(Long code) {
        return accountRepository.findByBankCode(code);
    }
    @Transactional
    @Override
    public List<Account> getByUserNationalCode(String s) {
        return accountRepository.findByUserNationalCode(s);
    }

    @Override
    public Account getByAccountNumber(String s) {
        return accountRepository.findByAccountNumber(s);
    }
}
