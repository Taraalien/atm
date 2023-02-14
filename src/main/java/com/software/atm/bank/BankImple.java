package com.software.atm.bank;

import com.software.atm.common.exceptions.NotFound;
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
public class BankImple implements BankService {

    private final BankRepository bankRepository;

    @Transactional
    @Override
    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    @Transactional
    @Override
    public Bank update(Bank bank) {
        Bank bank1=getById(bank.getId());
        bank1.setName(bank.getName());
        bank1.setCode(bank.getCode());
        bank1.setPhone(bank.getPhone());
        bank1.setAddress(bank.getAddress());
        return bankRepository.save(bank1);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        bankRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<Bank> paging(Integer page, Integer size) {
        return bankRepository.findAll(PageRequest.of(page,size, Sort.by("id").ascending()));
    }

    @Transactional
    @Override
    public Bank getById(Long id) {
        Optional<Bank>optionalBank=bankRepository.findById(id);
        if(!optionalBank.isPresent()){
            throw new NotFound("not found id");
        }
        return optionalBank.get();
    }

    @Transactional
    @Override
    public List<Bank> getAll() {
        return (List<Bank>) bankRepository.findAll();
    }
}
