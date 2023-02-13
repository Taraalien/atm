package com.software.atm.user;



import com.software.atm.common.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto,User> {


    User toEntity(UserDto userDto);

    UserDto toDto(User user);


    default User formId(Long id)
    {
        if(id==null){
            return null;
        }
        User user=new User();
        user.setId(id);
        return user;
    }
}
