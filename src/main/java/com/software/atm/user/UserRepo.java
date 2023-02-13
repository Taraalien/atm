package com.software.atm.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User,Long> {

    List<User> findAllByIsMarried(Boolean isMarried);

    Page<User> findAll(Pageable pageable);
    List<User> findAll();
}
