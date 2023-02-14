package com.software.atm.bank;


import com.software.atm.common.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper extends EntityMapper<BankDto,Bank> {

    Bank toEntity(BankDto dto);

    BankDto toDto(Bank bank);


    default Bank formId(Long id)
    {
        if(id==null){
            return null;
        }

        Bank bank=new Bank();
        bank.setId(id);
        return bank;

    }
}
