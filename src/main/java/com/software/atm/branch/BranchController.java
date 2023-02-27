package com.software.atm.branch;


import com.software.atm.bank.Bank;
import com.software.atm.bank.BankDto;
import com.software.atm.bank.BankMapper;
import com.software.atm.bank.BankService;
import com.software.atm.common.PagingData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(name = "v1/branch")
public class BranchController {

    private final BranchMapper branchMapper;
    private final BranchService branchService;



    @PostMapping
    @Operation(summary = "insert branch")
    public ResponseEntity save(@RequestBody BranchDto dto){
        Branch branch=branchMapper.toEntity(dto);
        branchService.save(branch);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Operation(summary = "update branch")
    public ResponseEntity update(@RequestBody BranchDto dto){
        Branch branch=branchMapper.toEntity(dto);
        branchService.save(branch);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @Operation(summary = "getAll")
    public ResponseEntity<List<BranchDto>>getAll()
    {
        List<Branch> banks=branchService.getAll();
        List<BranchDto>branchDtos=branchMapper.toDto(banks);
        return ResponseEntity.ok(branchDtos);
    }

    @GetMapping("get-by-id/{id}")
    @Operation(summary = "get by id ")
    public ResponseEntity<BranchDto>getById(@PathVariable Long id)
    {
        Branch  branch=branchService.getById(id);
        BranchDto branchDto=branchMapper.toDto(branch);
        return ResponseEntity.ok(branchDto);
    }

    @DeleteMapping("delete-by-id/{id}")
    @Operation(summary = "delete by id")
    public ResponseEntity deleteById(@PathVariable Long id){

        branchService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/paging/{page}/{size}")
    public ResponseEntity<PagingData<BranchDto>> findAll(@PathVariable Integer page, Integer size){

        Page<Branch> branches=branchService.paging(page,size);
        int totalPage=branches.getTotalPages();
        List<Branch> data=branches.getContent();
        List<BranchDto> branchDtos=branchMapper.toDto(data);
        PagingData<BranchDto> pagingData=new PagingData<>(totalPage,page,branchDtos);
        return ResponseEntity.ok(pagingData);
    }


    @GetMapping("get-by-bank-id/{id}")
    @Operation(summary = "get by bank Id")
    public ResponseEntity<List<BranchDto>> getByBankId(@PathVariable Long id) {
        List<Branch> banks=branchService.getByBankId(id);
        List<BranchDto>branchDtos=branchMapper.toDto(banks);
        return ResponseEntity.ok(branchDtos);
    }
}
