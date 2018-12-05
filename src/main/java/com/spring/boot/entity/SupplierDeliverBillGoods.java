package com.spring.boot.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SupplierDeliverBillGoods implements Serializable {

    private BigDecimal amount;

    private Long id;
}
