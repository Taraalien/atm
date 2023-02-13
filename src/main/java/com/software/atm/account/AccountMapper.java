package com.software.atm.account;


import com.software.atm.common.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends EntityMapper<AccountDto,Account> {

    Account toEntity(AccountDto accountDto);

    AccountDto toDto(Account account);


    default Account formId(Long id)
    {
        if(id==null)
        {
            return null;
        }
        Account account=new Account();
        account.setId(id);
        return account;
    }
}
