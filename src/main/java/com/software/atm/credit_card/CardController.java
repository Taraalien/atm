package com.software.atm.credit_card;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.atm.common.PagingData;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.util.List;

@RequestMapping("/v1/card")
@RestController
@AllArgsConstructor
public class CardController {

    private final CardMapper cardMapper;

    private final CardService cardService;



    @PostMapping
    @Operation(summary = "insert card")
    public ResponseEntity save(@RequestBody CardDto dto) {
        Card card = cardMapper.toEntity(dto);
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Operation(summary = "update card")
    public ResponseEntity update(@RequestBody CardDto dto) {
        Card card = cardMapper.toEntity(dto);
        cardService.update(card);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @Operation(summary = "getAll")
    public ResponseEntity<List<CardDto>> getAll() {
        List<Card> card = cardService.getAll();
        List<CardDto> cardDtos = cardMapper.toDto(card);
        return ResponseEntity.ok(cardDtos);

    }

    @GetMapping("get-by-id/{id}")
    @Operation(summary = "get by id ")
    public ResponseEntity<CardDto> getById(@PathVariable Long id) {
        Card card = cardService.getById(id);
        CardDto cardDtos = cardMapper.toDto(card);
        return ResponseEntity.ok(cardDtos);

    }

    @DeleteMapping("delete-by-id/{id}")
    @Operation(summary = "delete by id")
    public ResponseEntity deleteById(@PathVariable Long id) {

        cardService.delete(id);
        return ResponseEntity.ok().build();

    }


    @GetMapping("/paging/{page}/{size}")
    public ResponseEntity<PagingData<CardDto>> findAll(@PathVariable Integer page, Integer size) {

        Page<Card> cards = cardService.paging(page, size);
        int totalPage = cards.getTotalPages();
        List<Card> data = cards.getContent();
        List<CardDto> cardDtos = cardMapper.toDto(data);
        PagingData<CardDto> pagingData = new PagingData<>(totalPage, page, cardDtos);
        return ResponseEntity.ok(pagingData);
    }


    @GetMapping("get-by-user/{userId}")
    @Operation(summary = "get by user")
    public ResponseEntity<List<CardDto>> getByUser(@PathVariable Long userId,HttpServletResponse response) {
          List<Card> card = cardService.getByUser(userId);
        List<CardDto> cardDtos = cardMapper.toDto(card);

        return ResponseEntity.ok(cardDtos);

    }


    @GetMapping("get-by-user-national-code/{code}")
    @Operation(summary = "get by user national code")
    public ResponseEntity<List<CardDto>> getByUserNationalCode(@PathVariable String code) {
        List<Card> card = cardService.getByUserNationalCode(code);
        List<CardDto> cardDtos = cardMapper.toDto(card);
        return ResponseEntity.ok(cardDtos);

    }


    @GetMapping("get-by-user-national-code-and-accountNumber/{code}/{accountNumber}")
    @Operation(summary = "get by user national code and account number ")
    public ResponseEntity<CardDto> getByUserNationalCodeAndAccountNumber(@PathVariable String code, @PathVariable String accountNumber) {
        Card card = cardService.getByUserNationalCodeAndAccountNumber(code, accountNumber);
        CardDto cardDtos = cardMapper.toDto(card);
        return ResponseEntity.ok(cardDtos);

    }


    @GetMapping("get-active-card/{status}")
    @Operation(summary = "get active cards")
    public ResponseEntity<List<CardDto>> getActiveStatus(@PathVariable Status status) {
        List<Card> card = cardService.getByStatus(status);
        List<CardDto> cardDtos = cardMapper.toDto(card);
        return ResponseEntity.ok(cardDtos);

    }

    @GetMapping("get-amount/{cardNumber}")
    @Operation(summary = "get amount ")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "not found"),
            @ApiResponse(code = 200, message = "successfully get balance")
    })
    public ResponseEntity<BigDecimal> getAmount(@PathVariable String cardNumber) {
        BigDecimal balance = cardService.getAmount(cardNumber);
        return ResponseEntity.ok(balance);

    }

    @PutMapping("change-password/{cardNumber}/{pin}")
    @Operation(summary = "update pin")
    public ResponseEntity<CardDto> updatePin(@PathVariable String cardNumber, String pin) {
        Card card = cardService.changePassword(cardNumber, pin);
        CardDto cardDto = cardMapper.toDto(card);
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping(value = "withdrawal/{cardNumber}/{amount}")
    @Operation(summary = "withdrawal")
    public ResponseEntity<BigDecimal> withdrawal(@PathVariable String cardNumber, @PathVariable BigDecimal amount) {
        BigDecimal balance = cardService.withdrawal(cardNumber, amount);
        return ResponseEntity.ok(balance);
    }

    @GetMapping(value = "deposit/{cardNumber}/{amount}")
    @Operation(summary = "deposit")
    public ResponseEntity<BigDecimal> deposit(@PathVariable String cardNumber, @PathVariable BigDecimal amount) {
        BigDecimal balance = cardService.deposit(cardNumber, amount);
        return ResponseEntity.ok(balance);
    }



    }








