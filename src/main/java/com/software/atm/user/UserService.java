package com.software.atm.user;

import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {


    User save(User user);

    User update(User user);

    void delete(Long id);

    Page<User> paging(Integer page,Integer size);

    User getById(Long id);

    List<User> isMarried(Boolean aBoolean);

    List<User> getAll();




}
