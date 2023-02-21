package com.software.atm.credit_card;


import com.software.atm.account.AccountMapper;
import com.software.atm.common.EntityMapper;
import com.software.atm.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UserMapper.class, AccountMapper.class})
public interface CardMapper extends EntityMapper<CardDto,Card> {




    @Mapping(source = "userId",target = "user.id")
    @Mapping(source = "accountId",target = "account.id")
    Card toEntity (CardDto dto);


    @Mapping(source = "user.id",target = "userId")
    @Mapping(source = "account.id",target = "accountId")
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
