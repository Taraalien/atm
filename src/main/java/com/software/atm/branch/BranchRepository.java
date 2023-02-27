package com.software.atm.branch;


import com.software.atm.bank.Bank;
import org.dom4j.tree.BackedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends PagingAndSortingRepository<Branch,Long> {

    Page<Branch>findAllBy(Pageable pageable);

    List<Branch>findByBank(Bank bank);

}
