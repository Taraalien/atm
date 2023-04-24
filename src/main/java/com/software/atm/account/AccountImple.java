package com.software.atm.account;

import com.software.atm.branch.Branch;
import com.software.atm.branch.BranchService;
import com.software.atm.common.exceptions.BadRequest;
import com.software.atm.common.exceptions.ConflictException;
import com.software.atm.common.exceptions.NotFound;
import com.software.atm.user.User;
import com.software.atm.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class AccountImple implements AccountService{

    private final AccountRepository accountRepository;
    private final UserService userService;

    private final BranchService branchService;


    @Transactional
    @Override
    public Account save(Account account) {

        var account1=(List<Account>)accountRepository.findAll();

        for(Account account2:account1){

            if(account2.getAccountNumber().equals(account.getAccountNumber()))
            {
                log.error("duplicated account number");
                throw new ConflictException("duplicated account number");
            }
        }

        if(!(account.getAccountNumber().length() >= 10 && account.getAccountNumber().length() <= 15)){

            log.error("account format is wrong");
            throw new ConflictException("account number format not correct.");

        }

        Long userId=account.getUser().getId();
        User user=userService.getById(userId);
        Long branchId=account.getBranch().getId();
        Branch branch=branchService.getById(branchId);
        account.setUser(user);
        account.setBranch(branch);
        log.info("save new account");
        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public Account update(Account account) {
        Account account1=getById(account.getId());
        account1.setBalance(account.getBalance());
        account1.setAccountType(account.getAccountType());
        account1.setAccountNumber(account.getAccountNumber());
        Long userId=account.getUser().getId();
        User user=userService.getById(userId);
        Long branchId=account.getBranch().getId();
        Branch branch=branchService.getById(branchId);
        account1.setUser(user);
        account1.setBranch(branch);
        log.info("update account");
        return accountRepository.save(account1);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        log.info("delete account");
        if(accountRepository.findById(id).equals(true)) {
            accountRepository.deleteById(id);
        }
        throw new NotFound("not found id");
    }

    @Transactional
    @Override
    public Page<Account> paging(Integer page, Integer size) {
        log.info("find all  accounts");
        return accountRepository.findAll(PageRequest.of(page,size, Sort.by("name").ascending()));
    }

    @Transactional(readOnly = true)
    @Override
    public Account getById(Long id) {
        log.info("get account by id");
        Optional<Account>account=accountRepository.findById(id);
        if(!account.isPresent()){
            throw new NotFound("not found id");
        }
        return account.get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public List<Account> getByUserNationalCode(String s) {
        log.info("get account by user national code");
        return accountRepository.findByUserNationalCode(s);
    }

    @Transactional(readOnly = true)
    @Override
    public Account getByAccountNumber(String s) {
        log.info("get account by account number ");
        return accountRepository.findByAccountNumber(s);
    }

    @Transactional()
    @Override
    public BigDecimal withdrawal(Long accountId, BigDecimal balance) {
        log.info("withdrawal money");
        Account account=getById(accountId);
        if(account.getBalance().compareTo(balance)==-1){

            throw new BadRequest("amount you want is more than your balance");
        }
        account.setBalance(account.getBalance().subtract(balance));

        Account newBalance=accountRepository.save(account);
        return newBalance.getBalance();
    }

    @Transactional()
    @Override
    public BigDecimal deposit(Long accountId, BigDecimal balance) {

        log.info("deposit money");
        Account account=getById(accountId);

        account.setBalance(account.getBalance().add(balance));

        Account newBalance=accountRepository.save(account);
        return newBalance.getBalance();
    }

    @Override
    public List<Account> getByBranchCode(String s) {
        return accountRepository.findByBranch_Code(s);
    }

}
