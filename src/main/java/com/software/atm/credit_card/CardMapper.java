package com.software.atm.credit_card;


import com.software.atm.account.AccountMapper;
import com.software.atm.common.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper extends EntityMapper<CardDto,Card> {



    Card toEntity (CardDto dto);


    CardDto toDto(Card card);


    default Card formId(Long id){

        if(id==null){
            return null;
        }
        Card card=new Card();
        card.setId(id);
        return card;
    }
}
