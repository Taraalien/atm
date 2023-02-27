package com.software.atm.account;


import com.software.atm.bank.Bank;
import com.software.atm.branch.Branch;
import com.software.atm.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {

    Page<Account> findAll(Pageable pageable);

    List<Account> findByBranch_Code(String s);

    List<Account>findByUserNationalCode(String s);
    Account findByAccountNumber(String s);

}
