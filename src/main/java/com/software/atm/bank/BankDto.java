package com.software.atm.bank;


import com.software.atm.account.Account;
import com.software.atm.account.AccountDto;
import com.software.atm.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BankDto extends BaseDTO {


    @ApiModelProperty(required = true,hidden = false)
    private String name;

    @ApiModelProperty(required = true,hidden = false)
    private Long code;

    @ApiModelProperty(required = true,hidden = false)
    private String phone;

    @ApiModelProperty(required = true,hidden = false)
    private String address;




}
