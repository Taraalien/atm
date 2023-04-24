package com.software.atm.branch;

import com.software.atm.bank.Bank;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T11:43:00+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class BranchMapperImpl implements BranchMapper {

    @Override
    public List<Branch> toEntity(List<BranchDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Branch> list = new ArrayList<Branch>( dto.size() );
        for ( BranchDto branchDto : dto ) {
            list.add( toEntity( branchDto ) );
        }

        return list;
    }

    @Override
    public List<BranchDto> toDto(List<Branch> toEntity) {
        if ( toEntity == null ) {
            return null;
        }

        List<BranchDto> list = new ArrayList<BranchDto>( toEntity.size() );
        for ( Branch branch : toEntity ) {
            list.add( toDto( branch ) );
        }

        return list;
    }

    @Override
    public Branch toEntity(BranchDto dto) {
        if ( dto == null ) {
            return null;
        }

        Branch branch = new Branch();

        branch.setBank( branchDtoToBank( dto ) );
        branch.setId( dto.getId() );
        branch.setVersion( dto.getVersion() );
        branch.setCreatedBy( dto.getCreatedBy() );
        branch.setCreatedDate( dto.getCreatedDate() );
        branch.setLastModifiedBy( dto.getLastModifiedBy() );
        branch.setLastModifiedDate( dto.getLastModifiedDate() );
        branch.setCode( dto.getCode() );
        branch.setName( dto.getName() );
        branch.setAddress( dto.getAddress() );
        branch.setPhone( dto.getPhone() );

        return branch;
    }

    @Override
    public BranchDto toDto(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchDto branchDto = new BranchDto();

        branchDto.setBankId( branchBankId( branch ) );
        branchDto.setId( branch.getId() );
        branchDto.setVersion( branch.getVersion() );
        branchDto.setCreatedBy( branch.getCreatedBy() );
        branchDto.setCreatedDate( branch.getCreatedDate() );
        branchDto.setLastModifiedBy( branch.getLastModifiedBy() );
        branchDto.setLastModifiedDate( branch.getLastModifiedDate() );
        branchDto.setCode( branch.getCode() );
        branchDto.setName( branch.getName() );
        branchDto.setAddress( branch.getAddress() );
        branchDto.setPhone( branch.getPhone() );

        return branchDto;
    }

    protected Bank branchDtoToBank(BranchDto branchDto) {
        if ( branchDto == null ) {
            return null;
        }

        Bank bank = new Bank();

        bank.setId( branchDto.getBankId() );

        return bank;
    }

    private Long branchBankId(Branch branch) {
        if ( branch == null ) {
            return null;
        }
        Bank bank = branch.getBank();
        if ( bank == null ) {
            return null;
        }
        Long id = bank.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
