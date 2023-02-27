package com.software.atm.branch;

import com.software.atm.account.Account;
import com.software.atm.bank.Bank;
import com.software.atm.common.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Table(name = "tbl_branch")
@Data
@Entity
@Audited
public class Branch extends BaseEntity {


    @NotNull
    @Column(name = "code",unique = true)
    private String code;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;


    @NotNull
    @Column(name = "phone",length = 11)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "branch",cascade = CascadeType.ALL)
    private List<Account> accounts;

}
