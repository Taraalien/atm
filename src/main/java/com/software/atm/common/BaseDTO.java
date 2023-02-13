package com.software.atm.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {

    @ApiModelProperty(required = true,hidden = false)
    private Long Id;


    @ApiModelProperty(required = true,hidden = false)
    @JsonIgnore
    private Integer version;


    @ApiModelProperty(required = true,hidden = false)
    @JsonIgnore
    private String createdBy;


    @ApiModelProperty(required = true,hidden = false)
    @JsonIgnore
    private Date createdDate;


    @ApiModelProperty(required = true,hidden = false)
    @JsonIgnore
    private String lastModifiedBy;


    @ApiModelProperty(required = true,hidden = false)
    @JsonIgnore
    private Date lastModifiedDate;
}
