package com.software.atm.user;


import com.software.atm.common.exceptions.ConflictException;
import com.software.atm.common.exceptions.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {

    private final UserRepo repo;



    @Transactional
    @Override
    public User save(User user) {

        if(!user.getNationalCode().matches("^[0-9]{10}$")){

            throw new ConflictException("national code is not valid");
        }

        if (!user.getPhone().matches("^[0-9]{11}$")){

            throw new ConflictException("phone number is not valid");
        }
        return repo.save(user);
    }


    @Transactional
    @Override
    public User update(User user) {
        User user1=getById(user.getId());

        if(!user1.getNationalCode().matches("^[0-9]{10}$")){

            throw new ConflictException("national code is not valid");
        }

        if (!user1.getPhone().matches("^[0-9]{11}$")){

            throw new ConflictException("phone number is not valid");
        }
        user1.setName(user.getName());
        user1.setLastName(user.getLastName());
        user1.setNationalCode(user.getNationalCode());
        user1.setPhone(user.getPhone());
        user1.setBirthDay(user.getBirthDay());
        user1.setJob(user.getJob());
        user1.setIsMarried(user.getIsMarried());
        user1.setAddress(user.getAddress());
        return repo.save(user1);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        repo.deleteById(id);

    }
    @Transactional
    @Override
    public List<User> getAll() {
        return  repo.findAll();
    }

    @Transactional
    @Override
    public Page<User> paging(Integer page, Integer size) {
        return  repo.findAll(PageRequest.of(page,size,Sort.by("id").ascending()));
    }

    @Transactional
    @Override
    public User getById(Long id) {
        Optional<User> user=repo.findById(id);
        if(!user.isPresent()){
            throw new NotFound("Not found id");
        }
        return user.get();
    }

    @Transactional
    @Override
    public List<User> isMarried(Boolean aBoolean) {
        return repo.findAllByIsMarried(aBoolean.equals(true));
    }





}
