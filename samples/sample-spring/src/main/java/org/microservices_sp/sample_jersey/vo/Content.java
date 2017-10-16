/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.microservices_sp.sample_jersey.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Andre Dias (andre.dias@summa.com.br)
 * @since 2016-09-19
 * @version 1.0
 */
@NoArgsConstructor
@ApiModel(value = "General Content", description = "General Content (Description")
public class Content {

    @Getter
    @Setter
    @ApiModelProperty(value = "The payload of this representation")
    private String realContent;

}