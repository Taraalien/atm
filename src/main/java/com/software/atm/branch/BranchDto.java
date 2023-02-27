package com.software.atm.branch;


import com.software.atm.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BranchDto extends BaseDTO {

    @ApiModelProperty(required = true,hidden = false)
    private String code;

    @ApiModelProperty(required = true,hidden = false)
    private String name;

    @ApiModelProperty(required = true,hidden = false)
    private String address;


    @ApiModelProperty(required = true,hidden = false)
    private String phone;

    @ApiModelProperty(required = true,hidden = false)
    private Long bankId;

}
