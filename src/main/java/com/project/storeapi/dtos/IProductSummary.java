package com.project.storeapi.dtos;

import java.math.BigDecimal;

public interface IProductSummary {
    Long getId();
    String getName();
    BigDecimal getPrice();
}
