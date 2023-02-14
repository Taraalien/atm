package com.software.atm.bank;

import com.software.atm.account.Account;
import com.software.atm.account.AccountDto;
import com.software.atm.common.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Table(name = "tbl_bank")
@Data
@Entity
@Audited
public class Bank extends BaseEntity {


    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "code",length = 4)
    private Long code;

    @NotNull
    @Column(name = "phone",length = 11)
    private String phone;

    @NotNull
    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bank",cascade = CascadeType.ALL)
    private List<Account> accountList;




}