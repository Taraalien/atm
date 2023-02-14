package com.software.atm.credit_card;

import com.software.atm.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-14T16:12:36+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public List<Card> toEntity(List<CardDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Card> list = new ArrayList<Card>( dto.size() );
        for ( CardDto cardDto : dto ) {
            list.add( toEntity( cardDto ) );
        }

        return list;
    }

    @Override
    public List<CardDto> toDto(List<Card> toEntity) {
        if ( toEntity == null ) {
            return null;
        }

        List<CardDto> list = new ArrayList<CardDto>( toEntity.size() );
        for ( Card card : toEntity ) {
            list.add( toDto( card ) );
        }

        return list;
    }

    @Override
    public Card toEntity(CardDto dto) {
        if ( dto == null ) {
            return null;
        }

        Card card = new Card();

        card.setUser( cardDtoToUser( dto ) );
        card.setId( dto.getId() );
        card.setVersion( dto.getVersion() );
        card.setCreatedBy( dto.getCreatedBy() );
        card.setCreatedDate( dto.getCreatedDate() );
        card.setLastModifiedBy( dto.getLastModifiedBy() );
        card.setLastModifiedDate( dto.getLastModifiedDate() );
        card.setCardNumber( dto.getCardNumber() );
        card.setPin( dto.getPin() );
        card.setStatus( dto.getStatus() );
        card.setExpireDate( dto.getExpireDate() );

        return card;
    }

    @Override
    public CardDto toDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        cardDto.setUserId( cardUserId( card ) );
        cardDto.setId( card.getId() );
        cardDto.setVersion( card.getVersion() );
        cardDto.setCreatedBy( card.getCreatedBy() );
        cardDto.setCreatedDate( card.getCreatedDate() );
        cardDto.setLastModifiedBy( card.getLastModifiedBy() );
        cardDto.setLastModifiedDate( card.getLastModifiedDate() );
        cardDto.setCardNumber( card.getCardNumber() );
        cardDto.setPin( card.getPin() );
        cardDto.setStatus( card.getStatus() );
        cardDto.setExpireDate( card.getExpireDate() );

        return cardDto;
    }

    protected User cardDtoToUser(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( cardDto.getUserId() );

        return user;
    }

    private Long cardUserId(Card card) {
        if ( card == null ) {
            return null;
        }
        User user = card.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
