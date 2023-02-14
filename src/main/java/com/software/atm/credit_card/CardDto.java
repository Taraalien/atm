package com.software.atm.credit_card;


import com.software.atm.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class CardDto extends BaseDTO {

    @ApiModelProperty(required = true,hidden = false)
    private String cardNumber;

    @ApiModelProperty(required = true,hidden = false)
    private String pin;

    @ApiModelProperty(required = true,hidden = false)
    private Status status;

    @ApiModelProperty(required = true,hidden = false)
    private Date expireDate;

;

}
