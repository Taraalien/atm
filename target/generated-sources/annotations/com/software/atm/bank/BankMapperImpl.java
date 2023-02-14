package com.software.atm.bank;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-14T11:36:39+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class BankMapperImpl implements BankMapper {

    @Override
    public List<Bank> toEntity(List<BankDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Bank> list = new ArrayList<Bank>( dto.size() );
        for ( BankDto bankDto : dto ) {
            list.add( toEntity( bankDto ) );
        }

        return list;
    }

    @Override
    public List<BankDto> toDto(List<Bank> toEntity) {
        if ( toEntity == null ) {
            return null;
        }

        List<BankDto> list = new ArrayList<BankDto>( toEntity.size() );
        for ( Bank bank : toEntity ) {
            list.add( toDto( bank ) );
        }

        return list;
    }

    @Override
    public Bank toEntity(BankDto dto) {
        if ( dto == null ) {
            return null;
        }

        Bank bank = new Bank();

        bank.setId( dto.getId() );
        bank.setVersion( dto.getVersion() );
        bank.setCreatedBy( dto.getCreatedBy() );
        bank.setCreatedDate( dto.getCreatedDate() );
        bank.setLastModifiedBy( dto.getLastModifiedBy() );
        bank.setLastModifiedDate( dto.getLastModifiedDate() );
        bank.setName( dto.getName() );
        bank.setCode( dto.getCode() );
        bank.setPhone( dto.getPhone() );
        bank.setAddress( dto.getAddress() );

        return bank;
    }

    @Override
    public BankDto toDto(Bank bank) {
        if ( bank == null ) {
            return null;
        }

        BankDto bankDto = new BankDto();

        bankDto.setId( bank.getId() );
        bankDto.setVersion( bank.getVersion() );
        bankDto.setCreatedBy( bank.getCreatedBy() );
        bankDto.setCreatedDate( bank.getCreatedDate() );
        bankDto.setLastModifiedBy( bank.getLastModifiedBy() );
        bankDto.setLastModifiedDate( bank.getLastModifiedDate() );
        bankDto.setName( bank.getName() );
        bankDto.setCode( bank.getCode() );
        bankDto.setPhone( bank.getPhone() );
        bankDto.setAddress( bank.getAddress() );

        return bankDto;
    }
}
