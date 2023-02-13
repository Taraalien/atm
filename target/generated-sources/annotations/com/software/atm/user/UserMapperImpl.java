package com.software.atm.user;

import com.software.atm.user.User.UserBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-13T07:44:03+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<User> toEntity(List<UserDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dto.size() );
        for ( UserDto userDto : dto ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDto(List<User> toEntity) {
        if ( toEntity == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( toEntity.size() );
        for ( User user : toEntity ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.name( userDto.getName() );
        user.lastName( userDto.getLastName() );
        user.birthDay( userDto.getBirthDay() );
        user.phone( userDto.getPhone() );
        user.nationalCode( userDto.getNationalCode() );
        user.job( userDto.getJob() );
        user.isMarried( userDto.getIsMarried() );
        user.address( userDto.getAddress() );

        return user.build();
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setVersion( user.getVersion() );
        userDto.setCreatedBy( user.getCreatedBy() );
        userDto.setCreatedDate( user.getCreatedDate() );
        userDto.setLastModifiedBy( user.getLastModifiedBy() );
        userDto.setLastModifiedDate( user.getLastModifiedDate() );
        userDto.setName( user.getName() );
        userDto.setLastName( user.getLastName() );
        userDto.setBirthDay( user.getBirthDay() );
        userDto.setPhone( user.getPhone() );
        userDto.setNationalCode( user.getNationalCode() );
        userDto.setJob( user.getJob() );
        userDto.setIsMarried( user.getIsMarried() );
        userDto.setAddress( user.getAddress() );

        return userDto;
    }
}
