package com.api.verba.dto.product;

import com.api.verba.enumm.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal amount;
    private BigDecimal value;
    private ProductType productType;
}
