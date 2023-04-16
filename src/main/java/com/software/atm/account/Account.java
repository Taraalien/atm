package com.software.atm.account;


import com.software.atm.bank.Bank;
import com.software.atm.branch.Branch;
import com.software.atm.common.BaseEntity;
import com.software.atm.credit_card.Card;
import com.software.atm.user.User;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "tbl_account")
@Audited
public class Account  extends BaseEntity {

    @NotNull
    @Column(name = "balance")
    private BigDecimal balance;

    @NotNull
    @Column(name = "account_number",unique = true)
    private String accountNumber;

    @NotNull
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account")
    private Card  card;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;


}
