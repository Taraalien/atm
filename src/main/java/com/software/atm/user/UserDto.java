package com.software.atm.user;

import com.software.atm.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;


@Data
public class UserDto extends BaseDTO {

    @ApiModelProperty(required = true,hidden = false)
    private String name;


    @ApiModelProperty(required = true,hidden = false)
    private String lastName;

    @ApiModelProperty(required = true,hidden = false)
    private Date birthDay;

    @ApiModelProperty(required = true,hidden = false)
    @Pattern(regexp = "^[0-9]{10}$",message = "valid phone number")
    private String phone;

    @ApiModelProperty(required = true,hidden = false)
    @Pattern(regexp = "^[0-9]{10}$",message ="valid national code" )
    private String nationalCode;

    @ApiModelProperty(required = true,hidden = false)
    private String job;

    @ApiModelProperty(required = true,hidden = false)
    private Boolean isMarried;

    @ApiModelProperty(required = true,hidden = false)
    private String address;

    }
