package com.software.atm.user;

import com.software.atm.account.Account;
import com.software.atm.common.BaseEntity;
import com.software.atm.credit_card.Card;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;


@Data
@Table(name = "user_tbl")
@Entity
@Audited
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;


    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    private Date birthDay;


    @NotNull(message = "{phone.noempty}")
    @Column(name = "phone")
    @Pattern(regexp = "^[0-9]{10}$",message = "valid phone number")
    private String phone;

    @NotNull
    @Column(name = "national_code",unique = true)
    @Pattern(regexp = "^[0-9]{10}$",message ="valid national code" )
    private String nationalCode;

    @NotNull
    @Column(name = "job")
    private String job;

    @NotNull
    @Column(name = "is_married")
    private Boolean isMarried;

    @NotNull
    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private List<Account> accountList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private List<Card> cards;


}
