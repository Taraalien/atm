package com.software.atm.credit_card;


import com.software.atm.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.sql.Date;

@Data
public class CardDto extends BaseDTO {



    @ApiModelProperty(required = true,hidden = false)
    private Date date;


    @ApiModelProperty(required = true,hidden = false)
    private String cardNumber;

    @ApiModelProperty(required = true,hidden = false)
    @Size(max = 4,min = 4,message = "not valid")
    private String pin;

    @ApiModelProperty(required = true,hidden = false)
    private Status status;

    @ApiModelProperty(required = true,hidden = false)
    private Date expireDate;

    @ApiModelProperty(required = true,hidden = false)
    private Long userId;

    @ApiModelProperty(required = true,hidden = false)
    private Long accountId;


}
