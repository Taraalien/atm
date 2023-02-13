package com.software.atm.account;


import com.software.atm.common.PagingData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/account")
@AllArgsConstructor
public class AccountController {


    private final AccountMapper accountMapper;
    private final AccountService accountService;



    @PostMapping
    @Operation(summary = "insert account")
    public ResponseEntity save(@RequestBody AccountDto dto){
        Account account=accountMapper.toEntity(dto);
        accountService.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Operation(summary = "update account")
    public ResponseEntity update(@RequestBody AccountDto dto){
        Account account=accountMapper.toEntity(dto);
        accountService.save(account);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @Operation(summary = "getAll")
    public ResponseEntity<List<AccountDto>>getAll()
    {
        List<Account> accounts=accountService.getAll();
        List<AccountDto>accountDtos=accountMapper.toDto(accounts);
        return ResponseEntity.ok(accountDtos);
    }

    @GetMapping("get-by-id/{id}")
    @Operation(summary = "get by id ")
    public ResponseEntity<AccountDto>getById(@PathVariable Long id)
    {
        Account  account=accountService.getById(id);
        AccountDto accountDto=accountMapper.toDto(account);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("delete-by-id/{id}")
    @Operation(summary = "delete by id")
    public ResponseEntity deleteById(@PathVariable Long id){

        accountService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/paging/{page}/{size}")
    public ResponseEntity<PagingData<AccountDto>> findAll(@PathVariable Integer page, Integer size){

        Page<Account> accounts=accountService.paging(page,size);
        int totalPage=accounts.getTotalPages();
        List<Account> data=accounts.getContent();
        List<AccountDto> accountDtos=accountMapper.toDto(data);
        PagingData<AccountDto> pagingData=new PagingData<>(totalPage,page,accountDtos);
        return ResponseEntity.ok(pagingData);
    }
}
