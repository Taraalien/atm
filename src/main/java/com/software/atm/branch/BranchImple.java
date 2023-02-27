package com.software.atm.branch;


import com.software.atm.bank.Bank;
import com.software.atm.bank.BankService;
import com.software.atm.common.exceptions.NotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BranchImple implements BranchService {

    private final BankService bankService;

    private final BranchRepository branchRepository;

    @Override
    public Branch save(Branch branch) {
        Long branchId=branch.getBank().getId();
        Bank  bank=bankService.getById(branchId);
        branch.setBank(bank);
        return branchRepository.save(branch);
    }

    @Override
    public Branch update(Branch branch) {
        Branch branch1=getById(branch.getId());
        branch1.setName(branch.getName());
        branch1.setCode(branch.getCode());
        branch1.setAddress(branch.getAddress());
        Long branchId=branch.getBank().getId();
        Bank  bank=bankService.getById(branchId);
        branch1.setBank(bank);
        return branchRepository.save(branch1);
    }

    @Override
    public void delete(Long id) {

        branchRepository.deleteById(id);
    }

    @Override
    public Page<Branch> paging(Integer page, Integer size) {
        return branchRepository.findAllBy(PageRequest.of(page,size, Sort.by("id").ascending()));
    }

    @Override
    public Branch getById(Long id) {
        Optional<Branch> branch=branchRepository.findById(id);
        if(!branch.isPresent()){
            throw new NotFound("not found id");
        }
        return branch.get();
    }

    @Override
    public List<Branch> getAll() {
        return (List<Branch>) branchRepository.findAll();
    }

    @Override
    public List<Branch> getByBankId(Long id) {
        Bank bank=bankService.getById(id);
        List<Branch>branches=branchRepository.findByBank(bank);
        return branches;
    }
}
