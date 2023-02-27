package com.software.atm.branch;


import com.software.atm.bank.BankMapper;
import com.software.atm.common.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {BankMapper.class})
public interface BranchMapper  extends EntityMapper<BranchDto,Branch> {




    @Mapping(source = "bankId",target = "bank.id")
    Branch toEntity(BranchDto dto);

    @Mapping(source = "bank.id",target = "bankId")
    BranchDto toDto(Branch branch);


    default Branch formId(Long id)
    {
        if(id==null){return null;}

        Branch branch=new Branch();
        branch.setId(id);
        return branch;

    }

}
