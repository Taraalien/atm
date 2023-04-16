package com.software.atm.branch;


import org.springframework.data.domain.Page;

import java.util.List;

public interface BranchService{

    Branch save(Branch branch);

    Branch update(Branch branch);

    void delete(Long id);

    Page<Branch> paging(Integer page, Integer size);

     Branch getById(Long id);

    List<Branch> getAll();

    List<Branch>getByBankId(Long id);


}
