package com.idilantha.pos.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemDto {

    private int id;
    private String description;
    private BigDecimal price;
    private int quantity;
}
