package com.example.glovo.dto;
import lombok.Data;

import java.util.List;
@Data
public class Order {
    private Integer id;
    private List<Product> products;
}
