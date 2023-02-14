package com.software.atm.credit_card;


import com.software.atm.common.EntityMapper;
import com.software.atm.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface CardMapper extends EntityMapper<CardDto,Card> {



    @Mapping(source = "userId",target = "user.id")
    Card toEntity (CardDto dto);


    @Mapping(source = "user.id",target = "userId")
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
