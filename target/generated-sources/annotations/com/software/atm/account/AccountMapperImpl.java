package com.software.atm.account;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-13T16:11:31+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public List<Account> toEntity(List<AccountDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( dto.size() );
        for ( AccountDto accountDto : dto ) {
            list.add( toEntity( accountDto ) );
        }

        return list;
    }

    @Override
    public List<AccountDto> toDto(List<Account> toEntity) {
        if ( toEntity == null ) {
            return null;
        }

        List<AccountDto> list = new ArrayList<AccountDto>( toEntity.size() );
        for ( Account account : toEntity ) {
            list.add( toDto( account ) );
        }

        return list;
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( accountDto.getId() );
        account.setVersion( accountDto.getVersion() );
        account.setCreatedBy( accountDto.getCreatedBy() );
        account.setCreatedDate( accountDto.getCreatedDate() );
        account.setLastModifiedBy( accountDto.getLastModifiedBy() );
        account.setLastModifiedDate( accountDto.getLastModifiedDate() );
        account.setBalance( accountDto.getBalance() );
        account.setAccountType( accountDto.getAccountType() );

        return account;
    }

    @Override
    public AccountDto toDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId( account.getId() );
        accountDto.setVersion( account.getVersion() );
        accountDto.setCreatedBy( account.getCreatedBy() );
        accountDto.setCreatedDate( account.getCreatedDate() );
        accountDto.setLastModifiedBy( account.getLastModifiedBy() );
        accountDto.setLastModifiedDate( account.getLastModifiedDate() );
        accountDto.setBalance( account.getBalance() );
        accountDto.setAccountType( account.getAccountType() );

        return accountDto;
    }
}
