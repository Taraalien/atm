package com.software.atm.credit_card;

import com.software.atm.common.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Entity
@Data
@Table(name = "card_card")
@Audited
public class Card extends BaseEntity {


    @NotNull
    @Column(name = "card_number",length = 16)
    private String cardNumber;

    @NotNull
    @Column(name = "pin",length = 4)
    private String pin;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(name = "expire_time")
    private Date expireDate;



}
