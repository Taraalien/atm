package com.software.atm.account;


import com.software.atm.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto extends BaseDTO {


    @ApiModelProperty(required = true,hidden = false)
    private BigDecimal balance;

    @ApiModelProperty(required = true,hidden = false)
    private AccountType accountType;
}
