package com.example.RecordScanner.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "The response object given when requests are made to the service")
@Builder
public class recordResponse {

    @ApiModelProperty(" The resulting outcome of the request")
    private int status;
    @ApiModelProperty(value = "the people returned from the request")
    private Object record;
}
