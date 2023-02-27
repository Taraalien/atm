package com.software.atm.bank;

import com.software.atm.common.exceptions.NotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class BankImple implements BankService {

    private final BankRepository bankRepository;

    @Transactional
    @Override
    public Bank save(Bank bank) {
        log.info("save  bank");
        return bankRepository.save(bank);
    }

    @Transactional
    @Override
    public Bank update(Bank bank) {
        Bank bank1=getById(bank.getId());
        bank1.setName(bank.getName());
        bank1.setPhone(bank.getPhone());
        bank1.setAddress(bank.getAddress());
        log.info("update  bank");
        return bankRepository.save(bank1);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        log.info("delete  bank");
        bankRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<Bank> paging(Integer page, Integer size) {
        log.info("get all  banks");
        return bankRepository.findAll(PageRequest.of(page,size, Sort.by("id").ascending()));
    }

    @Transactional
    @Override
    public Bank getById(Long id) {
        log.info("get by bank id");
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
