package com.software.atm.account;


import com.software.atm.bank.BankMapper;
import com.software.atm.common.EntityMapper;
import com.software.atm.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {BankMapper.class, UserMapper.class})
public interface AccountMapper extends EntityMapper<AccountDto,Account> {

    @Mapping(source = "bankId",target = "bank.id")
    @Mapping(source = "userId",target = "user.id")
    Account toEntity(AccountDto accountDto);

    @Mapping(source = "bank.id",target = "bankId")
    @Mapping(source = "user.id",target = "userId")
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
