package com.software.atm.user;

import com.software.atm.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Data
@Builder
@Table(name = "user_tbl")
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
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


    @NotNull
    @Column(name = "phone")
    //^start with string between 0 to 9 10 times  $ end of string
    @Pattern(regexp = "^[0-9]{10}$",message = "valid phone number")
    private String phone;

    @NotNull
    @Column(name = "national_code")
    @Pattern(regexp = "^[0-9]{10}$")
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




}
