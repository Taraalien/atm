package com.software.atm.credit_card;


import com.software.atm.common.PagingData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/card")
@RestController
@AllArgsConstructor
public class CardController {

    private final CardMapper cardMapper;

    private final CardService cardService;


    @PostMapping
    @Operation(summary = "insert card")
    public ResponseEntity save(@RequestBody CardDto dto){
        Card card=cardMapper.toEntity(dto);
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Operation(summary = "update card")
    public ResponseEntity update(@RequestBody CardDto dto){
        Card card=cardMapper.toEntity(dto);
        cardService.update(card);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @Operation(summary = "getAll")
    public ResponseEntity<List<CardDto>>getAll()
    {
        List<Card> card=cardService.getAll();
        List<CardDto>cardDtos=cardMapper.toDto(card);
        return ResponseEntity.ok(cardDtos);

    }

    @GetMapping("get-by-id/{id}")
    @Operation(summary = "get by id ")
    public ResponseEntity<CardDto>getById(@PathVariable Long id)
    {
        Card card=cardService.getById(id);
        CardDto cardDtos=cardMapper.toDto(card);
        return ResponseEntity.ok(cardDtos);

    }

    @DeleteMapping("delete-by-id/{id}")
    @Operation(summary = "delete by id")
    public ResponseEntity deleteById(@PathVariable Long id){

       cardService.delete(id);
       return ResponseEntity.ok().build();

    }


    @GetMapping("/paging/{page}/{size}")
    public ResponseEntity<PagingData<CardDto>> findAll(@PathVariable Integer page, Integer size){

        Page<Card> cards=cardService.paging(page,size);
        int totalPage=cards.getTotalPages();
        List<Card> data=cards.getContent();
        List<CardDto> cardDtos=cardMapper.toDto(data);
        PagingData<CardDto> pagingData=new PagingData<>(totalPage,page,cardDtos);
        return ResponseEntity.ok(pagingData);
    }
}
