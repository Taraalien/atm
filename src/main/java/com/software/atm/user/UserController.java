package com.software.atm.user;


import com.software.atm.common.PagingData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/v1")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;


    @PostMapping
    @Operation(summary = "create user")
    public ResponseEntity save(@RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Operation(summary = "update user")
    public ResponseEntity update(@RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        userService.update(user);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("get-id/{id}")
    @Operation(summary = "delte user")
    public ResponseEntity delete(@PathVariable Long id)
    {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("get-id/{id}")
    @Operation(summary = "get  user")
    public ResponseEntity<UserDto>getById(@PathVariable Long id)
    {
        User user=userService.getById(id);
        UserDto dto=userMapper.toDto(user);
        return ResponseEntity.ok(dto);
    }


    @GetMapping("get-all/")
    @Operation(summary = "get all user")
    public ResponseEntity<List<UserDto>>getAll()
    {
        List<User> user=userService.getAll();
        List<UserDto> dto=userMapper.toDto(user);
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/is-married/")
    @Operation(summary = "get married user")
    public ResponseEntity<List<UserDto>>IsMarried()
    {
        List<User> user=userService.isMarried(true);
        List<UserDto> userDto=userMapper.toDto(user);
        return ResponseEntity.ok(userDto);

    }


    @GetMapping("/paging/{page}/{size}")
    public ResponseEntity<PagingData<UserDto>> findAll(@PathVariable Integer page, Integer size){

        Page<User> users=userService.paging(page,size);
        int totalPage=users.getTotalPages();
        List<User> data=users.getContent();
        List<UserDto> userDtos=userMapper.toDto(data);
        PagingData<UserDto> pagingData=new PagingData<>(totalPage,page,userDtos);
        return ResponseEntity.ok(pagingData);
    }



}
