package com.software.atm.bank;


import com.software.atm.common.PagingData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Bank")
@AllArgsConstructor
public class BankController {

    private final BankMapper bankMapper;
    private final BankService bankService;



    @PostMapping
    @Operation(summary = "insert bank")
    public ResponseEntity save(@RequestBody BankDto dto){
        Bank bank=bankMapper.toEntity(dto);
        bankService.save(bank);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Operation(summary = "update bank")
    public ResponseEntity update(@RequestBody BankDto dto){
        Bank bank=bankMapper.toEntity(dto);
        bankService.save(bank);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @Operation(summary = "getAll")
    public ResponseEntity<List<BankDto>>getAll()
    {
        List<Bank> banks=bankService.getAll();
        List<BankDto>bankDtos=bankMapper.toDto(banks);
        return ResponseEntity.ok(bankDtos);
    }

    @GetMapping("get-by-id/{id}")
    @Operation(summary = "get by id ")
    public ResponseEntity<BankDto>getById(@PathVariable Long id)
    {
        Bank  bank=bankService.getById(id);
        BankDto bankDto=bankMapper.toDto(bank);
        return ResponseEntity.ok(bankDto);
    }

    @DeleteMapping("delete-by-id/{id}")
    @Operation(summary = "delete by id")
    public ResponseEntity deleteById(@PathVariable Long id){

        bankService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/paging/{page}/{size}")
    public ResponseEntity<PagingData<BankDto>> findAll(@PathVariable Integer page, Integer size){

        Page<Bank> banks=bankService.paging(page,size);
        int totalPage=banks.getTotalPages();
        List<Bank> data=banks.getContent();
        List<BankDto> bankDtos=bankMapper.toDto(data);
        PagingData<BankDto> pagingData=new PagingData<>(totalPage,page,bankDtos);
        return ResponseEntity.ok(pagingData);
    }
}
